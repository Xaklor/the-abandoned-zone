package com.xaklor.util.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CraneFeature extends Feature<DefaultFeatureConfig> {
    public CraneFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    // this method is what is called when the game tries to generate the feature. it is where the actual blocks get placed into the world.
    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        // the origin is the place where the game starts trying to place the feature
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        int height = random.nextInt(7) + 7;

        BlockState state = world.getBlockState(origin.down());
        if (state.isOf(Blocks.COARSE_DIRT) || state.isOf(Blocks.GRAVEL) || state.isOf(Blocks.SMOOTH_BASALT)) {
            // the pillar
            if (world.getBlockState(origin.up()).isOf(Blocks.AIR)) {
                for (int i = 0; i < height; i++) {
                    world.setBlockState(origin, Blocks.POLISHED_BASALT.getDefaultState(), 0x10);
                    origin = origin.up();

                    // ensure we don't try to place blocks outside the world
                    if (origin.getY() >= world.getTopY()) break;
                }
                // the crane
                switch (random.nextInt(4)) {
                    // west facing
                    case 3  -> {
                        world.setBlockState(origin.east(), Blocks.OXIDIZED_CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(Properties.BLOCK_HALF, BlockHalf.TOP), 0x10);
                        for (int i = 0; i < 3; i++) {
                            world.setBlockState(origin, Blocks.OXIDIZED_CUT_COPPER.getDefaultState(), 0x10);
                            origin = origin.west();
                        }
                        origin = origin.east();
                        for (int i = 0; i < 5; i++) {
                            origin = origin.down();
                            world.setBlockState(origin, Blocks.CHAIN.getDefaultState(), 0x10);
                        }
                    }
                    // east facing
                    case 2  -> {
                        world.setBlockState(origin.west(), Blocks.OXIDIZED_CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(Properties.BLOCK_HALF, BlockHalf.TOP), 0x10);
                        for (int i = 0; i < 3; i++) {
                            world.setBlockState(origin, Blocks.OXIDIZED_CUT_COPPER.getDefaultState(), 0x10);
                            origin = origin.east();
                        }
                        origin = origin.west();
                        for (int i = 0; i < 5; i++) {
                            origin = origin.down();
                            world.setBlockState(origin, Blocks.CHAIN.getDefaultState(), 0x10);
                        }
                    }
                    // north facing
                    case 1  -> {
                        world.setBlockState(origin.south(), Blocks.OXIDIZED_CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(Properties.BLOCK_HALF, BlockHalf.TOP), 0x10);
                        for (int i = 0; i < 3; i++) {
                            world.setBlockState(origin, Blocks.OXIDIZED_CUT_COPPER.getDefaultState(), 0x10);
                            origin = origin.north();
                        }
                        origin = origin.south();
                        for (int i = 0; i < 5; i++) {
                            origin = origin.down();
                            world.setBlockState(origin, Blocks.CHAIN.getDefaultState(), 0x10);
                        }
                    }
                    // south facing
                    default -> {
                        world.setBlockState(origin.north(), Blocks.OXIDIZED_CUT_COPPER_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH).with(Properties.BLOCK_HALF, BlockHalf.TOP), 0x10);
                        for (int i = 0; i < 3; i++) {
                            world.setBlockState(origin, Blocks.OXIDIZED_CUT_COPPER.getDefaultState(), 0x10);
                            origin = origin.south();
                        }
                        origin = origin.north();
                        for (int i = 0; i < 5; i++) {
                            origin = origin.down();
                            world.setBlockState(origin, Blocks.CHAIN.getDefaultState(), 0x10);
                        }
                    }
                }
                return true;
            }
        }
        // the game couldn't find a place to put the pillar
        return false;
    }
}
