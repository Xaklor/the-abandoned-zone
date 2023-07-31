package com.xaklor;

import com.xaklor.util.AbandonedZoneBlock.BlockType;
import com.xaklor.util.TheAbandonedZoneTool.ToolType;
import com.xaklor.util.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TheAbandonedZoneMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "the_abandoned_zone";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final List<Item> ITEMS = new ArrayList<>();
	public static final List<AbandonedZoneBlock> BLOCKS = new ArrayList<>();

	//region ITEMS
	public static final Item AMETHYST_DUST = new AbandonedZoneItem("amethyst_dust", new FabricItemSettings());
	public static final Item AMULET_OF_GREED = new AbandonedZoneItem("amulet_of_greed", new FabricItemSettings());
	public static final Item BENEVOLENT_SPIRIT = new AbandonedZoneItem("benevolent_spirit", new FabricItemSettings());
	public static final Item BLUE_SAPPHIRE = new AbandonedZoneItem("blue_sapphire", new FabricItemSettings());
	public static final Item BRILLIANT_INGOT = new AbandonedZoneItem("brilliant_ingot", new FabricItemSettings());
	public static final Item BRILLIANT_NUGGET = new AbandonedZoneItem("brilliant_nugget", new FabricItemSettings());
	public static final Item COAL_DUST = new AbandonedZoneItem("coal_dust", new FabricItemSettings());
	public static final Item COLORFUL_DUST = new AbandonedZoneItem("colorful_dust", new FabricItemSettings());
	public static final Item CORPSE_DUST = new AbandonedZoneItem("corpse_dust", new FabricItemSettings());
	public static final Item CYAN_SAPPHIRE = new AbandonedZoneItem("cyan_sapphire", new FabricItemSettings());
	public static final Item DIAMOND_DUST = new AbandonedZoneItem("diamond_dust", new FabricItemSettings());
	public static final Item DULL_ORE = new AbandonedZoneItem("dull_ore", new FabricItemSettings());
	public static final Item EMERALD_DUST = new AbandonedZoneItem("emerald_dust", new FabricItemSettings());
	public static final Item FLOUR = new AbandonedZoneItem("flour", new FabricItemSettings());
	public static final Item FOOD_DUST = new AbandonedZoneItem("food_dust", new FabricItemSettings());
	public static final Item FUEL_CANISTER = new AbandonedZoneItem("fuel_canister", new FabricItemSettings());
	public static final Item FUNGAL_DUST = new AbandonedZoneItem("fungal_dust", new FabricItemSettings());
	public static final Item GEMSTONE_BLEND = new AbandonedZoneItem("gemstone_blend", new FabricItemSettings());
	public static final Item GLASS_DUST = new AbandonedZoneItem("glass_dust", new FabricItemSettings());
	public static final Item GRASS_DUST = new AbandonedZoneItem("grass_dust", new FabricItemSettings());
	public static final Item GREEN_SAPPHIRE = new AbandonedZoneItem("green_sapphire", new FabricItemSettings());
	public static final Item HAMMER_HANDLE = new AbandonedZoneItem("hammer_handle", new FabricItemSettings());
	public static final Item HAMMER_HEAD = new AbandonedZoneItem("hammer_head", new FabricItemSettings());
	public static final Item INFERNAL_DUST = new AbandonedZoneItem("infernal_dust", new FabricItemSettings());
	public static final Item INSTANT_CAKE = new AbandonedZoneItem("instant_cake", new FabricItemSettings());
	public static final Item LAPIS_LAZULI_DUST = new AbandonedZoneItem("lapis_lazuli_dust", new FabricItemSettings());
	public static final Item MALEVOLENT_SPIRIT = new AbandonedZoneItem("malevolent_spirit", new FabricItemSettings());
	public static final Item METAL_DUST = new AbandonedZoneItem("metal_dust", new FabricItemSettings());
	public static final Item MYSTIC_DUST = new AbandonedZoneItem("mystic_dust", new FabricItemSettings());
	public static final Item NETHERITE_DUST = new AbandonedZoneItem("netherite_dust", new FabricItemSettings());
	public static final Item NUTRIENT_POWDER = new AbandonedZoneItem("nutrient_powder", new FabricItemSettings());
	public static final Item PINK_SAPPHIRE = new AbandonedZoneItem("pink_sapphire", new FabricItemSettings());
	public static final Item POWDERED_EGG = new AbandonedZoneItem("powdered_egg", new FabricItemSettings());
	public static final Item POWDERED_MILK	 = new AbandonedZoneItem("powdered_milk", new FabricItemSettings());
	public static final Item PURPLE_SAPPHIRE = new AbandonedZoneItem("purple_sapphire", new FabricItemSettings());
	public static final Item QUARTZ_DUST = new AbandonedZoneItem("quartz_dust", new FabricItemSettings());
	public static final Item RARE_DUST = new AbandonedZoneItem("rare_dust", new FabricItemSettings());
	public static final Item RED_SAPPHIRE = new AbandonedZoneItem("red_sapphire", new FabricItemSettings());
	public static final Item RESONATING_DUST = new AbandonedZoneItem("resonating_dust", new FabricItemSettings());
	public static final Item ROYAL_HEART = new AbandonedZoneItem("royal_heart", new FabricItemSettings());
	public static final Item SAPPHIRE_DUST = new AbandonedZoneItem("sapphire_dust", new FabricItemSettings());
	public static final Item SCULK_DUST = new AbandonedZoneItem("sculk_dust", new FabricItemSettings());
	public static final Item SOIL_DUST = new AbandonedZoneItem("soil_dust", new FabricItemSettings());
	public static final Item SPECTRAL_DUST = new AbandonedZoneItem("spectral_dust", new FabricItemSettings());
	public static final Item SPIRIT_NEWBORN = new AbandonedZoneItem("spirit_newborn", new FabricItemSettings());
	public static final Item STARUDST = new AbandonedZoneItem("stardust", new FabricItemSettings());
	public static final Item STEEL_BONE = new AbandonedZoneItem("steel_bone", new FabricItemSettings());
	public static final Item THE_ABANDONED_TOME = new AbandonedZoneItem("the_abandoned_tome", new FabricItemSettings());
	public static final Item WAXY_DUST = new AbandonedZoneItem("waxy_dust", new FabricItemSettings());
	public static final Item WISHING_PENDANT = new AbandonedZoneItem("wishing_pendant", new FabricItemSettings());
	public static final Item WOOD_DUST = new AbandonedZoneItem("wood_dust", new FabricItemSettings());
	public static final Item YELLOW_SAPPHIRE = new AbandonedZoneItem("yellow_sapphire", new FabricItemSettings());
	//endregion

	//region BLOCKS
	public static final AbandonedZoneBlock ALCHEMIC_BRICKS = new AbandonedZoneBlock("alchemic_bricks", FabricBlockSettings.create(), BlockType.NORMAL);
	public static final AbandonedZoneBlock BLACK_CRYSTAL_STONE = new AbandonedZoneBlock("black_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock BLUE_CRYSTAL_STONE = new AbandonedZoneBlock("blue_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final BrilliantBlock BRILLIANT_BLOCK = new BrilliantBlock(FabricBlockSettings.create());
	public static final AbandonedZoneBlock BRILLIANT_ORE = new AbandonedZoneBlock("brilliant_ore", FabricBlockSettings.create(), BlockType.NORMAL);
	public static final AbandonedZoneBlock BROWN_CRYSTAL_STONE = new AbandonedZoneBlock("brown_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock CRYSTAL_STONE = new AbandonedZoneBlock("crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock CYAN_CRYSTAL_STONE = new AbandonedZoneBlock("cyan_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock DULL_ORE_BLOCK = new AbandonedZoneBlock("dull_ore_block", FabricBlockSettings.create(), BlockType.NORMAL);
	public static final AbandonedZoneBlock GNEISS = new AbandonedZoneBlock("gneiss", FabricBlockSettings.create(), BlockType.PILLAR);
	public static final AbandonedZoneBlock GNEISS_BRILLIANT_ORE = new AbandonedZoneBlock("gneiss_brilliant_ore", FabricBlockSettings.create(), BlockType.PILLAR);
	public static final AbandonedZoneBlock GRAY_CRYSTAL_STONE = new AbandonedZoneBlock("gray_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock GREEN_CRYSTAL_STONE = new AbandonedZoneBlock("green_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock INDUSTRIAL_SCRAP = new AbandonedZoneBlock("industrial_scrap", FabricBlockSettings.create(), BlockType.NORMAL);
	public static final AbandonedZoneBlock LIGHT_BLUE_CRYSTAL_STONE = new AbandonedZoneBlock("light_blue_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock LIGHT_GRAY_CRYSTAL_STONE = new AbandonedZoneBlock("light_gray_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock LIME_CRYSTAL_STONE = new AbandonedZoneBlock("lime_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock MAGENTA_CRYSTAL_STONE = new AbandonedZoneBlock("magenta_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock METAL_MATRIX = new AbandonedZoneBlock("metal_matrix", FabricBlockSettings.create(), BlockType.NORMAL);
	public static final AbandonedZoneBlock METEORITE_CHUNK = new AbandonedZoneBlock("meteorite_chunk", FabricBlockSettings.create(), BlockType.NORMAL);
	public static final AbandonedZoneBlock MIGHT_BLOCK = new AbandonedZoneBlock("might_block", FabricBlockSettings.create(), BlockType.NORMAL);
	public static final AbandonedZoneBlock ORANGE_CRYSTAL_STONE = new AbandonedZoneBlock("orange_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock PURPLE_CRYSTAL_STONE = new AbandonedZoneBlock("purple_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock RED_CRYSTAL_STONE = new AbandonedZoneBlock("red_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock SAPPHIRE_ORE = new AbandonedZoneBlock("sapphire_ore", FabricBlockSettings.create(), BlockType.PILLAR);
	public static final AbandonedZoneBlock VOLCANIC_ASH = new AbandonedZoneBlock("volcanic_ash", FabricBlockSettings.create(), BlockType.NORMAL);
	public static final AbandonedZoneBlock WHITE_CRYSTAL_STONE = new AbandonedZoneBlock("white_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock YELLOW_CRYSTAL_STONE = new AbandonedZoneBlock("yellow_crystal_stone", FabricBlockSettings.create().nonOpaque(), BlockType.TRANSPARENT);

	//endregion

	//region TOOLS
	public static final TheAbandonedZoneTool BLUE_SAPPHIRE_PICK = new TheAbandonedZoneTool(new BlueSapphireMaterial(), 0, -2.8f, new Item.Settings(), "blue_sapphire_pick", ToolType.PICKAXE);
	public static final TheAbandonedZoneTool BRILLIANT_PICK = new TheAbandonedZoneTool(new BrilliantMaterial(), 0, -2.8f, new Item.Settings(), "brilliant_pick", ToolType.PICKAXE);
	public static final TheAbandonedZoneTool CYAN_SAPPHIRE_PICK = new TheAbandonedZoneTool(new CyanSapphireMaterial(), 0, -2.8f, new Item.Settings(), "cyan_sapphire_pick", ToolType.PICKAXE);
	public static final TheAbandonedZoneTool GREEN_SAPPHIRE_PICK = new TheAbandonedZoneTool(new GreenSapphireMaterial(), 0, -2.8f, new Item.Settings(), "green_sapphire_pick", ToolType.PICKAXE);
	public static final TheAbandonedZoneTool PINK_SAPPHIRE_PICK = new TheAbandonedZoneTool(new PinkSapphireMaterial(), 0, -2.8f, new Item.Settings(), "pink_sapphire_pick", ToolType.PICKAXE);
	public static final TheAbandonedZoneTool PURPLE_SAPPHIRE_PICK = new TheAbandonedZoneTool(new PurpleSapphireMaterial(), 0, -2.8f, new Item.Settings(), "purple_sapphire_pick", ToolType.PICKAXE);
	public static final TheAbandonedZoneTool RED_SAPPHIRE_PICK = new TheAbandonedZoneTool(new RedSapphireMaterial(), 0, -2.8f, new Item.Settings(), "red_sapphire_pick", ToolType.PICKAXE);
	public static final TheAbandonedZoneTool SCRAP_PICK = new TheAbandonedZoneTool(new ScrapMaterial(), 0, -2.8f, new Item.Settings(), "scrap_pick", ToolType.PICKAXE);
	public static final TheAbandonedZoneTool STAR_HAMMER = new TheAbandonedZoneTool(new MeteoriteMaterial(), 0, -2.4f, new Item.Settings(), "star_hammer", ToolType.SWORD);
	public static final TheAbandonedZoneTool YELLOW_SAPPHIRE_PICK = new TheAbandonedZoneTool(new YellowSapphireMaterial(), 0, -2.8f, new Item.Settings(), "yellow_sapphire_pick", ToolType.PICKAXE);
	//endregion

	// item group, this gives mod items their own creative inventory page
	private static final ItemGroup ALL_ITEMS = FabricItemGroup.builder()
			.icon(() -> new ItemStack(THE_ABANDONED_TOME))
			.displayName(Text.translatable("itemGroup.the_abandoned_zone.all_items"))
			.entries((context, entries) -> {
				entries.add(THE_ABANDONED_TOME);
				entries.add(GNEISS.item);
				entries.add(ALCHEMIC_BRICKS.item);
				entries.add(VOLCANIC_ASH.item);
				entries.add(CRYSTAL_STONE.item);
				entries.add(WHITE_CRYSTAL_STONE.item);
				entries.add(LIGHT_GRAY_CRYSTAL_STONE.item);
				entries.add(GRAY_CRYSTAL_STONE.item);
				entries.add(BLACK_CRYSTAL_STONE.item);
				entries.add(BROWN_CRYSTAL_STONE.item);
				entries.add(RED_CRYSTAL_STONE.item);
				entries.add(ORANGE_CRYSTAL_STONE.item);
				entries.add(YELLOW_CRYSTAL_STONE.item);
				entries.add(LIME_CRYSTAL_STONE.item);
				entries.add(GREEN_CRYSTAL_STONE.item);
				entries.add(CYAN_CRYSTAL_STONE.item);
				entries.add(LIGHT_BLUE_CRYSTAL_STONE.item);
				entries.add(BLUE_CRYSTAL_STONE.item);
				entries.add(PURPLE_CRYSTAL_STONE.item);
				entries.add(MAGENTA_CRYSTAL_STONE.item);
				entries.add(METAL_MATRIX.item);
				entries.add(MIGHT_BLOCK.item);
				entries.add(INDUSTRIAL_SCRAP.item);
				entries.add(BRILLIANT_ORE.item);
				entries.add(GNEISS_BRILLIANT_ORE.item);
				entries.add(DULL_ORE);
				entries.add(BRILLIANT_INGOT);
				entries.add(BRILLIANT_NUGGET);
				entries.add(BRILLIANT_BLOCK);
				entries.add(DULL_ORE_BLOCK.item);
				entries.add(SAPPHIRE_ORE.item);
				entries.add(BLUE_SAPPHIRE);
				entries.add(RED_SAPPHIRE);
				entries.add(YELLOW_SAPPHIRE);
				entries.add(GREEN_SAPPHIRE);
				entries.add(CYAN_SAPPHIRE);
				entries.add(PURPLE_SAPPHIRE);
				entries.add(PINK_SAPPHIRE);
				entries.add(METEORITE_CHUNK.item);
				entries.add(SCRAP_PICK.tool);
				entries.add(BRILLIANT_PICK.tool);
				entries.add(BLUE_SAPPHIRE_PICK.tool);
				entries.add(RED_SAPPHIRE_PICK.tool);
				entries.add(YELLOW_SAPPHIRE_PICK.tool);
				entries.add(GREEN_SAPPHIRE_PICK.tool);
				entries.add(CYAN_SAPPHIRE_PICK.tool);
				entries.add(PURPLE_SAPPHIRE_PICK.tool);
				entries.add(PINK_SAPPHIRE_PICK.tool);
				entries.add(HAMMER_HEAD);
				entries.add(HAMMER_HANDLE);
				entries.add(STAR_HAMMER.tool);
				entries.add(FUEL_CANISTER);
				entries.add(INSTANT_CAKE);
				entries.add(NUTRIENT_POWDER);
				entries.add(ROYAL_HEART);
				entries.add(STEEL_BONE);
				entries.add(SPIRIT_NEWBORN);
				entries.add(MALEVOLENT_SPIRIT);
				entries.add(BENEVOLENT_SPIRIT);
				entries.add(AMULET_OF_GREED);
				entries.add(WISHING_PENDANT);
				entries.add(WOOD_DUST);
				entries.add(GRASS_DUST);
				entries.add(SOIL_DUST);
				entries.add(FUNGAL_DUST);
				entries.add(INFERNAL_DUST);
				entries.add(MYSTIC_DUST);
				entries.add(GLASS_DUST);
				entries.add(WAXY_DUST);
				entries.add(SCULK_DUST);
				entries.add(COAL_DUST);
				entries.add(METAL_DUST);
				entries.add(EMERALD_DUST);
				entries.add(LAPIS_LAZULI_DUST);
				entries.add(DIAMOND_DUST);
				entries.add(NETHERITE_DUST);
				entries.add(AMETHYST_DUST);
				entries.add(QUARTZ_DUST);
				entries.add(POWDERED_EGG);
				entries.add(POWDERED_MILK);
				entries.add(FLOUR);
				entries.add(FOOD_DUST);
				entries.add(CORPSE_DUST);
				entries.add(COLORFUL_DUST);
				entries.add(SPECTRAL_DUST);
				entries.add(RESONATING_DUST);
				entries.add(RARE_DUST);
				entries.add(GEMSTONE_BLEND);
				entries.add(SAPPHIRE_DUST);
				entries.add(STARUDST);
			})
			.build();

	@Override
	public void onInitialize() {

		// item group
		Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "all_items"), ALL_ITEMS);
	}
}