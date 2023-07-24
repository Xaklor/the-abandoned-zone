package com.xaklor;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheAbandonedZoneMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("the-abandoned-zone");
	public static final Block CRYSTAL_STONE = new GlassBlock(FabricBlockSettings.create().nonOpaque().solidBlock(Blocks::never));

	//region ITEMS
	public static final Item AMETHYST_DUST = new Item(new FabricItemSettings());
	public static final Item AMULET_OF_GREED = new Item(new FabricItemSettings());
	public static final Item BENEVOLENT_SPIRIT = new Item(new FabricItemSettings());
	public static final Item BRILLIANT_INGOT = new Item(new FabricItemSettings());
	public static final Item BRILLIANT_NUGGET = new Item(new FabricItemSettings());
	public static final Item COAL_DUST = new Item(new FabricItemSettings());
	public static final Item COLORFUL_DUST = new Item(new FabricItemSettings());
	public static final Item CORPSE_DUST = new Item(new FabricItemSettings());
	public static final Item DIAMOND_DUST = new Item(new FabricItemSettings());
	public static final Item DULL_ORE = new Item(new FabricItemSettings());
	public static final Item EMERALD_DUST = new Item(new FabricItemSettings());
	public static final Item FLOUR = new Item(new FabricItemSettings());
	public static final Item FOOD_DUST = new Item(new FabricItemSettings());
	public static final Item FUEL_CANISTER = new Item(new FabricItemSettings());
	public static final Item FUNGAL_DUST = new Item(new FabricItemSettings());
	public static final Item GEMSTONE_BLEND = new Item(new FabricItemSettings());
	public static final Item GLASS_DUST = new Item(new FabricItemSettings());
	public static final Item GRASS_DUST = new Item(new FabricItemSettings());
	public static final Item HAMMER_HANDLE = new Item(new FabricItemSettings());
	public static final Item HAMMER_HEAD = new Item(new FabricItemSettings());
	public static final Item INFERNAL_DUST = new Item(new FabricItemSettings());
	public static final Item INSTANT_CAKE = new Item(new FabricItemSettings());
	public static final Item LAPIS_LAZULI_DUST = new Item(new FabricItemSettings());
	public static final Item MALEVOLENT_SPIRIT = new Item(new FabricItemSettings());
	public static final Item METAL_DUST = new Item(new FabricItemSettings());
	public static final Item MYSTIC_DUST = new Item(new FabricItemSettings());
	public static final Item NETHERITE_DUST = new Item(new FabricItemSettings());
	public static final Item NUTRIENT_POWDER = new Item(new FabricItemSettings());
	public static final Item POWDERED_EGG = new Item(new FabricItemSettings());
	public static final Item POWDERED_MILK	 = new Item(new FabricItemSettings());
	public static final Item QUARTZ_DUST = new Item(new FabricItemSettings());
	public static final Item RARE_DUST = new Item(new FabricItemSettings());
	public static final Item RESONATING_DUST = new Item(new FabricItemSettings());
	public static final Item ROYAL_HEART = new Item(new FabricItemSettings());
	public static final Item SCULK_DUST = new Item(new FabricItemSettings());
	public static final Item SOIL_DUST = new Item(new FabricItemSettings());
	public static final Item SPECTRAL_DUST = new Item(new FabricItemSettings());
	public static final Item SPIRIT_NEWBORN = new Item(new FabricItemSettings());
	public static final Item STAR_HAMMER = new Item(new FabricItemSettings());
	public static final Item STEEL_BONE = new Item(new FabricItemSettings());
	public static final Item WAXY_DUST = new Item(new FabricItemSettings());
	public static final Item WISHING_PENDANT = new Item(new FabricItemSettings());
	public static final Item WOOD_DUST = new Item(new FabricItemSettings());
	//endregion

	// item group, this gives mod items their own creative inventory page
	private static final ItemGroup ALL_ITEMS = FabricItemGroup.builder()
			.icon(() -> new ItemStack(COAL_DUST))
			.displayName(Text.translatable("itemGroup.the-abandoned-zone.all_items"))
			.entries((context, entries) -> {
				entries.add(DULL_ORE);
				entries.add(BRILLIANT_INGOT);
				entries.add(BRILLIANT_NUGGET);
				entries.add(HAMMER_HEAD);
				entries.add(HAMMER_HANDLE);
				entries.add(STAR_HAMMER);
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
			})
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		// LOGGER.info("Hello Fabric world!");

		Registry.register(Registries.BLOCK, new Identifier("the-abandoned-zone", "crystal_stone"), CRYSTAL_STONE);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "crystal_stone"), new BlockItem(CRYSTAL_STONE, new FabricItemSettings()));

		// var temp = Blocks.BROWN_STAINED_GLASS;

		//region ITEMS
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "amethyst_dust"), AMETHYST_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "amulet_of_greed"), AMULET_OF_GREED);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "benevolent_spirit"), BENEVOLENT_SPIRIT);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "brilliant_ingot"), BRILLIANT_INGOT);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "brilliant_nugget"), BRILLIANT_NUGGET);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "coal_dust"), COAL_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "colorful_dust"), COLORFUL_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "corpse_dust"), CORPSE_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "diamond_dust"), DIAMOND_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "dull_ore"), DULL_ORE);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "emerald_dust"), EMERALD_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "flour"), FLOUR);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "food_dust"), FOOD_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "fuel_canister"), FUEL_CANISTER);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "fungal_dust"), FUNGAL_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "gemstone_blend"), GEMSTONE_BLEND);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "glass_dust"), GLASS_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "grass_dust"), GRASS_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "hammer_handle"), HAMMER_HANDLE);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "hammer_head"), HAMMER_HEAD);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "infernal_dust"), INFERNAL_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "instant_cake"), INSTANT_CAKE);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "lapis_lazuli_dust"), LAPIS_LAZULI_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "malevolent_spirit"), MALEVOLENT_SPIRIT);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "metal_dust"), METAL_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "mystic_dust"), MYSTIC_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "netherite_dust"), NETHERITE_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "nutrient_powder"), NUTRIENT_POWDER);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "powdered_egg"), POWDERED_EGG);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "powdered_milk"), POWDERED_MILK);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "quartz_dust"), QUARTZ_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "rare_dust"), RARE_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "resonating_dust"), RESONATING_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "royal_heart"), ROYAL_HEART);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "sculk_dust"), SCULK_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "soil_dust"), SOIL_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "spectral_dust"), SPECTRAL_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "spirit_newborn"), SPIRIT_NEWBORN);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "star_hammer"), STAR_HAMMER);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "steel_bone"), STEEL_BONE);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "waxy_dust"), WAXY_DUST);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "wishing_pendant"), WISHING_PENDANT);
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "wood_dust"), WOOD_DUST);
		//endregion

		// FUEL
		FuelRegistry.INSTANCE.add(COAL_DUST, 1600);
		FuelRegistry.INSTANCE.add(GRASS_DUST, 100);
		FuelRegistry.INSTANCE.add(FUEL_CANISTER, 2400);
		FuelRegistry.INSTANCE.add(WOOD_DUST, 300);

		// item group
		Registry.register(Registries.ITEM_GROUP, new Identifier("the-abandoned-zone", "all_items"), ALL_ITEMS);
		// RegistryKey<ItemGroup> test = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("the-abandoned-zone", "all_items"));

	}
}