package com.xaklor.util.other;

import com.xaklor.TheAbandonedZoneMod;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class AbandonedPortal
        extends Block {
    public final Item item;
    public final Identifier ID = new Identifier(TheAbandonedZoneMod.MOD_ID, "abandoned_portal");

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 6.0, 0.0, 16.0, 10.0, 16.0);

    public AbandonedPortal(AbstractBlock.Settings settings) {
        super(settings);
        item = new BlockItem(this, new FabricItemSettings());
        register();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world instanceof ServerWorld) {
            if (entity.canUsePortals()) {
                VoxelShape entityShape = VoxelShapes.cuboid(entity.getBoundingBox()).offset(-pos.getX(), -pos.getY(), -pos.getZ());
                VoxelShape blockShape = state.getOutlineShape(world, pos);
                if (VoxelShapes.matchesAnywhere(entityShape, blockShape, BooleanBiFunction.AND)) {
                    RegistryKey<World> registryKey = TheAbandonedZoneMod.THE_ABANDONED_ZONE;
                    ServerWorld serverWorld = ((ServerWorld)world).getServer().getWorld(registryKey);
                    if (serverWorld == null) {
                        return;
                    }
                    FabricDimensions.teleport(entity, serverWorld, new TeleportTarget(new Vec3d(0, 100, 0), new Vec3d(0, 0, 0), 0, 0));
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double)pos.getX() + random.nextDouble();
        double e = (double)pos.getY() + 0.8;
        double f = (double)pos.getZ() + random.nextDouble();
        world.addParticle(ParticleTypes.ENCHANTED_HIT, d, e, f, 0.0, 0.0, 0.0);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canBucketPlace(BlockState state, Fluid fluid) {
        return false;
    }

    private void register() {
        Registry.register(Registries.BLOCK, ID, this);
        Registry.register(Registries.ITEM, ID, this.item);
    }
}

