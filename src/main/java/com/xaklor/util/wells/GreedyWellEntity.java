package com.xaklor.util.wells;

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

public class GreedyWellEntity extends BlockEntity {
    private int numItems = 0;
    private int sumItems = 0;

    public GreedyWellEntity(BlockPos pos, BlockState state) {
        super(TheAbandonedZoneMod.DISINTEGRATOR_ENTITY, pos, state);
    }

    public static void tick (World world, BlockPos pos, BlockState state, GreedyWellEntity entity) {
        boolean changed = false;
        // save changes
        if (changed) entity.markDirty();

    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putShort("numItems", (short) numItems);
        nbt.putShort("sumItems", (short) sumItems);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.numItems = nbt.getShort("numItems");
        this.sumItems = nbt.getShort("sumItems");
    }
}
