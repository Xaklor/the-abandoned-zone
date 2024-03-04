
/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import com.xaklor.util.sculkchest.SculkChest;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LidOpenable;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.model.SpriteAtlasManager;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;

@Environment(value=EnvType.CLIENT)
public class SculkChestRenderer<T extends BlockEntity>
        implements BlockEntityRenderer<T> {
    private static final String BASE = "bottom";
    private static final String LID = "lid";
    private static final String LATCH = "lock";
    private final ModelPart lid;
    private final ModelPart base;
    private final ModelPart latch;

    public SculkChestRenderer(BlockEntityRendererFactory.Context ctx) {
        ModelPart modelPart = ctx.getLayerModelPart(EntityModelLayers.CHEST);
        this.base = modelPart.getChild(BASE);
        this.lid = modelPart.getChild(LID);
        this.latch = modelPart.getChild(LATCH);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(BASE, ModelPartBuilder.create().uv(0, 19).cuboid(1.0f, 0.0f, 1.0f, 14.0f, 10.0f, 14.0f), ModelTransform.NONE);
        modelPartData.addChild(LID, ModelPartBuilder.create().uv(0, 0).cuboid(1.0f, 0.0f, 0.0f, 14.0f, 5.0f, 14.0f), ModelTransform.pivot(0.0f, 9.0f, 1.0f));
        modelPartData.addChild(LATCH, ModelPartBuilder.create().uv(0, 0).cuboid(7.0f, -2.0f, 14.0f, 2.0f, 4.0f, 1.0f), ModelTransform.pivot(0.0f, 9.0f, 1.0f));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        boolean bl = world != null;
        BlockState blockState = bl ? entity.getCachedState() : TheAbandonedZoneMod.SCULK_CHEST.getDefaultState().with((Property) SculkChest.FACING, (Comparable)Direction.SOUTH);
        Block block = blockState.getBlock();
        if (!(block instanceof AbstractChestBlock)) {
            return;
        }
        AbstractChestBlock abstractChestBlock = (AbstractChestBlock)block;
        matrices.push();
        float f = ((Direction)blockState.get((Property)ChestBlock.FACING)).asRotation();
        matrices.translate(0.5f, 0.5f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-f));
        matrices.translate(-0.5f, -0.5f, -0.5f);
        DoubleBlockProperties.PropertySource propertySource = bl ? abstractChestBlock.getBlockEntitySource(blockState, world, entity.getPos(), true) : DoubleBlockProperties.PropertyRetriever::getFallback;
        float g = ((Float2FloatFunction)propertySource.apply(ChestBlock.getAnimationProgressRetriever(((LidOpenable)entity)))).get(tickDelta);
        g = 1.0f - g;
        g = 1.0f - g * g * g;
        int i = ((Int2IntFunction)propertySource.apply(new LightmapCoordinatesRetriever())).applyAsInt(light);
        // SpriteIdentifier spriteIdentifier = TexturedRenderLayers.getChestTextureId(entity, chestType, this.christmas);
        SpriteIdentifier spriteIdentifier = new SpriteIdentifier(new Identifier("textures/atlas/chest.png"), new Identifier("entity/chest/sculk"));
        // SpriteIdentifier test = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("textures/entity/sculk_chest/sculk_chest.png"));
        // new Identifier("textures/entity/allay/allay.png")
        VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
        this.render(matrices, vertexConsumer, this.lid, this.latch, this.base, g, i, overlay);
        matrices.pop();
    }

    private void render(MatrixStack matrices, VertexConsumer vertices, ModelPart lid, ModelPart latch, ModelPart base, float openFactor, int light, int overlay) {
        latch.pitch = lid.pitch = -(openFactor * 1.5707964f);
        lid.render(matrices, vertices, light, overlay);
        latch.render(matrices, vertices, light, overlay);
        base.render(matrices, vertices, light, overlay);
    }
}
