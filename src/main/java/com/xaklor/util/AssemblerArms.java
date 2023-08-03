package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class AssemblerArms extends HorizontalFacingBlock {
    VoxelShape SHAPE = createCuboidShape(0, 10, 0, 16, 16, 16);
    public AssemblerArms(Settings settings) {
        super(settings);
        register();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }


    private void register() {
        Registry.register(Registries.BLOCK, new Identifier(TheAbandonedZoneMod.MOD_ID, "assembler_arms"), this);
        Registry.register(Registries.ITEM, new Identifier(TheAbandonedZoneMod.MOD_ID, "assembler_arms"), new BlockItem(this, new FabricItemSettings()));

    }
}