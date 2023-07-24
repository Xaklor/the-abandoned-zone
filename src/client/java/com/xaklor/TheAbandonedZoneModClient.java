package com.xaklor;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TheAbandonedZoneModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		// this allows for a translucent block
		BlockRenderLayerMap.INSTANCE.putBlock(TheAbandonedZoneMod.CRYSTAL_STONE, RenderLayer.getTranslucent());
	}
}