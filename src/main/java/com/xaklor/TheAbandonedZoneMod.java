package com.xaklor;

import com.xaklor.util.features.BroadcasterFeature;
import com.xaklor.util.features.CraneFeature;
import com.xaklor.util.features.ReceiverFeature;
import com.xaklor.util.other.AbandonedPortal;
import com.xaklor.util.alchemybox.AlchemyBox;
import com.xaklor.util.alchemybox.AlchemyBoxEntity;
import com.xaklor.util.alchemybox.AlchemyBoxRecipes;
import com.xaklor.util.alchemybox.AlchemyBoxScreenHandler;
import com.xaklor.util.assembler.*;
import com.xaklor.util.disintegrator.Disintegrator;
import com.xaklor.util.disintegrator.DisintegratorEntity;
import com.xaklor.util.disintegrator.DisintegratorRecipes;
import com.xaklor.util.disintegrator.DisintegratorScreenHandler;
import com.xaklor.util.general.*;
import com.xaklor.util.general.AbandonedZoneBlock.BlockType;
import com.xaklor.util.general.AbandonedZoneTool.ToolType;
import com.xaklor.util.mobs.SteelSkeletonEntity;
import com.xaklor.util.mobs.ZombieKingEntity;
import com.xaklor.util.other.MiracleCure;
import com.xaklor.util.other.NotPlayerHead;
import com.xaklor.util.sculkchest.SculkChest;
import com.xaklor.util.sculkchest.SculkChestEntity;
import com.xaklor.util.sculkchest.SculkLinker;
import com.xaklor.util.wells.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheAbandonedZoneMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "the_abandoned_zone";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final List<Item> ITEMS = new ArrayList<>();
	public static final List<AbandonedZoneBlock> BLOCKS = new ArrayList<>();
	public static final Map<Item, List<Enchantment>> INCOMPATIBLE_ENCHANT_RULES = new HashMap<>();
	public static final Map<Item, List<Enchantment>> COMPATIBLE_ENCHANT_RULES = new HashMap<>();
	public static final RegistryKey<World> THE_ABANDONED_ZONE = RegistryKey.of(RegistryKeys.WORLD, new Identifier(MOD_ID, "the_abandoned_zone"));


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
	public static final Item INSTANT_CAKE = new AbandonedZoneItem("instant_cake", new FabricItemSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).snack().build()));
	public static final Item LAPIS_LAZULI_DUST = new AbandonedZoneItem("lapis_lazuli_dust", new FabricItemSettings());
	public static final Item MALEVOLENT_SPIRIT = new AbandonedZoneItem("malevolent_spirit", new FabricItemSettings());
	public static final Item METAL_DUST = new AbandonedZoneItem("metal_dust", new FabricItemSettings());
	public static final Item MIRACLE_CURE = new MiracleCure(new FabricItemSettings());
	public static final Item MYSTIC_DUST = new AbandonedZoneItem("mystic_dust", new FabricItemSettings());
	public static final Item NETHERITE_DUST = new AbandonedZoneItem("netherite_dust", new FabricItemSettings());
	public static final Item NUTRIENT_POWDER = new AbandonedZoneItem("nutrient_powder", new FabricItemSettings().food(new FoodComponent.Builder().hunger(20).saturationModifier(0.05f).build()));
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
	public static final Item STARDUST = new AbandonedZoneItem("stardust", new FabricItemSettings());
	public static final Item STEEL_BONE = new AbandonedZoneItem("steel_bone", new FabricItemSettings());
	public static final Item THE_ABANDONED_TOME = new AbandonedZoneItem("the_abandoned_tome", new FabricItemSettings());
	public static final Item WAXY_DUST = new AbandonedZoneItem("waxy_dust", new FabricItemSettings());
	public static final Item WISHING_PENDANT = new AbandonedZoneItem("wishing_pendant", new FabricItemSettings());
	public static final Item WOOD_DUST = new AbandonedZoneItem("wood_dust", new FabricItemSettings());
	public static final Item YELLOW_SAPPHIRE = new AbandonedZoneItem("yellow_sapphire", new FabricItemSettings());
	//endregion

	//region BLOCKS
	public static final AbandonedPortal ABANDONED_PORTAL = new AbandonedPortal(FabricBlockSettings.create().noCollision().luminance(state -> 15).strength(-1.0f, 3600000.0f).dropsNothing().pistonBehavior(PistonBehavior.BLOCK));
	public static final AbandonedZoneBlock ALCHEMIC_BRICKS = new AbandonedZoneBlock("alchemic_bricks", FabricBlockSettings.create().requiresTool().strength(3, 6).sounds(BlockSoundGroup.NETHER_BRICKS), BlockType.NORMAL);
	public static final AssemblerArms ASSEMBLER_ARMS = new AssemblerArms(FabricBlockSettings.create().strength(1.5f, 3).sounds(BlockSoundGroup.METAL));
	public static final AbandonedZoneBlock BLACK_CRYSTAL_STONE = new AbandonedZoneBlock("black_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock BLUE_CRYSTAL_STONE = new AbandonedZoneBlock("blue_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock BRILLIANT_BLOCK = new AbandonedZoneBlock("brilliant_block", FabricBlockSettings.create().requiresTool().strength(3, 6).sounds(BlockSoundGroup.NETHERITE), BlockType.FACING);
	public static final AbandonedZoneBlock BRILLIANT_ORE = new AbandonedZoneBlock("brilliant_ore", FabricBlockSettings.create().requiresTool().strength(3, 6).sounds(BlockSoundGroup.STONE), BlockType.NORMAL);
	public static final AbandonedZoneBlock BROWN_CRYSTAL_STONE = new AbandonedZoneBlock("brown_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final ConveyorBelt CONVEYOR_BELT = new ConveyorBelt(FabricBlockSettings.create().strength(1.5f, 3).sounds(BlockSoundGroup.METAL));
	public static final AbandonedZoneBlock CRYSTAL_STONE = new AbandonedZoneBlock("crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock CYAN_CRYSTAL_STONE = new AbandonedZoneBlock("cyan_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock DEEPSLATE_BRILLIANT_ORE = new AbandonedZoneBlock("deepslate_brilliant_ore", FabricBlockSettings.create().requiresTool().strength(4.5f, 6).sounds(BlockSoundGroup.DEEPSLATE), BlockType.NORMAL);
	public static final AbandonedZoneBlock DEEPSLATE_SAPPHIRE_ORE = new AbandonedZoneBlock("deepslate_sapphire_ore", FabricBlockSettings.create().requiresTool().strength(4.5f, 6).sounds(BlockSoundGroup.DEEPSLATE), BlockType.NORMAL);
	public static final AbandonedZoneBlock DULL_ORE_BLOCK = new AbandonedZoneBlock("dull_ore_block", FabricBlockSettings.create().requiresTool().strength(3, 6).sounds(BlockSoundGroup.NETHERITE), BlockType.NORMAL);
	public static final AbandonedZoneBlock GNEISS = new AbandonedZoneBlock("gneiss", FabricBlockSettings.create().requiresTool().strength(40, 6).sounds(BlockSoundGroup.DRIPSTONE_BLOCK), BlockType.PILLAR);
	public static final AbandonedZoneBlock GNEISS_BRILLIANT_ORE = new AbandonedZoneBlock("gneiss_brilliant_ore", FabricBlockSettings.create().requiresTool().strength(50, 6).sounds(BlockSoundGroup.DRIPSTONE_BLOCK), BlockType.PILLAR);
	public static final AbandonedZoneBlock GRAY_CRYSTAL_STONE = new AbandonedZoneBlock("gray_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock GREEN_CRYSTAL_STONE = new AbandonedZoneBlock("green_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock INDUSTRIAL_SCRAP = new AbandonedZoneBlock("industrial_scrap", FabricBlockSettings.create().requiresTool().strength(3, 6).sounds(BlockSoundGroup.COPPER), BlockType.NORMAL);
	public static final AbandonedZoneBlock LIGHT_BLUE_CRYSTAL_STONE = new AbandonedZoneBlock("light_blue_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock LIGHT_GRAY_CRYSTAL_STONE = new AbandonedZoneBlock("light_gray_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock LIME_CRYSTAL_STONE = new AbandonedZoneBlock("lime_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock MAGENTA_CRYSTAL_STONE = new AbandonedZoneBlock("magenta_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock METAL_MATRIX = new AbandonedZoneBlock("metal_matrix", FabricBlockSettings.create().requiresTool().strength(20, 30).sounds(BlockSoundGroup.COPPER), BlockType.NORMAL);
	public static final AbandonedZoneBlock METEORITE_CHUNK = new AbandonedZoneBlock("meteorite_chunk", FabricBlockSettings.create().requiresTool().strength(50, 1200).sounds(BlockSoundGroup.NETHERITE), BlockType.NORMAL);
	public static final AbandonedZoneBlock MIGHT_BLOCK = new AbandonedZoneBlock("might_block", FabricBlockSettings.create().requiresTool().strength(30, 30).sounds(BlockSoundGroup.NETHERITE), BlockType.NORMAL);
	public static final NotPlayerHead NOT_PLAYER_HEAD = new NotPlayerHead(FabricBlockSettings.create().strength(1.5f, 3));
	public static final AbandonedZoneBlock ORANGE_CRYSTAL_STONE = new AbandonedZoneBlock("orange_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock PURPLE_CRYSTAL_STONE = new AbandonedZoneBlock("purple_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock RED_CRYSTAL_STONE = new AbandonedZoneBlock("red_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock SAPPHIRE_ORE = new AbandonedZoneBlock("sapphire_ore", FabricBlockSettings.create().requiresTool().strength(50, 6).sounds(BlockSoundGroup.DRIPSTONE_BLOCK), BlockType.PILLAR);
	public static final AbandonedZoneBlock VOLCANIC_ASH = new AbandonedZoneBlock("volcanic_ash", FabricBlockSettings.create().strength(1.5f, 3).sounds(BlockSoundGroup.SAND), BlockType.NORMAL);
	public static final AbandonedZoneBlock WHITE_CRYSTAL_STONE = new AbandonedZoneBlock("white_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);
	public static final AbandonedZoneBlock YELLOW_CRYSTAL_STONE = new AbandonedZoneBlock("yellow_crystal_stone", FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f, 1.5f).sounds(BlockSoundGroup.GLASS), BlockType.TRANSPARENT);

	//endregion

	//region TOOLS
	public static final AbandonedZoneTool BLUE_SAPPHIRE_PICK = new AbandonedZoneTool(AbandonedZoneMaterials.BLUE_SAPPHIRE, 0, -2.8f, new Item.Settings(), "blue_sapphire_pick", ToolType.PICKAXE);
	public static final AbandonedZoneTool BRILLIANT_PICK = new AbandonedZoneTool(AbandonedZoneMaterials.BRILLIANT_MATERIAL, 0, -2.8f, new Item.Settings(), "brilliant_pick", ToolType.PICKAXE);
	public static final AbandonedZoneTool CYAN_SAPPHIRE_PICK = new AbandonedZoneTool(AbandonedZoneMaterials.CYAN_SAPPHIRE, 0, -2.8f, new Item.Settings(), "cyan_sapphire_pick", ToolType.PICKAXE);
	public static final AbandonedZoneTool GREEN_SAPPHIRE_PICK = new AbandonedZoneTool(AbandonedZoneMaterials.GREEN_SAPPHIRE, 0, -2.8f, new Item.Settings(), "green_sapphire_pick", ToolType.PICKAXE);
	public static final AbandonedZoneTool PINK_SAPPHIRE_PICK = new AbandonedZoneTool(AbandonedZoneMaterials.PINK_SAPPHIRE, 0, -2.8f, new Item.Settings(), "pink_sapphire_pick", ToolType.PICKAXE);
	public static final AbandonedZoneTool PURPLE_SAPPHIRE_PICK = new AbandonedZoneTool(AbandonedZoneMaterials.PURPLE_SAPPHIRE, 0, -2.8f, new Item.Settings(), "purple_sapphire_pick", ToolType.PICKAXE);
	public static final AbandonedZoneTool RED_SAPPHIRE_PICK = new AbandonedZoneTool(AbandonedZoneMaterials.RED_SAPPHIRE, 0, -2.8f, new Item.Settings(), "red_sapphire_pick", ToolType.PICKAXE);
	public static final AbandonedZoneTool SCRAP_PICK = new AbandonedZoneTool(AbandonedZoneMaterials.SCRAP_MATERIAL, 0, -2.8f, new Item.Settings(), "scrap_pick", ToolType.PICKAXE);
	public static final AbandonedZoneTool STAR_HAMMER = new AbandonedZoneTool(AbandonedZoneMaterials.METEORITE_MATERIAL, 0, -2.4f, new Item.Settings(), "star_hammer", ToolType.SWORD, Enchantments.FIRE_ASPECT, Enchantments.BANE_OF_ARTHROPODS, Enchantments.SMITE, Enchantments.SHARPNESS);
	public static final AbandonedZoneTool YELLOW_SAPPHIRE_PICK = new AbandonedZoneTool(AbandonedZoneMaterials.YELLOW_SAPPHIRE, 0, -2.8f, new Item.Settings(), "yellow_sapphire_pick", ToolType.PICKAXE);
	//endregion

	//region BLOCK ENTITIES
	public static final AlchemyBox ALCHEMY_BOX = new AlchemyBox(FabricBlockSettings.create().strength(1.5f, 3).sounds(BlockSoundGroup.METAL));
	public static final BlockEntityType<AlchemyBoxEntity> ALCHEMY_BOX_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, ALCHEMY_BOX.ID, FabricBlockEntityTypeBuilder.create(AlchemyBoxEntity::new, ALCHEMY_BOX).build());
	public static final ScreenHandlerType<AlchemyBoxScreenHandler> ALCHEMY_BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ALCHEMY_BOX.ID, AlchemyBoxScreenHandler::new);
	public static final Assembler ASSEMBLER = new Assembler(FabricBlockSettings.create().strength(1.5f, 3).sounds(BlockSoundGroup.METAL));
	public static final BlockEntityType<AssemblerEntity> ASSEMBLER_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, ASSEMBLER.ID, FabricBlockEntityTypeBuilder.create(AssemblerEntity::new, ASSEMBLER).build());
	public static final ScreenHandlerType<AssemblerScreenHandler> ASSEMBLER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ASSEMBLER.ID, AssemblerScreenHandler::new);
	public static final Disintegrator DISINTEGRATOR = new Disintegrator(FabricBlockSettings.create().strength(1.5f, 3).luminance(Disintegrator.createLightLevelFromOnBlockState(15)).sounds(BlockSoundGroup.METAL));
	public static final BlockEntityType<DisintegratorEntity> DISINTEGRATOR_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, DISINTEGRATOR.ID, FabricBlockEntityTypeBuilder.create(DisintegratorEntity::new, DISINTEGRATOR).build());
	public static final ScreenHandlerType<DisintegratorScreenHandler> DISINTEGRATOR_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(DISINTEGRATOR.ID, DisintegratorScreenHandler::new);
	public static final GreedyWell GREEDY_WELL = new GreedyWell(FabricBlockSettings.create().strength(1.5f, 3).sounds(BlockSoundGroup.STONE));
	public static final BlockEntityType<GreedyWellEntity> GREEDY_WELL_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, GREEDY_WELL.ID, FabricBlockEntityTypeBuilder.create(GreedyWellEntity::new, GREEDY_WELL).build());
	public static final SculkChest SCULK_CHEST = new SculkChest(FabricBlockSettings.create().strength(1.5f, 3).sounds(BlockSoundGroup.SCULK));
	public static final SculkLinker SCULK_LINKER = new SculkLinker("sculk_linker", new FabricItemSettings().maxCount(1));
	public static final BlockEntityType<SculkChestEntity> SCULK_CHEST_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, SCULK_CHEST.ID, FabricBlockEntityTypeBuilder.create(SculkChestEntity::new, SCULK_CHEST).build());
	public static final WishingWell WISHING_WELL = new WishingWell(FabricBlockSettings.create().strength(1.5f, 3).sounds(BlockSoundGroup.STONE));
	public static final BlockEntityType<WishingWellEntity> WISHING_WELL_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, WISHING_WELL.ID, FabricBlockEntityTypeBuilder.create(WishingWellEntity::new, WISHING_WELL).build());
	//endregion

	//region RECIPES
	public static final AlchemyBoxRecipes ALCHEMY_BOX_RECIPES = new AlchemyBoxRecipes();
	public static final AssemblerRecipes ASSEMBLER_RECIPES = new AssemblerRecipes();
	public static final DisintegratorRecipes DISINTEGRATOR_RECIPES = new DisintegratorRecipes();
	public static final WellScores WELL_SCORES = new WellScores();
	//endregion

	//region MOBS
	public static final EntityType<SteelSkeletonEntity> STEEL_SKELETON = Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "steel_skeleton"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SteelSkeletonEntity::new).dimensions(EntityDimensions.fixed(0.75f, 2.4f)).fireImmune().build());
	public static final Item STEEL_SKELETON_SPAWN_EGG = new SpawnEggItem(STEEL_SKELETON, 0x556266, 0xffffff, new FabricItemSettings());
	public static final EntityType<ZombieKingEntity> ZOMBIE_KING = Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "zombie_king"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ZombieKingEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
	public static final Item ZOMBIE_KING_SPAWN_EGG = new SpawnEggItem(ZOMBIE_KING, 0xd52639, 0x474f1e, new FabricItemSettings());
	//endregion

	//region SOUNDS
	public static final Identifier STEEL_SKELETON_DEATH_ID = new Identifier(MOD_ID, "steel_skeleton_death");
	public static SoundEvent STEEL_SKELETON_DEATH = SoundEvent.of(STEEL_SKELETON_DEATH_ID);
	public static final Identifier STEEL_SKELETON_RARE_DEATH_ID = new Identifier(MOD_ID, "steel_skeleton_rare_death");
	public static SoundEvent STEEL_SKELETON_RARE_DEATH = SoundEvent.of(STEEL_SKELETON_RARE_DEATH_ID);
	public static final Identifier STEEL_SKELETON_IDLE_ID = new Identifier(MOD_ID, "steel_skeleton_idle");
	public static SoundEvent STEEL_SKELETON_IDLE = SoundEvent.of(STEEL_SKELETON_IDLE_ID);
	public static final Identifier STEEL_SKELETON_HURT_ID = new Identifier(MOD_ID, "steel_skeleton_hurt");
	public static SoundEvent STEEL_SKELETON_HURT = SoundEvent.of(STEEL_SKELETON_HURT_ID);
	//endregion

	//region FEATURES
	public static final Identifier BROADCASTER_FEATURE_ID = new Identifier(MOD_ID, "broadcaster");
	public static final BroadcasterFeature BROADCASTER_FEATURE = new BroadcasterFeature(DefaultFeatureConfig.CODEC);
	public static final Identifier RECEIVER_FEATURE_ID = new Identifier(MOD_ID, "receiver");
	public static final ReceiverFeature RECEIVER_FEATURE = new ReceiverFeature(DefaultFeatureConfig.CODEC);
	public static final Identifier CRANE_FEATURE_ID = new Identifier(MOD_ID, "crane");
	public static final CraneFeature CRANE_FEATURE = new CraneFeature(DefaultFeatureConfig.CODEC);
	//endregion

	// item group, this gives mod items their own creative inventory page
	private static final ItemGroup ALL_ITEMS = FabricItemGroup.builder()
			.icon(() -> new ItemStack(THE_ABANDONED_TOME))
			.displayName(Text.translatable("itemGroup.the_abandoned_zone.all_items"))
			.entries((context, entries) -> {
				entries.add(THE_ABANDONED_TOME);
				entries.add(GNEISS);
				entries.add(ALCHEMIC_BRICKS);
				entries.add(VOLCANIC_ASH);
				entries.add(CRYSTAL_STONE);
				entries.add(WHITE_CRYSTAL_STONE);
				entries.add(LIGHT_GRAY_CRYSTAL_STONE);
				entries.add(GRAY_CRYSTAL_STONE);
				entries.add(BLACK_CRYSTAL_STONE);
				entries.add(BROWN_CRYSTAL_STONE);
				entries.add(RED_CRYSTAL_STONE);
				entries.add(ORANGE_CRYSTAL_STONE);
				entries.add(YELLOW_CRYSTAL_STONE);
				entries.add(LIME_CRYSTAL_STONE);
				entries.add(GREEN_CRYSTAL_STONE);
				entries.add(CYAN_CRYSTAL_STONE);
				entries.add(LIGHT_BLUE_CRYSTAL_STONE);
				entries.add(BLUE_CRYSTAL_STONE);
				entries.add(PURPLE_CRYSTAL_STONE);
				entries.add(MAGENTA_CRYSTAL_STONE);
				entries.add(METAL_MATRIX);
				entries.add(MIGHT_BLOCK);
				entries.add(INDUSTRIAL_SCRAP);
				entries.add(BRILLIANT_ORE);
				entries.add(GNEISS_BRILLIANT_ORE);
				entries.add(DEEPSLATE_BRILLIANT_ORE);
				entries.add(DULL_ORE);
				entries.add(BRILLIANT_INGOT);
				entries.add(BRILLIANT_NUGGET);
				entries.add(BRILLIANT_BLOCK);
				entries.add(DULL_ORE_BLOCK);
				entries.add(SAPPHIRE_ORE);
				entries.add(DEEPSLATE_SAPPHIRE_ORE);
				entries.add(BLUE_SAPPHIRE);
				entries.add(RED_SAPPHIRE);
				entries.add(YELLOW_SAPPHIRE);
				entries.add(GREEN_SAPPHIRE);
				entries.add(CYAN_SAPPHIRE);
				entries.add(PURPLE_SAPPHIRE);
				entries.add(PINK_SAPPHIRE);
				entries.add(METEORITE_CHUNK);
				entries.add(ASSEMBLER);
				entries.add(CONVEYOR_BELT);
				entries.add(ASSEMBLER_ARMS);
				entries.add(DISINTEGRATOR);
				entries.add(ALCHEMY_BOX);
				entries.add(GREEDY_WELL);
				entries.add(WISHING_WELL);
				entries.add(SCULK_CHEST);
				entries.add(SCULK_LINKER);
				entries.add(NOT_PLAYER_HEAD);
				entries.add(SCRAP_PICK);
				entries.add(BRILLIANT_PICK);
				entries.add(BLUE_SAPPHIRE_PICK);
				entries.add(RED_SAPPHIRE_PICK);
				entries.add(YELLOW_SAPPHIRE_PICK);
				entries.add(GREEN_SAPPHIRE_PICK);
				entries.add(CYAN_SAPPHIRE_PICK);
				entries.add(PURPLE_SAPPHIRE_PICK);
				entries.add(PINK_SAPPHIRE_PICK);
				entries.add(HAMMER_HEAD);
				entries.add(HAMMER_HANDLE);
				entries.add(STAR_HAMMER);
				entries.add(FUEL_CANISTER);
				entries.add(INSTANT_CAKE);
				entries.add(NUTRIENT_POWDER);
				entries.add(MIRACLE_CURE);
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
				entries.add(STARDUST);
				entries.add(ZOMBIE_KING_SPAWN_EGG);
				entries.add(STEEL_SKELETON_SPAWN_EGG);
			})
			.build();

	@Override
	public void onInitialize() {
		// potions
		AbandonedZonePotions.registerPotionsRecipes();
		// item group
		Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "all_items"), ALL_ITEMS);

		FabricDefaultAttributeRegistry.register(STEEL_SKELETON, SteelSkeletonEntity.createSteelSkeletonAttributes());
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "steel_skeleton_spawn_egg"), STEEL_SKELETON_SPAWN_EGG);

		FabricDefaultAttributeRegistry.register(ZOMBIE_KING, ZombieKingEntity.createZombieKingAttributes());
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "zombie_king_spawn_egg"), ZOMBIE_KING_SPAWN_EGG);

		Registry.register(Registries.SOUND_EVENT, STEEL_SKELETON_DEATH_ID, STEEL_SKELETON_DEATH);
		Registry.register(Registries.SOUND_EVENT, STEEL_SKELETON_RARE_DEATH_ID, STEEL_SKELETON_RARE_DEATH);
		Registry.register(Registries.SOUND_EVENT, STEEL_SKELETON_IDLE_ID, STEEL_SKELETON_IDLE);
		Registry.register(Registries.SOUND_EVENT, STEEL_SKELETON_HURT_ID, STEEL_SKELETON_HURT);

		Registry.register(Registries.FEATURE, BROADCASTER_FEATURE_ID, BROADCASTER_FEATURE);
		Registry.register(Registries.FEATURE, RECEIVER_FEATURE_ID, RECEIVER_FEATURE);
		Registry.register(Registries.FEATURE, CRANE_FEATURE_ID, CRANE_FEATURE);
	}
}