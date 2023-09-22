package com.xaklor.util.alchemybox;

import com.xaklor.TheAbandonedZoneMod;
import com.xaklor.util.disintegrator.Disintegrator;
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

public class AlchemyBoxEntity extends BlockEntity implements SidedInventory, NamedScreenHandlerFactory {
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private static final int[] TOP_SLOTS = new int[]{0, 1};
    private static final int[] BOTTOM_SLOTS = new int[]{3};
    private static final int[] SIDE_SLOTS = new int[]{2};
    private int workTime = 0;
    private final int maxWorkTime = 200;
    private int fuelCharges = 0;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){

        @Override
        public int get(int index) {
            if (index == 0)
                return workTime;

            return fuelCharges;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0)
                workTime = value;
            else
                fuelCharges = value;
        }

        @Override
        public int size() {
            return 2;
        }
    };


    public AlchemyBoxEntity(BlockPos pos, BlockState state) {
        super(TheAbandonedZoneMod.ALCHEMY_BOX_ENTITY, pos, state);
    }

    public static void tick (World world, BlockPos pos, BlockState state, AlchemyBoxEntity entity) {
        boolean startedOn = entity.isOn();
        boolean changed = false;
        ItemStack leftInStack = entity.inventory.get(0);
        ItemStack rightInStack = entity.inventory.get(1);
        ItemStack fuelStack = entity.inventory.get(2);
        ItemStack outputStack = entity.inventory.get(3);
        boolean hasInput = !leftInStack.isEmpty() && !rightInStack.isEmpty();
        boolean hasFuel = !fuelStack.isEmpty() && fuelStack.isOf(TheAbandonedZoneMod.LAPIS_LAZULI_DUST);
        boolean hasCharges = entity.fuelCharges > 0;
        // if we're out of charges but we have fuel, add more charges
        if (hasFuel && entity.fuelCharges <= 0) {
            fuelStack.decrement(1);
            entity.fuelCharges = 8;
            changed = true;
        }
        if (entity.isOn() || hasCharges && hasInput) {
            Item outputItem = hasInput ? TheAbandonedZoneMod.ALCHEMY_BOX_RECIPES.LookupRecipe(leftInStack.getItem(), rightInStack.getItem()) : null;
            int stackMax = entity.getMaxCountPerStack();
            // if the alchemy box is not on and the output slot can accept the output, start the fusion process
            if (!entity.isOn() && AlchemyBoxEntity.canAcceptRecipeOutput(outputItem, entity.inventory, stackMax)) {
                changed = true;
                entity.workTime = entity.maxWorkTime;
                entity.fuelCharges--;
            }
            // if the alchemy box IS on and the output slot can accept the output, make fusion progress
            if (entity.isOn() && AlchemyBoxEntity.canAcceptRecipeOutput(outputItem, entity.inventory, stackMax)) {
                entity.workTime--;
                if (entity.workTime <= 0) {
                    changed = true;
                    leftInStack.decrement(1);
                    rightInStack.decrement(1);
                    if (outputStack.isEmpty()) {
                        // canAcceptRecipeOutput returns false if outputItem is null, this is safe
                        assert outputItem != null;
                        entity.inventory.set(3, new ItemStack(outputItem, 1));
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

    private static boolean canAcceptRecipeOutput(@Nullable Item output, DefaultedList<ItemStack> slots, int stackMax) {
        if (slots.get(0).isEmpty() || slots.get(1).isEmpty() || output == null) {
            return false;
        }
        ItemStack outputSlot = slots.get(3);
        if (outputSlot.isEmpty()) {
            return true;
        }
        if (!outputSlot.isOf(output)) {
            return false;
        }
        return outputSlot.getCount() < stackMax && outputSlot.getCount() < outputSlot.getMaxCount();
    }

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
    public int size() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.inventory) {
            if (!itemStack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(this.inventory, slot, amount);
        markDirty();
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(this.inventory, slot);
        markDirty();
        return result;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.inventory.set(slot, stack);
        if (stack.getCount() > stack.getMaxCount()) {
            stack.setCount(stack.getMaxCount());
        }
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlchemyBoxScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putShort("workTime", (short) workTime);
        nbt.putShort("fuelCharges", (short) fuelCharges);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        this.workTime = nbt.getShort("workTime");
        this.fuelCharges = nbt.getShort("fuelCharges");
    }
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == 3) {
            return false;
        }
        if (slot == 2) {
            return stack.isOf(TheAbandonedZoneMod.LAPIS_LAZULI_DUST);
        }
        return true;
    }
}
