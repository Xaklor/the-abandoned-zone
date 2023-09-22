package com.xaklor.util.general;

import com.xaklor.TheAbandonedZoneMod;
import com.xaklor.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AbandonedZonePotions {

    public static final Potion WITHER_POTION =
            Registry.register(Registries.POTION, new Identifier("the_abandoned_zone", "wither_potion"),
                    new Potion(new StatusEffectInstance(StatusEffects.WITHER, 3600, 0)));
    public static final Potion WITHER_POTION_EXTENDED =
            Registry.register(Registries.POTION, new Identifier("the_abandoned_zone", "wither_potion_extended"),
                    new Potion(new StatusEffectInstance(StatusEffects.WITHER, 9600, 0)));
    public static final Potion WITHER_POTION_ENHANCED =
            Registry.register(Registries.POTION, new Identifier("the_abandoned_zone", "wither_potion_enhanced"),
                    new Potion(new StatusEffectInstance(StatusEffects.WITHER, 1800, 1)));
    public static final Potion HASTE_POTION =
            Registry.register(Registries.POTION, new Identifier("the_abandoned_zone", "haste_potion"),
                    new Potion(new StatusEffectInstance(StatusEffects.HASTE, 3600, 0)));
    public static final Potion HASTE_POTION_EXTENDED =
            Registry.register(Registries.POTION, new Identifier("the_abandoned_zone", "haste_potion_extended"),
                    new Potion(new StatusEffectInstance(StatusEffects.HASTE, 9600, 0)));
    public static final Potion HASTE_POTION_ENHANCED =
            Registry.register(Registries.POTION, new Identifier("the_abandoned_zone", "haste_potion_enhanced"),
                    new Potion(new StatusEffectInstance(StatusEffects.HASTE, 1800, 1)));

    public static void registerPotionsRecipes(){
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.THICK, TheAbandonedZoneMod.COAL_DUST, WITHER_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(WITHER_POTION, Items.REDSTONE, WITHER_POTION_EXTENDED);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(WITHER_POTION, Items.GLOWSTONE_DUST, WITHER_POTION_ENHANCED);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.THICK, TheAbandonedZoneMod.GEMSTONE_BLEND, HASTE_POTION);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(HASTE_POTION,  Items.REDSTONE, HASTE_POTION_EXTENDED);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(HASTE_POTION,  Items.GLOWSTONE_DUST, HASTE_POTION_ENHANCED);
    }
}
