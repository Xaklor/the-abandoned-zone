package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
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


    public DisintegratorEntity(BlockPos pos, BlockState state) {
        super(TheAbandonedZoneMod.DISINTEGRATOR_ENTITY, pos, state);
    }

    public static void tick (World world, BlockPos pos, BlockState state, DisintegratorEntity entity) {
        // entity.num++;
        // entity.markDirty();
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DisintegratorScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
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
            ItemStack itemStack = this.inventory.get(1);
            return itemStack.isOf(TheAbandonedZoneMod.FUEL_CANISTER);
        }
        return true;
    }
}
