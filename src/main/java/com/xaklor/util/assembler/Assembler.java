package com.xaklor.util.assembler;

import com.xaklor.TheAbandonedZoneMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class Assembler extends Block {
    public final Item item;
    VoxelShape BASE = createCuboidShape(0, 0, 0, 16, 3, 16);
    VoxelShape TOP = createCuboidShape(0, 13, 0, 16, 16, 16);
    VoxelShape LEG_NW = createCuboidShape(0, 3, 0, 3, 13, 3);
    VoxelShape LEG_NE = createCuboidShape(13, 3, 0, 16, 13, 3);
    VoxelShape LEG_SW = createCuboidShape(0, 3, 13, 3, 13, 16);
    VoxelShape LEG_SE = createCuboidShape(13, 3, 13, 16, 13, 16);
    VoxelShape SHAPE = VoxelShapes.union(BASE, TOP, LEG_NW, LEG_NE, LEG_SW, LEG_SE);
    public Assembler(Settings settings) {
        super(settings);
        this.item = new BlockItem(this, new FabricItemSettings());
        register();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    private void register() {
        Registry.register(Registries.BLOCK, new Identifier(TheAbandonedZoneMod.MOD_ID, "assembler"), this);
        Registry.register(Registries.ITEM, new Identifier(TheAbandonedZoneMod.MOD_ID, "assembler"), this.item);
    }
}
