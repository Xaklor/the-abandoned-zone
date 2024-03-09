package com.xaklor.mobs;

import com.xaklor.util.mobs.ZombieKingEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;

public class ZombieKingEntityModel<T extends ZombieKingEntity> extends DrownedEntityModel<T> {
    private static final String CLOAK = "cloak";
    private final ModelPart cloak;


    public ZombieKingEntityModel(ModelPart root) {
        super(root);
        this.cloak = root.getChild(CLOAK);
    }

    public void renderCape(MatrixStack matrices, VertexConsumer vertices, int light, int overlay) {
        this.cloak.render(matrices, vertices, light, overlay);
    }

    public static TexturedModelData getTexturedModelData(Dilation dilation) {
        ModelData modelData = DrownedEntityModel.getModelData(dilation, 0.0f);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(CLOAK, ModelPartBuilder.create().uv(0, 0).cuboid(-5.0f, 0.0f, -1.0f, 10.0f, 16.0f, 1.0f, dilation, 1.0f, 0.5f), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getArmorTexturedModelData(Dilation dilation) {
        ModelData modelData = ArmorEntityModel.getModelData(dilation);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(CLOAK, ModelPartBuilder.create().uv(0, 0).cuboid(-5.0f, 0.0f, -1.0f, 10.0f, 16.0f, 1.0f, dilation, 1.0f, 0.5f), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
        return TexturedModelData.of(modelData, 64, 32);
    }
}
