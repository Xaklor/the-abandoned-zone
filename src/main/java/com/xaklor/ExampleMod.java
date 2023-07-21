package com.xaklor;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("the-abandoned-zone");

	// ITEMS
	public static final Item COAL_DUST = new Item(new FabricItemSettings());

	// item group, this gives mod items their own creative inventory page
	private static final ItemGroup ALL_ITEMS = FabricItemGroup.builder()
			.icon(() -> new ItemStack(COAL_DUST))
			.displayName(Text.translatable("itemGroup.the-abandoned-zone.all_items"))
			.entries((context, entries) -> {
				entries.add(COAL_DUST);
			})
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		// LOGGER.info("Hello Fabric world!");

		// ITEMS
		Registry.register(Registries.ITEM, new Identifier("the-abandoned-zone", "coal_dust"), COAL_DUST);

		// FUEL
		FuelRegistry.INSTANCE.add(COAL_DUST, 1600);

		// item group
		Registry.register(Registries.ITEM_GROUP, new Identifier("the-abandoned-zone", "all_items"), ALL_ITEMS);
		// RegistryKey<ItemGroup> test = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("the-abandoned-zone", "all_items"));

	}
}