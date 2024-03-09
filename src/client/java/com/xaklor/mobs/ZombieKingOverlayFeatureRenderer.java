/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.xaklor.mobs;

import com.xaklor.TheAbandonedZoneMod;
import com.xaklor.util.mobs.ZombieKingEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

@Environment(value=EnvType.CLIENT)
public class ZombieKingOverlayFeatureRenderer<T extends ZombieKingEntity>
        extends FeatureRenderer<T, ZombieKingEntityModel<T>> {
    private static final Identifier SKIN = new Identifier(TheAbandonedZoneMod.MOD_ID, "textures/entity/zombie_king/zombie_king_outer_layer.png");
    private final DrownedEntityModel<T> model;

    public ZombieKingOverlayFeatureRenderer(FeatureRendererContext<T, ZombieKingEntityModel<T>> context, EntityModelLoader loader) {
        super(context);
        this.model = new DrownedEntityModel<>(loader.getModelPart(EntityModelLayers.DROWNED_OUTER));
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T zombieKingEntity, float f, float g, float h, float j, float k, float l) {
        // crown
        ZombieKingOverlayFeatureRenderer.render(this.getContextModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, i, zombieKingEntity, f, g, j, k, l, h, 1.0f, 1.0f, 1.0f);

        // cape
        if (zombieKingEntity.isInvisible()) {
            return;
        }
        matrixStack.push();
        matrixStack.translate(0.0f, 0.0f, 0.125f);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(10));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(new Identifier(TheAbandonedZoneMod.MOD_ID, "textures/entity/zombie_king/zombie_king_cape.png")));
        this.getContextModel().renderCape(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();
    }
}

