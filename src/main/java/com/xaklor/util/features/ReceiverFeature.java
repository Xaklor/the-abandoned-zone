package com.xaklor.util.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ReceiverFeature extends Feature<DefaultFeatureConfig> {
    public ReceiverFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        BlockState state = world.getBlockState(origin.down());
        if (state.isOf(Blocks.COARSE_DIRT) || state.isOf(Blocks.GRAVEL) || state.isOf(Blocks.SMOOTH_BASALT)) {
            // the corners
            world.setBlockState(origin.add(-1, 0, -1), Blocks.POLISHED_BASALT.getDefaultState(), 0x10);
            world.setBlockState(origin.add( 1, 0, -1), Blocks.POLISHED_BASALT.getDefaultState(), 0x10);
            world.setBlockState(origin.add(-1, 0,  1), Blocks.POLISHED_BASALT.getDefaultState(), 0x10);
            world.setBlockState(origin.add( 1, 0,  1), Blocks.POLISHED_BASALT.getDefaultState(), 0x10);
            world.setBlockState(origin.add(-1, 1, -1), Blocks.IRON_BARS.getDefaultState(), 0x10);
            world.setBlockState(origin.add( 1, 1, -1), Blocks.IRON_BARS.getDefaultState(), 0x10);
            world.setBlockState(origin.add(-1, 1,  1), Blocks.IRON_BARS.getDefaultState(), 0x10);
            world.setBlockState(origin.add( 1, 1,  1), Blocks.IRON_BARS.getDefaultState(), 0x10);
            // the sides
            world.setBlockState(origin.add(-1, 0,  0), Blocks.POLISHED_BASALT.getDefaultState().with(Properties.AXIS, Direction.Axis.Z), 0x10);
            world.setBlockState(origin.add( 1, 0,  0), Blocks.POLISHED_BASALT.getDefaultState().with(Properties.AXIS, Direction.Axis.Z), 0x10);
            world.setBlockState(origin.add( 0, 0, -1), Blocks.POLISHED_BASALT.getDefaultState().with(Properties.AXIS, Direction.Axis.X), 0x10);
            world.setBlockState(origin.add( 0, 0,  1), Blocks.POLISHED_BASALT.getDefaultState().with(Properties.AXIS, Direction.Axis.X), 0x10);
            // the center copper block
            world.setBlockState(origin, Blocks.OXIDIZED_CUT_COPPER.getDefaultState(), 0x10);
            return true;
        }
        return false;
    }
}
