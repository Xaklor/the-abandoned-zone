package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum AbandonedZoneMaterials implements ToolMaterial {
    SCRAP_MATERIAL(4, 1000, 6.0f, 3.0f, 14, Ingredient.ofItems(TheAbandonedZoneMod.INDUSTRIAL_SCRAP)),
    BRILLIANT_MATERIAL(5, 2031, 12.0f, 4.0f, 18, Ingredient.ofItems(TheAbandonedZoneMod.BRILLIANT_INGOT)),
    BLUE_SAPPHIRE(6, 4096, 17.0f, 5.0f, 22, Ingredient.ofItems(TheAbandonedZoneMod.BLUE_SAPPHIRE)),
    RED_SAPPHIRE(BLUE_SAPPHIRE, Ingredient.ofItems(TheAbandonedZoneMod.RED_SAPPHIRE)),
    YELLOW_SAPPHIRE(BLUE_SAPPHIRE, Ingredient.ofItems(TheAbandonedZoneMod.YELLOW_SAPPHIRE)),
    GREEN_SAPPHIRE(BLUE_SAPPHIRE, Ingredient.ofItems(TheAbandonedZoneMod.GREEN_SAPPHIRE)),
    CYAN_SAPPHIRE(BLUE_SAPPHIRE, Ingredient.ofItems(TheAbandonedZoneMod.CYAN_SAPPHIRE)),
    PURPLE_SAPPHIRE(BLUE_SAPPHIRE, Ingredient.ofItems(TheAbandonedZoneMod.PURPLE_SAPPHIRE)),
    PINK_SAPPHIRE(BLUE_SAPPHIRE, Ingredient.ofItems(TheAbandonedZoneMod.PINK_SAPPHIRE)),
    METEORITE_MATERIAL(7, 8192, 17.0f, 24.0f, 22, Ingredient.ofItems(TheAbandonedZoneMod.METEORITE_CHUNK));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Ingredient repairIngredient;

    AbandonedZoneMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    AbandonedZoneMaterials(AbandonedZoneMaterials inherits, Ingredient ingredient) {
        this.miningLevel = inherits.getMiningLevel();
        this.itemDurability = inherits.getDurability();
        this.miningSpeed = inherits.getMiningSpeedMultiplier();
        this.attackDamage = inherits.getAttackDamage();
        this.enchantability = inherits.getEnchantability();
        this.repairIngredient = ingredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
}