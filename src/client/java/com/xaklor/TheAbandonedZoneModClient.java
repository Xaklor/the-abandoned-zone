package com.xaklor;

import com.xaklor.util.AbandonedZoneBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
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
		BlockRenderLayerMap.INSTANCE.putBlock(TheAbandonedZoneMod.ASSEMBLER_ARMS, RenderLayer.getCutout());
	}
}