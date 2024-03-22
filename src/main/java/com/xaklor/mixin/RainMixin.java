package com.xaklor.mixin;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * forces weather events for specific biomes
 */
@Mixin(World.class)
public class RainMixin {
    @Inject(at = @At("HEAD"), method = "hasRain", cancellable = true)
    private void forceRain (BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        // info.setReturnValue(true);
        RegistryEntry<Biome> test = ((World) (Object) this).getBiome(pos);
        Identifier x = test.getKey().get().getValue();
        TheAbandonedZoneMod.LOGGER.info(x.toString());
        // logs "minecraft:plains"
    }
}
