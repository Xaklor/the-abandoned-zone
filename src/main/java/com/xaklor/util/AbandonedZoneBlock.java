package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.StateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

/**
 * Class for simplifying block initialization and registration
 */
public class AbandonedZoneBlock implements ItemConvertible {

    public final Identifier id;
    public final BlockType type;
    public final Block block;
    public final Item item;
    public final boolean hasItem;
    public final int burnTime;

    public enum BlockType {
        NORMAL,
        TRANSPARENT,
        PILLAR,
        FACING
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
            case FACING -> this.block = new AbandonedZoneFacingBlock(settings);
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

    @Override
    public Item asItem() {
        return this.item;
    }

    private static class AbandonedZoneFacingBlock extends FacingBlock {

        public AbandonedZoneFacingBlock(Settings settings) {
            super(settings);
            this.setDefaultState(getDefaultState().with(FACING, Direction.DOWN));
        }

        @Override
        protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
            builder.add(FACING);
        }

        public BlockState getPlacementState(ItemPlacementContext ctx) {
            return this.getDefaultState().with(FACING, ctx.getSide());
        }
    }

}
