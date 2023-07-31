package com.xaklor.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

/**
 * Overwrites the default enchantment behavior to allow for more enchant customization
 */
@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperCompatMixin {

    @Inject(method = "getPossibleEntries", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"),
            locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void modifiedListBuilder(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir, List<EnchantmentLevelEntry> list, Item item) {
        boolean bl = stack.isOf(Items.BOOK);
        outer: for (Enchantment enchantment : Registries.ENCHANTMENT) {
            if (enchantment.isTreasure() && !treasureAllowed || !enchantment.isAvailableForRandomSelection() ||
                    !enchantment.isAcceptableItem(stack) && !bl)
                continue;
            for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                if (power < enchantment.getMinPower(i) || power > enchantment.getMaxPower(i))
                    continue;
                list.add(new EnchantmentLevelEntry(enchantment, i));
                continue outer;
            }
        }
        cir.setReturnValue(list);
    }

}