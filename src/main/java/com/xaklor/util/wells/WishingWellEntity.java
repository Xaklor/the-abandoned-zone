package com.xaklor.util.wells;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WishingWellEntity extends BlockEntity {
    private int numItems = 0;
    private int sumItems = 0;
    private boolean inactive = true;
    private static final int MAX_ITEMS = 10;
    private static final WishingWellPrizes PRIZES = new WishingWellPrizes();
    private static final Vec3i[] BLOCK_LOCATIONS = {
            // back
            new Vec3i(1, 0, 0),
            new Vec3i(1, 1, 0),
            new Vec3i(0, 1, 0),
            new Vec3i(-1, 1, 0),
            new Vec3i(-1, 0, 0),
            new Vec3i(-1, -1, 0),
            new Vec3i(0, -1, 0),
            new Vec3i(1, -1, 0),
            new Vec3i(1, -2, 0),
            new Vec3i(1, -3, 0),
            new Vec3i(0, -2, 0),
            new Vec3i(0, -3, 0),
            new Vec3i(-1, -2, 0),
            new Vec3i(-1, -3, 0),
            // front
            new Vec3i(-1, -1, 3),
            new Vec3i(-1, -2, 3),
            new Vec3i(-1, -3, 3),
            new Vec3i(0, -1, 3),
            new Vec3i(0, -2, 3),
            new Vec3i(0, -3, 3),
            new Vec3i(1, -1, 3),
            new Vec3i(1, -2, 3),
            new Vec3i(1, -3, 3),
            // right
            new Vec3i(2, 0, 1),
            new Vec3i(2, -1, 1),
            new Vec3i(2, -2, 1),
            new Vec3i(2, -3, 1),
            new Vec3i(2, -1, 2),
            new Vec3i(2, -2, 2),
            new Vec3i(2, -3, 2),
            // left
            new Vec3i(-2, 0, 1),
            new Vec3i(-2, -1, 1),
            new Vec3i(-2, -2, 1),
            new Vec3i(-2, -3, 1),
            new Vec3i(-2, -1, 2),
            new Vec3i(-2, -2, 2),
            new Vec3i(-2, -3, 2),
            // bottom
            new Vec3i(-1, -4, 1),
            new Vec3i(0, -4, 1),
            new Vec3i(1, -4, 1),
            new Vec3i(-1, -4, 2),
            new Vec3i(0, -4, 2),
            new Vec3i(1, -4, 2)};

    private static final Vec3i[] WATER_LOCATIONS = {
            new Vec3i(-1, -3, 1),
            new Vec3i(0, -3, 1),
            new Vec3i(1, -3, 1),
            new Vec3i(-1, -3, 2),
            new Vec3i(0, -3, 2),
            new Vec3i(1, -3, 2)};

    private static final Vec3i[] AIR_LOCATIONS = {
            new Vec3i(-1, -1, 1),
            new Vec3i(0, -1, 1),
            new Vec3i(1, -1, 1),
            new Vec3i(-1, -1, 2),
            new Vec3i(0, -1, 2),
            new Vec3i(1, -1, 2),
            new Vec3i(-1, -2, 1),
            new Vec3i(0, -2, 1),
            new Vec3i(1, -2, 1),
            new Vec3i(-1, -2, 2),
            new Vec3i(0, -2, 2),
            new Vec3i(1, -2, 2),
            new Vec3i(0, 0, 3)};

    private final Vec3i[] rotatedBlocks = new Vec3i[BLOCK_LOCATIONS.length];
    private final Vec3i[] rotatedWater = new Vec3i[WATER_LOCATIONS.length];
    private final Vec3i[] rotatedAir = new Vec3i[AIR_LOCATIONS.length];


    public WishingWellEntity(BlockPos pos, BlockState state) {
        super(TheAbandonedZoneMod.WISHING_WELL_ENTITY, pos, state);

        switch (state.get(Properties.HORIZONTAL_FACING)) {
            case NORTH -> {
                for (int idx = 0; idx < BLOCK_LOCATIONS.length; idx++) { rotatedBlocks[idx] = rotateVec(BLOCK_LOCATIONS[idx], 180); }
                for (int idx = 0; idx < WATER_LOCATIONS.length; idx++) { rotatedWater[idx]  = rotateVec(WATER_LOCATIONS[idx], 180); }
                for (int idx = 0; idx < AIR_LOCATIONS.length; idx++)   { rotatedAir[idx]    = rotateVec(AIR_LOCATIONS[idx],   180); }
            }
            case EAST -> {
                for (int idx = 0; idx < BLOCK_LOCATIONS.length; idx++) { rotatedBlocks[idx] = rotateVec(BLOCK_LOCATIONS[idx], 90); }
                for (int idx = 0; idx < WATER_LOCATIONS.length; idx++) { rotatedWater[idx]  = rotateVec(WATER_LOCATIONS[idx], 90); }
                for (int idx = 0; idx < AIR_LOCATIONS.length; idx++)   { rotatedAir[idx]    = rotateVec(AIR_LOCATIONS[idx],   90); }
            }
            case WEST -> {
                for (int idx = 0; idx < BLOCK_LOCATIONS.length; idx++) { rotatedBlocks[idx] = rotateVec(BLOCK_LOCATIONS[idx], 270); }
                for (int idx = 0; idx < WATER_LOCATIONS.length; idx++) { rotatedWater[idx]  = rotateVec(WATER_LOCATIONS[idx], 270); }
                for (int idx = 0; idx < AIR_LOCATIONS.length; idx++)   { rotatedAir[idx]    = rotateVec(AIR_LOCATIONS[idx],   270); }
            }
            default -> {
                System.arraycopy(BLOCK_LOCATIONS, 0, rotatedBlocks, 0, BLOCK_LOCATIONS.length);
                System.arraycopy(WATER_LOCATIONS, 0, rotatedWater,  0, WATER_LOCATIONS.length);
                System.arraycopy(AIR_LOCATIONS,   0, rotatedAir,    0, AIR_LOCATIONS.length);
            }
        }
    }

    public static void tick (World world, BlockPos pos, BlockState state, WishingWellEntity entity) {
        if (!world.isClient) {
            boolean changed = false;
            // is this multiblock structure complete? only check periodically for performance
            if (world.getTime() % 40L == 0L) {
                boolean isValid = verifyStructure(world, pos, entity.rotatedBlocks, entity.rotatedWater, entity.rotatedAir);
                if (isValid == entity.inactive) {
                    entity.inactive = !entity.inactive;
                    world.setBlockState(pos, state.with(WishingWell.INACTIVE, entity.inactive));
                    changed = true;
                }
            }
            // if it is, examine what items are inside the well. only check periodically for performance
            if (!entity.inactive && (world.getTime() & 5L) == 0L) {
                List<ItemEntity> items;
                switch (state.get((Properties.HORIZONTAL_FACING))) {
                    case NORTH -> items = world.getEntitiesByClass(ItemEntity.class, new Box(pos.add(new Vec3i(-1, -3, -2)), pos.add(new Vec3i(2, -2, 0))), EntityPredicates.VALID_ENTITY);
                    case EAST ->  items = world.getEntitiesByClass(ItemEntity.class, new Box(pos.add(new Vec3i(1, -3, -1)),  pos.add(new Vec3i(3, -2, 2))), EntityPredicates.VALID_ENTITY);
                    case WEST ->  items = world.getEntitiesByClass(ItemEntity.class, new Box(pos.add(new Vec3i(-2, -3, -1)), pos.add(new Vec3i(0, -2, 2))), EntityPredicates.VALID_ENTITY);
                    default ->    items = world.getEntitiesByClass(ItemEntity.class, new Box(pos.add(new Vec3i(-1, -3, 1)),  pos.add(new Vec3i(2, -2, 3))), EntityPredicates.VALID_ENTITY);
                }
                if (!items.isEmpty()) {
                    ItemStack target = items.get(0).getStack();
                    // reject enchanted items and the star hammer, they're probably important to the player
                    if (target.hasEnchantments() || target.isOf(TheAbandonedZoneMod.STAR_HAMMER.asItem())) {
                        items.get(0).teleport(pos.getX() + entity.rotatedAir[12].getX() + 0.5, pos.getY() + entity.rotatedAir[12].getY() + 0.5, pos.getZ() + entity.rotatedAir[12].getZ() + 0.5);
                    // ore block
                    } else if (TheAbandonedZoneMod.WELL_SCORES.oreBlocks.containsKey(target.getItem())) {
                        int usableAmt = Math.min(MAX_ITEMS - entity.numItems, 9);
                        entity.numItems += usableAmt;
                        entity.sumItems += usableAmt * TheAbandonedZoneMod.WELL_SCORES.oreBlocks.get(target.getItem());
                        if (entity.numItems >= MAX_ITEMS) {
                            entity.spawnPrize(world, pos);
                            entity.numItems = 0;
                            entity.sumItems = 0;
                        }
                        // handle excess
                        if (usableAmt < 9) {
                            entity.numItems += 9 - usableAmt;
                            entity.sumItems += (9 - usableAmt) * TheAbandonedZoneMod.WELL_SCORES.oreBlocks.get(target.getItem());
                        }
                        target.decrement(1);
                        changed = true;
                    // special item
                    } else if (TheAbandonedZoneMod.WELL_SCORES.scoreMap.containsKey(target.getItem())) {
                        entity.numItems += 1;
                        entity.sumItems += TheAbandonedZoneMod.WELL_SCORES.scoreMap.get(target.getItem());
                        if (entity.numItems >= MAX_ITEMS) {
                            entity.spawnPrize(world, pos);
                            entity.numItems = 0;
                            entity.sumItems = 0;
                        }
                        target.decrement(1);
                        changed = true;
                    // ordinary item, assume value 1
                    } else {
                        entity.numItems += 1;
                        entity.sumItems += 1;
                        if (entity.numItems >= MAX_ITEMS) {
                            entity.spawnPrize(world, pos);
                            entity.numItems = 0;
                            entity.sumItems = 0;
                        }
                        target.decrement(1);
                        changed = true;
                    }
                }
            }
            // save changes
            if (changed) entity.markDirty();
        }
    }

    private void spawnPrize(World world, BlockPos pos) {
        ItemEntity prize;
        double spawnX = pos.getX() + rotatedAir[12].getX() + 0.5;
        double spawnY = pos.getY() + rotatedAir[12].getY() + 0.5;
        double spawnZ = pos.getZ() + rotatedAir[12].getZ() + 0.5;
        if (sumItems >= 50) {
            Item v = PRIZES.tier5[ThreadLocalRandom.current().nextInt(0, PRIZES.tier5.length)];
            prize = new ItemEntity(world, spawnX, spawnY, spawnZ, new ItemStack(v, 1), 0, 0, 0);
        } else if (sumItems >= 35) {
            Item v = PRIZES.tier4[ThreadLocalRandom.current().nextInt(0, PRIZES.tier4.length)];
            prize = new ItemEntity(world, spawnX, spawnY, spawnZ, new ItemStack(v, 10), 0, 0, 0);
        } else if (sumItems >= 30) {
            Item v = PRIZES.tier3[ThreadLocalRandom.current().nextInt(0, PRIZES.tier3.length)];
            prize = new ItemEntity(world, spawnX, spawnY, spawnZ, new ItemStack(v, 10), 0, 0, 0);
        } else if (sumItems >= 15) {
            Item v = PRIZES.tier2[ThreadLocalRandom.current().nextInt(0, PRIZES.tier2.length)];
            prize = new ItemEntity(world, spawnX, spawnY, spawnZ, new ItemStack(v, 10), 0, 0, 0);
        } else if (sumItems >= 1) {
            Item v = PRIZES.tier1[ThreadLocalRandom.current().nextInt(0, PRIZES.tier1.length)];
            prize = new ItemEntity(world, spawnX, spawnY, spawnZ, new ItemStack(v, 10), 0, 0, 0);
        } else {
            Item v = PRIZES.tier0[ThreadLocalRandom.current().nextInt(0, PRIZES.tier0.length)];
            prize = new ItemEntity(world, spawnX, spawnY, spawnZ, new ItemStack(v, 1), 0, 0, 0);
        }
        world.spawnEntity(prize);
    }

    private static boolean verifyStructure(World world, BlockPos pos, Vec3i[] blocks, Vec3i[] water, Vec3i[] air) {
        for (Vec3i neighbor : blocks) {
            if (!world.getBlockState(pos.add(neighbor)).isOf(Blocks.STONE_BRICKS)) return false;
        }
        for (Vec3i neighbor : water) {
            if (!world.isWater(pos.add(neighbor))) return false;
        }
        for (Vec3i neighbor : air) {
            if (!world.getBlockState(pos.add(neighbor)).isOf(Blocks.AIR)) return false;
        }
        return true;
    }

    private static Vec3i rotateVec(Vec3i vec, int degrees) {
        return switch (degrees) {
            case (90) -> new Vec3i(vec.getZ(), vec.getY(), -vec.getX());
            case (180) -> new Vec3i(-vec.getX(), vec.getY(), -vec.getZ());
            case (270) -> new Vec3i(-vec.getZ(), vec.getY(), vec.getX());
            default -> vec;
        };
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putShort("numItems", (short) numItems);
        nbt.putShort("sumItems", (short) sumItems);
        nbt.putBoolean("inactive", inactive);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.numItems = nbt.getShort("numItems");
        this.sumItems = nbt.getShort("sumItems");
        this.inactive = nbt.getBoolean("inactive");
    }
}
