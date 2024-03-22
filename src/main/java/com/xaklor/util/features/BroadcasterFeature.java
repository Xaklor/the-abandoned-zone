package com.xaklor.util.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BroadcasterFeature extends Feature<DefaultFeatureConfig> {
    public BroadcasterFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    // this method is what is called when the game tries to generate the feature. it is where the actual blocks get placed into the world.
    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        // the origin is the place where the game starts trying to place the feature
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        int height = random.nextInt(3) + 3;

        BlockState state = world.getBlockState(origin.down());
        if (state.isOf(Blocks.COARSE_DIRT) || state.isOf(Blocks.GRAVEL) || state.isOf(Blocks.SMOOTH_BASALT)) {
            // the "fence posts"
            if (world.getBlockState(origin.add(-1, 0, -1)).isOf(Blocks.AIR)) { world.setBlockState(origin.add(-1, 0, -1), Blocks.IRON_BARS.getDefaultState(), 0x10); }
            if (world.getBlockState(origin.add( 1, 0, -1)).isOf(Blocks.AIR)) { world.setBlockState(origin.add( 1, 0, -1), Blocks.IRON_BARS.getDefaultState(), 0x10); }
            if (world.getBlockState(origin.add(-1, 0,  1)).isOf(Blocks.AIR)) { world.setBlockState(origin.add(-1, 0,  1), Blocks.IRON_BARS.getDefaultState(), 0x10); }
            if (world.getBlockState(origin.add( 1, 0,  1)).isOf(Blocks.AIR)) { world.setBlockState(origin.add( 1, 0,  1), Blocks.IRON_BARS.getDefaultState(), 0x10); }
            // the chains
            if (world.getBlockState(origin.add(-1, 0,  0)).isOf(Blocks.AIR)) { world.setBlockState(origin.add(-1, 0,  0), Blocks.CHAIN.getDefaultState().with(Properties.AXIS, Direction.Axis.Z), 0x10); }
            if (world.getBlockState(origin.add( 1, 0,  0)).isOf(Blocks.AIR)) { world.setBlockState(origin.add( 1, 0,  0), Blocks.CHAIN.getDefaultState().with(Properties.AXIS, Direction.Axis.Z), 0x10); }
            if (world.getBlockState(origin.add( 0, 0, -1)).isOf(Blocks.AIR)) { world.setBlockState(origin.add( 0, 0, -1), Blocks.CHAIN.getDefaultState().with(Properties.AXIS, Direction.Axis.X), 0x10); }
            if (world.getBlockState(origin.add( 0, 0,  1)).isOf(Blocks.AIR)) { world.setBlockState(origin.add( 0, 0,  1), Blocks.CHAIN.getDefaultState().with(Properties.AXIS, Direction.Axis.X), 0x10); }
            // the pillar
            if (world.getBlockState(origin.up()).isOf(Blocks.AIR)) {
                for (int i = 0; i < height; i++) {
                    world.setBlockState(origin, Blocks.POLISHED_BASALT.getDefaultState(), 0x10);
                    origin = origin.up();

                    // ensure we don't try to place blocks outside the world
                    if (origin.getY() >= world.getTopY()) break;
                }
                // the copper top
                world.setBlockState(origin, Blocks.OXIDIZED_CUT_COPPER.getDefaultState(), 0x10);
                return true;
            }
        }
        // the game couldn't find a place to put the pillar
        return false;
    }
}
