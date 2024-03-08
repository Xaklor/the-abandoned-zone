package com.xaklor.mobs;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.Identifier;

public class SteelSkeletonEntityRenderer extends SkeletonEntityRenderer {

    private static final Identifier TEXTURE = new Identifier(TheAbandonedZoneMod.MOD_ID, "textures/entity/steel_skeleton/steel_skeleton.png");

    public SteelSkeletonEntityRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.WITHER_SKELETON, EntityModelLayers.WITHER_SKELETON_INNER_ARMOR, EntityModelLayers.WITHER_SKELETON_OUTER_ARMOR);
    }

    @Override
    public Identifier getTexture(AbstractSkeletonEntity abstractSkeletonEntity) {
        return TEXTURE;
    }

    @Override
    protected void scale(AbstractSkeletonEntity abstractSkeletonEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.2f, 1.2f, 1.2f);
    }
}
