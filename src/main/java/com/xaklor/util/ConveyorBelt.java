package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ConveyorBelt extends HorizontalFacingBlock {
    private static final VoxelShape NE_LEG = createCuboidShape(0 ,0, 0,  4,  7,  4);
    private static final VoxelShape SE_LEG = createCuboidShape(0, 0, 12, 4,  7,  16);
    private static final VoxelShape NW_LEG = createCuboidShape(12,0, 0,  16, 7,  4);
    private static final VoxelShape SW_LEG = createCuboidShape(12,0, 12, 16, 7,  16);
    private static final VoxelShape BODY =   createCuboidShape(0, 7, 0,  16, 12, 16);
    protected static final VoxelShape SHAPE = VoxelShapes.union(NE_LEG, SE_LEG, NW_LEG, SW_LEG, BODY);
    public ConveyorBelt(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
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
        Registry.register(Registries.BLOCK, new Identifier(TheAbandonedZoneMod.MOD_ID, "conveyor_belt"), this);
        Registry.register(Registries.ITEM, new Identifier(TheAbandonedZoneMod.MOD_ID, "conveyor_belt"), new BlockItem(this, new FabricItemSettings()));
    }
}
