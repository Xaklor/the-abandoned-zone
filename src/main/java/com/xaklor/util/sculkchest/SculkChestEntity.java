/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.xaklor.util.sculkchest;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class SculkChestEntity
        extends BlockEntity
        implements LidOpenable, Inventory, NamedScreenHandlerFactory {
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);
    private BlockPos targetChest;
    private final ChestLidAnimator lidAnimator = new ChestLidAnimator();
    private final ViewerCountManager stateManager = new ViewerCountManager(){

        @Override
        protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
            world.syncWorldEvent(WorldEvents.SCULK_SHRIEKS, pos, 0);
            world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.BLOCKS, 1f, world.random.nextFloat() * 0.1f + 0.9f);
        }

        @Override
        protected void onContainerClose(World world, BlockPos pos, BlockState state) {
            world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_ENDER_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5f, world.random.nextFloat() * 0.1f + 0.9f);
        }

        @Override
        protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            world.addSyncedBlockEvent(SculkChestEntity.this.pos, TheAbandonedZoneMod.SCULK_CHEST, 1, newViewerCount);
        }

        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
                Inventory inventory = ((GenericContainerScreenHandler)player.currentScreenHandler).getInventory();
                Inventory targetInventory = SculkChestEntity.getTargetInventory(world, targetChest);
                return inventory == SculkChestEntity.this || inventory == targetInventory || (inventory instanceof DoubleInventory && ((DoubleInventory)inventory).isPart(targetInventory));
            }
            return false;
        }
    };

    public SculkChestEntity(BlockPos pos, BlockState state) {
        super(TheAbandonedZoneMod.SCULK_CHEST_ENTITY, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, SculkChestEntity blockEntity) {
        blockEntity.lidAnimator.step();
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (type == 1) {
            this.lidAnimator.setOpen(data > 0);
            return true;
        }
        return super.onSyncedBlockEvent(type, data);
    }

    public void onOpen(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.openContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    public void onClose(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.closeContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    public float getAnimationProgress(float tickDelta) {
        return this.lidAnimator.getProgress(tickDelta);
    }

    public void onScheduledTick() {
        if (!this.removed) {
            /*
            TheAbandonedZoneMod.LOGGER.info(String.valueOf(this.getCachedState()));
            if (world instanceof ServerWorld sworld) {
                ChunkPos chunklington = new ChunkPos(targetChest);
                sworld.getChunkManager().addTicket(ChunkTicketType.FORCED, chunklington, 2, chunklington);
            }
             */
            this.stateManager.updateViewerCount(this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        if (world instanceof ServerWorld sworld) {
            // ChunkPos chunklington = new ChunkPos(targetChest); <- null pointer on unlinked
            // sworld.getChunkManager().addTicket(ChunkTicketType.FORCED, chunklington, 2, chunklington);
            // sworld.getBlockState(targetChest);
            Inventory targetInventory = SculkChestEntity.getTargetInventory(world, targetChest);
            if (targetInventory != null) {
                if (targetInventory.size() == 54) {
                    return SculkChestScreenHandler.createGeneric9x6(syncId, playerInventory, targetInventory, this);
                }
                return SculkChestScreenHandler.createGeneric9x3(syncId, playerInventory, targetInventory, this);
            } else { return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this); }
        }
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    public void updateTarget(BlockPos target) {
        targetChest = target;
        markDirty();
    }

    private static Inventory getTargetInventory (World world, BlockPos target) {
        if (target != null && world.getBlockEntity(target) instanceof ChestBlockEntity) {
            BlockState chestState = world.getBlockState(target);
            ChestBlock block = (ChestBlock) chestState.getBlock();
            return ChestBlock.getInventory(block, chestState, world, target, true);
        }
        return null;
    }

    protected void scatterContents () {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        for (ItemStack stack : inventory) {
            double d = EntityType.ITEM.getWidth();
            double e = 1.0 - d;
            double f = d / 2.0;
            double g = x + world.random.nextDouble() * e + f;
            double h = y + world.random.nextDouble() * e;
            double i = z + world.random.nextDouble() * e + f;
            while (!stack.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(world, g, h, i, stack.split(world.random.nextInt(21) + 10));
                itemEntity.setVelocity(world.random.nextTriangular(0.0, 0.11485000171139836), world.random.nextTriangular(0.2, 0.11485000171139836), world.random.nextTriangular(0.0, 0.11485000171139836));
                world.spawnEntity(itemEntity);
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        if (targetChest != null) {
            nbt.putBoolean("linked", true);
            nbt.putInt("target x", targetChest.getX());
            nbt.putInt("target y", targetChest.getY());
            nbt.putInt("target z", targetChest.getZ());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        boolean linked = nbt.getBoolean("linked");
        if (linked) {
            int x = nbt.getInt("target x");
            int y = nbt.getInt("target y");
            int z = nbt.getInt("target z");
            targetChest = new BlockPos(x, y, z);
        }
    }

    // Inventory methods
    // size of the inventory
    @Override
    public int size() {
        Inventory inv = getTargetInventory(world, targetChest);
        if (inv != null) { return inv.size(); }
        return this.inventory.size();
    }

    // is the inventory only empty stacks
    @Override
    public boolean isEmpty() {
        Inventory inv = getTargetInventory(world, targetChest);
        if (inv != null) { return inv.isEmpty(); }

        for (ItemStack itemStack : this.inventory) {
            if (!itemStack.isEmpty()) return false;
        }
        return true;
    }

    // retrieve item in slot
    @Override
    public ItemStack getStack(int slot) {
        Inventory inv = getTargetInventory(world, targetChest);
        if (inv != null) { return inv.getStack(slot); }

        return this.inventory.get(slot);
    }

    // remove amount items from slot
    @Override
    public ItemStack removeStack(int slot, int amount) {
        Inventory inv = getTargetInventory(world, targetChest);
        if (inv != null) { return inv.removeStack(slot, amount); }

        ItemStack result = Inventories.splitStack(this.inventory, slot, amount);
        markDirty();
        return result;
    }

    // remove all items from slot
    @Override
    public ItemStack removeStack(int slot) {
        Inventory inv = getTargetInventory(world, targetChest);
        if (inv != null) { return inv.removeStack(slot); }

        ItemStack result = Inventories.removeStack(this.inventory, slot);
        markDirty();
        return result;
    }

    // replaces stack in slot with new stack, resized to max if necessary
    @Override
    public void setStack(int slot, ItemStack stack) {
        Inventory inv = getTargetInventory(world, targetChest);
        if (inv != null) { inv.setStack(slot, stack); }

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
}

