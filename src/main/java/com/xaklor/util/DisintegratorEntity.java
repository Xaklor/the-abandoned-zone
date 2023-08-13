package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class DisintegratorEntity extends BlockEntity implements SidedInventory, NamedScreenHandlerFactory {
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private static final int[] TOP_SLOTS = new int[]{0};
    private static final int[] BOTTOM_SLOTS = new int[]{2};
    private static final int[] SIDE_SLOTS = new int[]{1};
    private int workTime = 0;
    private final int maxWorkTime = 200;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){

        @Override
        public int get(int index) {
            return workTime;
        }

        @Override
        public void set(int index, int value) {
            workTime = value;
        }

        @Override
        public int size() {
            return 1;
        }
    };


    public DisintegratorEntity(BlockPos pos, BlockState state) {
        super(TheAbandonedZoneMod.DISINTEGRATOR_ENTITY, pos, state);
    }

    public static void tick (World world, BlockPos pos, BlockState state, DisintegratorEntity entity) {
        boolean startedOn = entity.isOn();
        boolean changed = false;
        ItemStack inputStack = entity.inventory.get(0);
        ItemStack fuelStack = entity.inventory.get(1);
        ItemStack outputStack = entity.inventory.get(2);
        boolean hasInput = !inputStack.isEmpty() && !inputStack.hasEnchantments();
        boolean hasFuel = !fuelStack.isEmpty() && fuelStack.isOf(TheAbandonedZoneMod.FUEL_CANISTER);
        if (entity.isOn() || hasFuel && hasInput) {
            Item outputItem = hasInput ? TheAbandonedZoneMod.DISINTEGRATOR_RECIPES.recipeMap.get(inputStack.getItem()) : null;
            int stackMax = entity.getMaxCountPerStack();
            // if the disintegrator is not on and the output slot can accept the output, start the disintegration process
            if (!entity.isOn() && DisintegratorEntity.canAcceptRecipeOutput(outputItem, entity.inventory, stackMax)) {
                changed = true;
                entity.workTime = entity.maxWorkTime;
                fuelStack.decrement(1);
            }
            // if the disintegrator IS on and the output slot can accept the output, make disintegration progress
            if (entity.isOn() && DisintegratorEntity.canAcceptRecipeOutput(outputItem, entity.inventory, stackMax)) {
                entity.workTime--;
                if (entity.workTime <= 0) {
                    changed = true;
                    inputStack.decrement(1);
                    if (outputStack.isEmpty()) {
                        // canAcceptRecipeOutput returns false if outputItem is null, this is safe
                        assert outputItem != null;
                        entity.inventory.set(2, new ItemStack(outputItem, 1));
                    }
                    else outputStack.increment(1);
                }
            }
            // if the output slot cannot accept the output item
            else {
                entity.workTime = 0;
            }
        // if there is some reason to not work yet there is work progress, discard it
        } else if (!entity.isOn() && entity.workTime > 0) {
            entity.workTime = 0;
        }
        // if on status has changed, update block state
        if (startedOn != entity.isOn()) {
            changed = true;
            state = state.with(Disintegrator.ON, entity.isOn());
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }
        // save changes
        if (changed) entity.markDirty();

    }

    private boolean isOn() {
        return this.workTime > 0;
    }

    // returns true if the output inventory slot can accept at least one more copy of the output item
    private static boolean canAcceptRecipeOutput(@Nullable Item output, DefaultedList<ItemStack> slots, int stackMax) {
        if (slots.get(0).isEmpty() || output == null) {
            return false;
        }
        ItemStack outputSlot = slots.get(2);
        if (outputSlot.isEmpty()) {
            return true;
        }
        if (!outputSlot.isOf(output)) {
            return false;
        }
        return outputSlot.getCount() < stackMax && outputSlot.getCount() < outputSlot.getMaxCount();
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DisintegratorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putShort("workTime", (short) workTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        this.workTime = nbt.getShort("workTime");
    }

    // Inventory methods

    // size of the inventory
    @Override
    public int size() {
        return this.inventory.size();
    }

    // is the inventory only empty stacks
    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.inventory) {
            if (!itemStack.isEmpty()) return false;
        }
        return true;
    }

    // retrieve item in slot
    @Override
    public ItemStack getStack(int slot) {
        return this.inventory.get(slot);
    }

    // remove amount items from slot
    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(this.inventory, slot, amount);
        markDirty();
        return result;
    }

    // remove all items from slot
    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(this.inventory, slot);
        markDirty();
        return result;
    }

    // replaces stack in slot with new stack, resized to max if necessary
    @Override
    public void setStack(int slot, ItemStack stack) {
        this.inventory.set(slot, stack);
        if (stack.getCount() > stack.getMaxCount()) {
            stack.setCount(stack.getMaxCount());
        }
        markDirty();
    }

    // can the player interact with this
    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    // clears inventory
    @Override
    public void clear() {
        inventory.clear();
    }

    // gets which slots are available to which sides
    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) return BOTTOM_SLOTS;
        if (side == Direction.UP) return TOP_SLOTS;
        return SIDE_SLOTS;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.isValid(slot, stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == 2) {
            return false;
        }
        if (slot == 1) {
            return stack.isOf(TheAbandonedZoneMod.FUEL_CANISTER);
        }
        return true;
    }
}
