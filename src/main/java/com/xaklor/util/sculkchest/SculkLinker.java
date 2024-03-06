package com.xaklor.util.sculkchest;

import com.xaklor.TheAbandonedZoneMod;
import com.xaklor.util.general.AbandonedZoneItem;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SculkLinker extends AbandonedZoneItem {
    public SculkLinker(String id, Settings settings) {
        super(id, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        NbtCompound nbt = context.getStack().getOrCreateNbt();
        if (world.getBlockState(pos).isOf(Blocks.CHEST)) {
            nbt.putBoolean("linked", true);
            nbt.putInt("x", pos.getX());
            nbt.putInt("y", pos.getY());
            nbt.putInt("z", pos.getZ());
            return ActionResult.success(world.isClient);
        }
        if (nbt.getBoolean("linked") && world.getBlockState(pos).isOf(TheAbandonedZoneMod.SCULK_CHEST)) {
            SculkChestEntity entity = world.getBlockEntity(pos) instanceof SculkChestEntity ? (SculkChestEntity)world.getBlockEntity(pos) : null;
            if (entity != null) { entity.updateTarget(new BlockPos(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z"))); }
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}
