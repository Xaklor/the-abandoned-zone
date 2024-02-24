package com.xaklor;

import com.xaklor.util.general.AbandonedZoneBlock;
import com.xaklor.util.DisintegratorScreen;
import com.xaklor.util.AlchemyBoxScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class TheAbandonedZoneModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		// allows for translucent blocks
		TheAbandonedZoneMod.BLOCKS.forEach((b) -> {
			if (b.type == AbandonedZoneBlock.BlockType.TRANSPARENT)
				BlockRenderLayerMap.INSTANCE.putBlock(b.block, RenderLayer.getTranslucent());
		});
		// BlockRenderLayerMap.INSTANCE.putBlock(TheAbandonedZoneMod.ASSEMBLER_ARMS, RenderLayer.getCutout());

		// screen registrations
		HandledScreens.register(TheAbandonedZoneMod.DISINTEGRATOR_SCREEN_HANDLER, DisintegratorScreen::new);
		HandledScreens.register(TheAbandonedZoneMod.ALCHEMY_BOX_SCREEN_HANDLER, AlchemyBoxScreen::new);
	}
}