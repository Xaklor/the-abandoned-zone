package com.xaklor;

import com.xaklor.mobs.SteelSkeletonEntityRenderer;
import com.xaklor.mobs.ZombieKingEntityModel;
import com.xaklor.mobs.ZombieKingEntityRenderer;
import com.xaklor.util.*;
import com.xaklor.util.general.AbandonedZoneBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.util.Identifier;

import static com.xaklor.TheAbandonedZoneMod.SCULK_CHEST_ENTITY;

public class TheAbandonedZoneModClient implements ClientModInitializer {
	public static final EntityModelLayer STEEL_SKELETON_MODEL = new EntityModelLayer(new Identifier("the_abandoned_zone", "steel_skeleton"), "main");
	public static final EntityModelLayer STEEL_SKELETON_INNER_ARMOR = new EntityModelLayer(new Identifier("the_abandoned_zone", "steel_skeleton"), "inner_armor");
	public static final EntityModelLayer STEEL_SKELETON_OUTER_ARMOR = new EntityModelLayer(new Identifier("the_abandoned_zone", "steel_skeleton"), "outer_armor");

	public static final EntityModelLayer ZOMBIE_KING_MODEL = new EntityModelLayer(new Identifier("the_abandoned_zone", "zombie_king"), "main");
	public static final EntityModelLayer ZOMBIE_KING_INNER_ARMOR = new EntityModelLayer(new Identifier("the_abandoned_zone", "zombie_king"), "inner_armor");
	public static final EntityModelLayer ZOMBIE_KING_OUTER_ARMOR = new EntityModelLayer(new Identifier("the_abandoned_zone", "zombie_king"), "outer_armor");

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		// allows for translucent blocks
		TheAbandonedZoneMod.BLOCKS.forEach((b) -> {
			if (b.type == AbandonedZoneBlock.BlockType.TRANSPARENT)
				BlockRenderLayerMap.INSTANCE.putBlock(b.block, RenderLayer.getTranslucent());
		});
		BlockRenderLayerMap.INSTANCE.putBlock(TheAbandonedZoneMod.ABANDONED_PORTAL, RenderLayer.getTranslucent());
		// BlockRenderLayerMap.INSTANCE.putBlock(TheAbandonedZoneMod.ASSEMBLER_ARMS, RenderLayer.getCutout());

		BlockEntityRendererRegistry.register(SCULK_CHEST_ENTITY, SculkChestRenderer::new);

		// see EntityModels for vanilla models
		EntityRendererRegistry.register(TheAbandonedZoneMod.STEEL_SKELETON, SteelSkeletonEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(STEEL_SKELETON_MODEL, SkeletonEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(STEEL_SKELETON_INNER_ARMOR, () -> TexturedModelData.of(ArmorEntityModel.getModelData(new Dilation(0.5f)), 64, 32));
		EntityModelLayerRegistry.registerModelLayer(STEEL_SKELETON_OUTER_ARMOR, () -> TexturedModelData.of(ArmorEntityModel.getModelData(new Dilation(1.0f)), 64, 32));

		EntityRendererRegistry.register(TheAbandonedZoneMod.ZOMBIE_KING, ZombieKingEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ZOMBIE_KING_MODEL, () -> ZombieKingEntityModel.getTexturedModelData(new Dilation(0.0f)));
		EntityModelLayerRegistry.registerModelLayer(ZOMBIE_KING_INNER_ARMOR, () -> ZombieKingEntityModel.getArmorTexturedModelData(new Dilation(0.5f)));
		EntityModelLayerRegistry.registerModelLayer(ZOMBIE_KING_OUTER_ARMOR, () -> ZombieKingEntityModel.getArmorTexturedModelData(new Dilation(1.0f)));

		// screen registrations
		HandledScreens.register(TheAbandonedZoneMod.ALCHEMY_BOX_SCREEN_HANDLER, AlchemyBoxScreen::new);
		HandledScreens.register(TheAbandonedZoneMod.ASSEMBLER_SCREEN_HANDLER, AssemblerScreen::new);
		HandledScreens.register(TheAbandonedZoneMod.DISINTEGRATOR_SCREEN_HANDLER, DisintegratorScreen::new);
	}
}