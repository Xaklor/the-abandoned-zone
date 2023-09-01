package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class AlchemyBoxRecipes {
    private ArrayList<Item[]> recipeList;

    public AlchemyBoxRecipes() {
        recipeList = new ArrayList<>() {
            {
                add(new Item[]{ Items.OAK_LOG, Items.BLACK_DYE, Items.DARK_OAK_LOG });
                add(new Item[]{ Items.OAK_LOG, Items.WHITE_DYE, Items.BIRCH_LOG });
                add(new Item[]{ Items.OAK_LOG, Items.SNOWBALL, Items.SPRUCE_LOG });
                add(new Item[]{ Items.OAK_LOG, Items.ORANGE_DYE, Items.ACACIA_LOG });
                add(new Item[]{ Items.OAK_LOG, Items.RED_DYE, Items.MANGROVE_LOG });
                add(new Item[]{ Items.OAK_LOG, Items.PINK_DYE, Items.CHERRY_LOG });
                add(new Item[]{ Items.WARPED_STEM, Items.NETHER_WART, Items.CRIMSON_STEM });
                add(new Item[]{ Items.CRIMSON_STEM, Items.NETHER_SPROUTS, Items.WARPED_STEM });
                add(new Item[]{ Items.GLOWSTONE, Items.KELP, Items.SEA_LANTERN });
                add(new Item[]{ Items.SAND, Items.ROTTEN_FLESH, Items.RED_SAND });
                add(new Item[]{ Items.RED_SAND, Items.WATER_BUCKET, Items.SAND });
                add(new Item[]{ Items.STONE_BRICKS, Items.NETHER_WART, Items.RED_NETHER_BRICKS });
                add(new Item[]{ Items.GOLD_INGOT, Items.BLACKSTONE, Items.GILDED_BLACKSTONE });
                add(new Item[]{ Items.ICE, Items.BLUE_TERRACOTTA, Items.BLUE_ICE });
                add(new Item[]{ Items.NETHERRACK, Items.NETHER_WART, Items.CRIMSON_NYLIUM });
                add(new Item[]{ Items.NETHERRACK, Items.NETHER_SPROUTS, Items.WARPED_NYLIUM });
                add(new Item[]{ Items.NETHERRACK, Items.SAND, Items.SOUL_SAND });
                add(new Item[]{ Items.COAL_BLOCK, Items.STONE, Items.COAL_ORE });
                add(new Item[]{ Items.COAL_BLOCK, Items.DEEPSLATE, Items.DEEPSLATE_COAL_ORE });
                add(new Item[]{ Items.IRON_BLOCK, Items.STONE, Items.IRON_ORE });
                add(new Item[]{ Items.IRON_BLOCK, Items.DEEPSLATE, Items.DEEPSLATE_IRON_ORE });
                add(new Item[]{ Items.COPPER_BLOCK, Items.STONE, Items.COPPER_ORE });
                add(new Item[]{ Items.COPPER_BLOCK, Items.DEEPSLATE, Items.DEEPSLATE_COPPER_ORE });
                add(new Item[]{ Items.GOLD_BLOCK, Items.STONE, Items.GOLD_ORE });
                add(new Item[]{ Items.GOLD_BLOCK, Items.DEEPSLATE, Items.DEEPSLATE_GOLD_ORE });
                add(new Item[]{ Items.GOLD_BLOCK, Items.NETHERRACK, Items.NETHER_GOLD_ORE });
                add(new Item[]{ Items.REDSTONE_BLOCK, Items.STONE, Items.REDSTONE_ORE });
                add(new Item[]{ Items.REDSTONE_BLOCK, Items.DEEPSLATE, Items.DEEPSLATE_REDSTONE_ORE });
                add(new Item[]{ Items.EMERALD_BLOCK, Items.STONE, Items.EMERALD_ORE });
                add(new Item[]{ Items.EMERALD_BLOCK, Items.DEEPSLATE, Items.DEEPSLATE_EMERALD_ORE });
                add(new Item[]{ Items.LAPIS_BLOCK, Items.STONE, Items.LAPIS_ORE });
                add(new Item[]{ Items.LAPIS_BLOCK, Items.DEEPSLATE, Items.DEEPSLATE_LAPIS_ORE });
                add(new Item[]{ Items.DIAMOND_BLOCK, Items.STONE, Items.DIAMOND_ORE });
                add(new Item[]{ Items.DIAMOND_BLOCK, Items.DEEPSLATE, Items.DEEPSLATE_DIAMOND_ORE });
                add(new Item[]{ Items.QUARTZ_BLOCK, Items.NETHERRACK, Items.NETHER_QUARTZ_ORE });
                add(new Item[]{ TheAbandonedZoneMod.MIGHT_BLOCK.item, TheAbandonedZoneMod.INFERNAL_DUST, Items.ANCIENT_DEBRIS });
                add(new Item[]{ Items.IRON_BLOCK, TheAbandonedZoneMod.SOIL_DUST, Items.RAW_IRON_BLOCK });
                add(new Item[]{ Items.COPPER_BLOCK, TheAbandonedZoneMod.SOIL_DUST, Items.RAW_COPPER_BLOCK });
                add(new Item[]{ Items.GOLD_BLOCK, TheAbandonedZoneMod.SOIL_DUST, Items.RAW_GOLD_BLOCK });
                add(new Item[]{ Items.SEA_LANTERN, TheAbandonedZoneMod.INFERNAL_DUST, Items.GLOWSTONE });
                add(new Item[]{ Items.AMETHYST_BLOCK, TheAbandonedZoneMod.ROYAL_HEART, Items.BUDDING_AMETHYST });
                add(new Item[]{ Items.GLOWSTONE, TheAbandonedZoneMod.FUNGAL_DUST, Items.SHROOMLIGHT });
                add(new Item[]{ Items.SEA_LANTERN, TheAbandonedZoneMod.FUNGAL_DUST, Items.SHROOMLIGHT });
                add(new Item[]{ Items.RED_MUSHROOM, TheAbandonedZoneMod.INFERNAL_DUST, Items.CRIMSON_FUNGUS });
                add(new Item[]{ Items.BROWN_MUSHROOM, TheAbandonedZoneMod.INFERNAL_DUST, Items.WARPED_FUNGUS });
                add(new Item[]{ Items.GRASS, Items.YELLOW_DYE, Items.DANDELION });
                add(new Item[]{ Items.GRASS, Items.RED_DYE, Items.POPPY });
                add(new Item[]{ Items.GRASS, Items.LIGHT_BLUE_DYE, Items.BLUE_ORCHID });
                add(new Item[]{ Items.GRASS, Items.MAGENTA_DYE, Items.ALLIUM });
                add(new Item[]{ Items.GRASS, Items.LIGHT_GRAY_DYE, Items.AZURE_BLUET });
                add(new Item[]{ Items.GRASS, Items.WHITE_DYE, Items.OXEYE_DAISY });
                add(new Item[]{ Items.GRASS, Items.BLUE_DYE, Items.CORNFLOWER });
                add(new Item[]{ Items.GRASS, Items.GRAY_DYE, Items.LILY_OF_THE_VALLEY });
                add(new Item[]{ Items.GRASS, Items.BLACK_DYE, Items.WITHER_ROSE });
                add(new Item[]{ Items.WHEAT_SEEDS, Items.RED_DYE, Items.RED_TULIP });
                add(new Item[]{ Items.WHEAT_SEEDS, Items.ORANGE_DYE, Items.ORANGE_TULIP });
                add(new Item[]{ Items.WHEAT_SEEDS, Items.LIGHT_GRAY_DYE, Items.WHITE_TULIP });
                add(new Item[]{ Items.WHEAT_SEEDS, Items.PINK_DYE, Items.PINK_TULIP });
                add(new Item[]{ Items.TALL_GRASS, Items.YELLOW_DYE, Items.SUNFLOWER });
                add(new Item[]{ Items.TALL_GRASS, Items.MAGENTA_DYE, Items.LILAC });
                add(new Item[]{ Items.TALL_GRASS, Items.RED_DYE, Items.ROSE_BUSH });
                add(new Item[]{ Items.TALL_GRASS, Items.PINK_DYE, Items.PEONY });
                add(new Item[]{ Items.OXEYE_DAISY, Items.CHORUS_FRUIT, Items.CHORUS_FLOWER });
                add(new Item[]{ Items.VINE, Items.TORCH, Items.GLOW_LICHEN });
                add(new Item[]{ Items.TURTLE_EGG, Items.TORCHFLOWER_SEEDS, Items.SNIFFER_EGG });
                add(new Item[]{ Items.EGG, Items.SEAGRASS, Items.TURTLE_EGG });
                add(new Item[]{ Items.SWEET_BERRIES, Items.TORCH, Items.GLOW_BERRIES });
                add(new Item[]{ Items.KELP, Items.WHITE_CARPET, Items.LILY_PAD });
                add(new Item[]{ Items.KELP, Items.CANDLE, Items.SEA_PICKLE });
                add(new Item[]{ Items.DRIED_KELP_BLOCK, Items.PRISMARINE_CRYSTALS, Items.SPONGE });
                add(new Item[]{ Items.HONEY_BLOCK, Items.SLIME_BALL, Items.SLIME_BLOCK });
                add(new Item[]{ Items.SLIME_BLOCK, Items.HONEYCOMB, Items.HONEY_BLOCK });
                add(new Item[]{ Items.STRING, Items.WHITE_WOOL, Items.COBWEB });
                add(new Item[]{ Items.GLASS, TheAbandonedZoneMod.MYSTIC_DUST, Items.END_CRYSTAL });
                add(new Item[]{ Items.GOLD_BLOCK, Items.SCULK_SHRIEKER, Items.BELL });
                add(new Item[]{ Items.DIAMOND_SWORD, TheAbandonedZoneMod.MIGHT_BLOCK.item, Items.TRIDENT });
                add(new Item[]{ Items.TURTLE_EGG, Items.SEAGRASS, Items.SCUTE });
                add(new Item[]{ Items.GOLDEN_APPLE, TheAbandonedZoneMod.ALCHEMIC_BRICKS.item, Items.ENCHANTED_GOLDEN_APPLE });
                add(new Item[]{ Items.POTATO, Items.SPIDER_EYE, Items.POISONOUS_POTATO });
                add(new Item[]{ Items.GLASS_BOTTLE, Items.SCULK, Items.EXPERIENCE_BOTTLE });
                add(new Item[]{ Items.COBBLESTONE, TheAbandonedZoneMod.MYSTIC_DUST, Items.END_STONE });
                add(new Item[]{ Items.GHAST_TEAR, TheAbandonedZoneMod.ROYAL_HEART, TheAbandonedZoneMod.SPIRIT_NEWBORN });
                add(new Item[]{ TheAbandonedZoneMod.METAL_MATRIX.item, TheAbandonedZoneMod.RARE_DUST, TheAbandonedZoneMod.ALCHEMIC_BRICKS.item });
            }
        };
    }

    public void AddRecipe(Item input1, Item input2, Item output) {
        recipeList.add(new Item[] { input1, input2, output });
    }

    public Item LookupRecipe(Item input1, Item input2) {
        for (Item[] recipe : recipeList) {
            if ((recipe[0] == input1 && recipe[1] == input2) || (recipe[0] == input2 && recipe[1] == input1))
                return recipe[2];
        }
        return null;
    }
}
