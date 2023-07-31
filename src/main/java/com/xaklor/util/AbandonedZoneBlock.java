package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Class for simplifying block initialization and registration
 */
public class AbandonedZoneBlock {

    public final Identifier id;
    public final BlockType type;
    public final Block block;
    public final Item item;
    public final boolean hasItem;
    public final int burnTime;

    public enum BlockType {
        NORMAL,
        TRANSPARENT,
        PILLAR
    }

    /**
     * @param id string id, registered in the form "the_abandoned_zone:id"
     * @param settings block settings
     * @param blockType either TRANSPARENT, PILLAR, or NORMAL transparent allows for block transparency, pillar behaves like pillars
     * @param hasItem if the block should be obtainable or not
     * @param burnTime burn time of the block item in a furnace, if 0 does not add to FuelRegistry
     */
    public AbandonedZoneBlock(String id, AbstractBlock.Settings settings, BlockType blockType, boolean hasItem, int burnTime) {
        this.id = new Identifier(TheAbandonedZoneMod.MOD_ID, id);
        this.type = blockType;
        switch (blockType) {
            case TRANSPARENT -> this.block = new TransparentBlock(settings);
            case PILLAR -> this.block = new PillarBlock(settings);
            default -> this.block = new Block(settings);
        }
        this.hasItem = hasItem;
        this.item = this.hasItem ? new BlockItem(block, new FabricItemSettings()) : Items.AIR;
        TheAbandonedZoneMod.BLOCKS.add(this);
        if (hasItem)
            TheAbandonedZoneMod.ITEMS.add(this.item);
        this.burnTime = burnTime;
        this.register();
    }

    public AbandonedZoneBlock(String id, AbstractBlock.Settings settings, BlockType blockType, int burnTime) {
        this(id, settings, blockType, true, burnTime);
    }

    public AbandonedZoneBlock(String id, AbstractBlock.Settings settings, BlockType blockType) {
        this(id, settings, blockType, true, 0);
    }

    private void register() {
        Registry.register(Registries.BLOCK, this.id, this.block);
        if (this.hasItem)
            Registry.register(Registries.ITEM, this.id, this.item);
        if (this.burnTime > 0)
            FuelRegistry.INSTANCE.add(this.item, this.burnTime);
    }

}
