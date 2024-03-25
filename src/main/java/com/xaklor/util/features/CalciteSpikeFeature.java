package com.xaklor.util.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IceSpikeFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/**
 * largely copied from IceSpikeFeature with some internal parameters changed
 */
public class CalciteSpikeFeature
        extends Feature<DefaultFeatureConfig> {
    public CalciteSpikeFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        int l;
        int k;
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        while (structureWorldAccess.isAir(blockPos) && blockPos.getY() > structureWorldAccess.getBottomY() + 2) {
            blockPos = blockPos.down();
        }
        if (!structureWorldAccess.getBlockState(blockPos).isOf(Blocks.GRASS_BLOCK)) {
            return false;
        }
        blockPos = blockPos.up(random.nextInt(4));
        int tip_height = random.nextInt(4) + 5;
        int tip_width = 2 /* tip_height / 3 + random.nextInt(2) + 1 */;
        blockPos = blockPos.up(10 + random.nextInt(30));
        for (k = 0; k < tip_height; ++k) {
            float f = (1.0f - (float)k / (float)tip_height) * (float)tip_width;
            l = MathHelper.ceil(f);
            for (int m = -l; m <= l; ++m) {
                float g = (float)MathHelper.abs(m) - 0.25f;
                for (int n = -l; n <= l; ++n) {
                    float h = (float)MathHelper.abs(n) - 0.25f;
                    if ((m != 0 || n != 0) && g * g + h * h > f * f || (m == -l || m == l || n == -l || n == l) || random.nextFloat() > 0.75f ) continue;
                    BlockState blockState = structureWorldAccess.getBlockState(blockPos.add(m, k, n));
                    if (blockState.isAir() || IceSpikeFeature.isSoil(blockState)) {
                        this.setBlockState(structureWorldAccess, blockPos.add(m, k, n), Blocks.CALCITE.getDefaultState());
                    }
                    if (k == 0 || l <= 1 || !(blockState = structureWorldAccess.getBlockState(blockPos.add(m, -k, n))).isAir() && !IceSpikeFeature.isSoil(blockState) && !blockState.isOf(Blocks.SNOW_BLOCK) && !blockState.isOf(Blocks.ICE)) continue;
                    this.setBlockState(structureWorldAccess, blockPos.add(m, -k, n), Blocks.CALCITE.getDefaultState());
                }
            }
        }
        k = tip_width - 1;
        if (k < 0) {
            k = 0;
        } else if (k > 1) {
            k = 1;
        }
        for (int o = -k; o <= k; ++o) {
            for (l = -k; l <= k; ++l) {
                BlockState blockState2;
                BlockPos blockPos2 = blockPos.add(o, -1, l);
                int p = 50;
                if (Math.abs(o) == 1 && Math.abs(l) == 1) {
                    p = random.nextInt(5);
                }
                while (blockPos2.getY() > 50 && ((blockState2 = structureWorldAccess.getBlockState(blockPos2)).isAir() || IceSpikeFeature.isSoil(blockState2) || blockState2.isOf(Blocks.CALCITE))) {
                    this.setBlockState(structureWorldAccess, blockPos2, Blocks.CALCITE.getDefaultState());
                    blockPos2 = blockPos2.down();
                    if (--p > 0) continue;
                    blockPos2 = blockPos2.down(random.nextInt(3) + 1);
                    p = random.nextInt(5);
                }
            }
        }
        return true;
    }
}

