package com.xaklor.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.xaklor.TheAbandonedZoneMod.COMPATIBLE_ENCHANT_RULES;
import static com.xaklor.TheAbandonedZoneMod.INCOMPATIBLE_ENCHANT_RULES;

/**
 * Allows AbandonedZoneItem to provide enchantment overrides for specific items
 */
@Mixin(Enchantment.class)
public class EnchantmentCompatMixin {

    @Inject(method = "isAcceptableItem", at = @At("TAIL"), cancellable = true)
    private void makeItemCompatible(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Enchantment self = (Enchantment) (Object) this;
        Item item = stack.getItem();
        if (INCOMPATIBLE_ENCHANT_RULES.containsKey(item) && INCOMPATIBLE_ENCHANT_RULES.get(item).contains(self))
            cir.setReturnValue(false);
        if (COMPATIBLE_ENCHANT_RULES.containsKey(item) && COMPATIBLE_ENCHANT_RULES.get(item).contains(self))
            cir.setReturnValue(true);
    }

}