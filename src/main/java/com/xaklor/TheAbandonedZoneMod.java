package com.xaklor;

import com.xaklor.util.AbandonedZoneBlock;
import com.xaklor.util.AbandonedZoneBlock.BlockType;
import com.xaklor.util.AbandonedZoneItem;
import com.xaklor.util.AbandonedZoneRegisterable;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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
	public static final List<AbandonedZoneRegisterable> REGISTERABLE = new ArrayList<>();

	//region ITEMS
	public static final Item AMETHYST_DUST = new AbandonedZoneItem("amethyst_dust", new FabricItemSettings());
	public static final Item AMULET_OF_GREED = new AbandonedZoneItem("amulet_of_greed", new FabricItemSettings());
	public static final Item BENEVOLENT_SPIRIT = new AbandonedZoneItem("benevolent_spirit", new FabricItemSettings());
	public static final Item BRILLIANT_INGOT = new AbandonedZoneItem("brilliant_ingot", new FabricItemSettings());
	public static final Item BRILLIANT_NUGGET = new AbandonedZoneItem("brilliant_nugget", new FabricItemSettings());
	public static final Item COAL_DUST = new AbandonedZoneItem("coal_dust", new FabricItemSettings());
	public static final Item COLORFUL_DUST = new AbandonedZoneItem("colorful_dust", new FabricItemSettings());
	public static final Item CORPSE_DUST = new AbandonedZoneItem("corpse_dust", new FabricItemSettings());
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
	public static final Item POWDERED_EGG = new AbandonedZoneItem("powdered_egg", new FabricItemSettings());
	public static final Item POWDERED_MILK	 = new AbandonedZoneItem("powdered_milk", new FabricItemSettings());
	public static final Item QUARTZ_DUST = new AbandonedZoneItem("quartz_dust", new FabricItemSettings());
	public static final Item RARE_DUST = new AbandonedZoneItem("rare_dust", new FabricItemSettings());
	public static final Item RESONATING_DUST = new AbandonedZoneItem("resonating_dust", new FabricItemSettings());
	public static final Item ROYAL_HEART = new AbandonedZoneItem("royal_heart", new FabricItemSettings());
	public static final Item SCULK_DUST = new AbandonedZoneItem("sculk_dust", new FabricItemSettings());
	public static final Item SOIL_DUST = new AbandonedZoneItem("soil_dust", new FabricItemSettings());
	public static final Item SPECTRAL_DUST = new AbandonedZoneItem("spectral_dust", new FabricItemSettings());
	public static final Item SPIRIT_NEWBORN = new AbandonedZoneItem("spirit_newborn", new FabricItemSettings());
	public static final Item STAR_HAMMER = new AbandonedZoneItem("star_hammer", new FabricItemSettings());
	public static final Item STEEL_BONE = new AbandonedZoneItem("steel_bone", new FabricItemSettings());
	public static final Item WAXY_DUST = new AbandonedZoneItem("waxy_dust", new FabricItemSettings());
	public static final Item WISHING_PENDANT = new AbandonedZoneItem("wishing_pendant", new FabricItemSettings());
	public static final Item WOOD_DUST = new AbandonedZoneItem("wood_dust", new FabricItemSettings());
	//endregion

	//region BLOCKS
	public static final AbandonedZoneBlock CRYSTAL_STONE = new AbandonedZoneBlock("crystal_stone", FabricBlockSettings.create().nonOpaque().solidBlock(Blocks::never), BlockType.TRANSPARENT);
	//endregion

	// item group, this gives mod items their own creative inventory page
	private static final ItemGroup ALL_ITEMS = FabricItemGroup.builder()
			.icon(() -> new ItemStack(COAL_DUST))
			.displayName(Text.translatable("itemGroup.the_abandoned_zone.all_items"))
			.entries((context, entries) -> {
				for (AbandonedZoneRegisterable r : REGISTERABLE)
					if (r instanceof Item item)
						entries.add(item);
					else if (r instanceof AbandonedZoneBlock b && b.hasItem)
						entries.add(b.blockItem);
			})
			.build();

	@Override
	public void onInitialize() {
		REGISTERABLE.forEach(AbandonedZoneRegisterable::register);

		// item group
		Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "all_items"), ALL_ITEMS);
	}
}