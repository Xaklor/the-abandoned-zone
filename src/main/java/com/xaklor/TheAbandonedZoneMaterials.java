package com.xaklor;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

class ScrapMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 1000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 6.0f;
    }

    @Override
    public float getAttackDamage() {
        return 3;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 14;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.INDUSTRIAL_SCRAP.block);
    }
}

class BrilliantMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 2031;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 12.0f;
    }

    @Override
    public float getAttackDamage() {
        return 4;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 18;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.BRILLIANT_INGOT);
    }
}

class BlueSapphireMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 4096;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 17.0f;
    }

    @Override
    public float getAttackDamage() {
        return 5;
    }

    @Override
    public int getMiningLevel() {
        return 6;
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.BLUE_SAPPHIRE);
    }
}

class RedSapphireMaterial extends BlueSapphireMaterial {
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.RED_SAPPHIRE);
    }
}

class YellowSapphireMaterial extends BlueSapphireMaterial {
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.YELLOW_SAPPHIRE);
    }
}

class GreenSapphireMaterial extends BlueSapphireMaterial {
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.GREEN_SAPPHIRE);
    }
}

class CyanSapphireMaterial extends BlueSapphireMaterial {
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.CYAN_SAPPHIRE);
    }
}

class PurpleSapphireMaterial extends BlueSapphireMaterial {
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.PURPLE_SAPPHIRE);
    }
}

class PinkSapphireMaterial extends BlueSapphireMaterial {
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.PINK_SAPPHIRE);
    }
}

class MeteoriteMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 8192;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 17.0f;
    }

    @Override
    public float getAttackDamage() {
        return 24;
    }

    @Override
    public int getMiningLevel() {
        return 7;
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(TheAbandonedZoneMod.METEORITE_CHUNK.block);
    }
}