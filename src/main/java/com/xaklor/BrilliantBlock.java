package com.xaklor;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.StateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class BrilliantBlock extends FacingBlock {

    public BrilliantBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(FACING, Direction.DOWN));
        this.register();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getSide());
    }

    private void register(){
        Registry.register(Registries.BLOCK, new Identifier("the_abandoned_zone", "brilliant_block"), this);
        Registry.register(Registries.ITEM, new Identifier("the_abandoned_zone", "brilliant_block"), new BlockItem(this, new FabricItemSettings()));

    }
}
