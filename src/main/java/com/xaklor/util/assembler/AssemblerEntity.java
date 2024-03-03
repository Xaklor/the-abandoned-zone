package com.xaklor.util.assembler;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;


public class AssemblerEntity extends BlockEntity implements Inventory, NamedScreenHandlerFactory {
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
    private int workTime = 0;
    private final int maxWorkTime = 600;
    private int level = 1;
    private ItemStack[] initialState = new ItemStack[] { new ItemStack(Blocks.AIR), new ItemStack(Blocks.AIR), new ItemStack(Blocks.AIR), new ItemStack(Blocks.AIR), new ItemStack(Blocks.AIR)};
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){

        @Override
        public int get(int index) {
            if (index == 1) { return level; }
            return workTime;
        }

        @Override
        public void set(int index, int value) {
            if (index != 1) { workTime = value; }
        }

        @Override
        public int size() {
            return 2;
        }
    };


    public AssemblerEntity(BlockPos pos, BlockState state) {
        super(TheAbandonedZoneMod.ASSEMBLER_ENTITY, pos, state);
    }

    public static void tick (World world, BlockPos pos, BlockState state, AssemblerEntity entity) {
        if (!world.isClient) {
            // periodically check if the belts and arms exist and adjust level accordingly
            if (world.getTime() % 40L == 0L) {
                entity.level = getLevel(world, pos);
            }

            boolean startedOn = entity.isOn();
            boolean changed = false;
            ItemStack inputStack1 = entity.inventory.get(0);
            ItemStack inputStack2 = entity.inventory.get(1);
            ItemStack inputStack3 = entity.inventory.get(2);
            ItemStack inputStack4 = entity.inventory.get(3);
            ItemStack outputStack = entity.inventory.get(4);
            // if the assembler is off, determine if we can begin work
            if (!entity.isOn()) {
                Item outputItem = switch (entity.level) {
                    case 3 ->  TheAbandonedZoneMod.ASSEMBLER_RECIPES.LookupRecipe(inputStack1, inputStack2, inputStack3, inputStack4);
                    case 2 ->  TheAbandonedZoneMod.ASSEMBLER_RECIPES.LookupRecipe(inputStack1, inputStack2, inputStack3);
                    default -> TheAbandonedZoneMod.ASSEMBLER_RECIPES.LookupRecipe(inputStack1, inputStack2);
                };
                if (outputItem != null && AssemblerEntity.canAcceptRecipeOutput(outputItem, entity.inventory, outputItem.getMaxCount())) {
                    changed = true;
                    entity.initialState = new ItemStack[]{inputStack1.copy(), inputStack2.copy(), inputStack3.copy(), inputStack4.copy(), outputItem.getDefaultStack()};
                    entity.workTime = entity.maxWorkTime;
                }
                // if the assembler is on, verify the inputs are unchanged and then make progress
            } else {
            boolean isValid  = inputStack1.isOf(entity.initialState[0].getItem()) && inputStack1.getCount() == entity.initialState[0].getCount();
                    isValid &= inputStack2.isOf(entity.initialState[1].getItem()) && inputStack2.getCount() == entity.initialState[1].getCount();
                    isValid &= inputStack3.isOf(entity.initialState[2].getItem()) && inputStack3.getCount() == entity.initialState[2].getCount();
                    isValid &= inputStack4.isOf(entity.initialState[3].getItem()) && inputStack4.getCount() == entity.initialState[3].getCount();

                if (isValid && AssemblerEntity.canAcceptRecipeOutput(entity.initialState[4].getItem(), entity.inventory, entity.initialState[4].getItem().getMaxCount())) {
                    entity.workTime--;
                    if (entity.workTime <= 0) {
                        changed = true;
                        inputStack1.setCount(0);
                        inputStack2.setCount(0);
                        inputStack3.setCount(0);
                        inputStack4.setCount(0);
                        if (outputStack.isEmpty()) {
                            entity.inventory.set(4, new ItemStack(entity.initialState[4].getItem(), 1));
                        } else outputStack.increment(1);
                    }
                    // if the state has been tampered with, reset everything
                } else {
                    entity.workTime = 0;
                }
            }
            // if on status has changed, update block state
            if (startedOn != entity.isOn()) {
                changed = true;
                state = state.with(Assembler.ON, entity.isOn());
                world.setBlockState(pos, state, Block.NOTIFY_ALL);
            }
            // save changes
            if (changed) entity.markDirty();
        }
    }

    // returns true if the output inventory slot can accept at least one more copy of the output item
    private static boolean canAcceptRecipeOutput(@Nullable Item output, DefaultedList<ItemStack> slots, int stackMax) {
        if (output == null) {
            return false;
        }
        ItemStack outputSlot = slots.get(4);
        if (outputSlot.isEmpty()) {
            return true;
        }
        if (!outputSlot.isOf(output)) {
            return false;
        }
        return outputSlot.getCount() < stackMax && outputSlot.getCount() < outputSlot.getMaxCount();
    }

    private static int getLevel(World world, BlockPos pos) {
        if (world.getBlockState(pos.add(1, 0, 0)).isOf(TheAbandonedZoneMod.CONVEYOR_BELT) &&
                world.getBlockState(pos.add(-1, 0, 0)).isOf(TheAbandonedZoneMod.CONVEYOR_BELT)) {
            if (world.getBlockState(pos.add(1, 1, 0)).isOf(TheAbandonedZoneMod.ASSEMBLER_ARMS) &&
                    world.getBlockState(pos.add(-1, 1, 0)).isOf(TheAbandonedZoneMod.ASSEMBLER_ARMS)) {
                return 3;
            } else {
                return 2;
            }
        } else if (world.getBlockState(pos.add(0, 0, 1)).isOf(TheAbandonedZoneMod.CONVEYOR_BELT) &&
                world.getBlockState(pos.add(0, 0, -1)).isOf(TheAbandonedZoneMod.CONVEYOR_BELT)) {
            if (world.getBlockState(pos.add(0, 1, 1)).isOf(TheAbandonedZoneMod.ASSEMBLER_ARMS) &&
                    world.getBlockState(pos.add(0, 1, -1)).isOf(TheAbandonedZoneMod.ASSEMBLER_ARMS)) {
                return 3;
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    private boolean isOn() {
        return this.workTime > 0;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AssemblerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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
        NbtList list = new NbtList();
        list.add(initialState[0].writeNbt(new NbtCompound()));
        list.add(initialState[1].writeNbt(new NbtCompound()));
        list.add(initialState[2].writeNbt(new NbtCompound()));
        list.add(initialState[3].writeNbt(new NbtCompound()));
        list.add(initialState[4].writeNbt(new NbtCompound()));
        nbt.put("state", list);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        this.workTime = nbt.getShort("workTime");
        // NbtList list = nbt.getList("state", NbtElement.LIST_TYPE);
        NbtList list = (NbtList) nbt.get("state");
        this.initialState = new ItemStack[5];
        this.initialState[0] = ItemStack.fromNbt(list.getCompound(0));
        this.initialState[1] = ItemStack.fromNbt(list.getCompound(1));
        this.initialState[2] = ItemStack.fromNbt(list.getCompound(2));
        this.initialState[3] = ItemStack.fromNbt(list.getCompound(3));
        this.initialState[4] = ItemStack.fromNbt(list.getCompound(4));
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
