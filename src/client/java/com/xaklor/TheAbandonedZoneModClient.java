package com.xaklor;

import com.xaklor.util.AbandonedZoneBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TheAbandonedZoneModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		// this allows for a translucent block
		TheAbandonedZoneMod.REGISTERABLE.forEach((r) -> {
			// can definitely be cleaned up but lazy
			if (r instanceof AbandonedZoneBlock b && b.type == AbandonedZoneBlock.BlockType.TRANSPARENT)
				BlockRenderLayerMap.INSTANCE.putBlock(b.block, RenderLayer.getTranslucent());
		});
	}
}