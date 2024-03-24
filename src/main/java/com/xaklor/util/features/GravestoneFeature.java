package com.xaklor.util.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GravestoneFeature extends Feature<DefaultFeatureConfig> {
    public GravestoneFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        BlockState state = world.getBlockState(origin.down());
        if (state.isOf(Blocks.GRASS_BLOCK) && origin.getY() + 3 < world.getTopY()) {
            switch (random.nextInt(3)) {
                // cross
                case 2 -> {
                    world.setBlockState(origin, Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 1, 1, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 0, 1, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add(-1, 1, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 0, 2, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                }
                // large
                case 1 -> {
                    world.setBlockState(origin, Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 1, 1, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 0, 1, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add(-1, 1, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 0, 2, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 1, 0, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add(-1, 0, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                }
                // small
                default -> {
                    world.setBlockState(origin, Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 1, 1, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 0, 1, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                    world.setBlockState(origin.add( 1, 0, 0), Blocks.WHITE_CONCRETE.getDefaultState(), 0x10);
                }
            }
        }
        // the game couldn't find a place to put the pillar
        return false;
    }
}
