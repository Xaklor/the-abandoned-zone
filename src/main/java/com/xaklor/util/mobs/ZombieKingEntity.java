package com.xaklor.util.mobs;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;

public class ZombieKingEntity extends ZombieEntity {

    public ZombieKingEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 8;
    }

    public static DefaultAttributeContainer.Builder createZombieKingAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 9.0).add(EntityAttributes.GENERIC_ARMOR, 2.0).add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public void setBaby(boolean baby) {  }

    // almost all of this is directly copied from ZombieEntity, the only difference is the king is penalized harder for reinforcements
    // reinforcements will happen twice on hard because of the call to super, but I don't think I care
    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!super.damage(source, amount)) {
            return false;
        }
        if (!(this.getWorld() instanceof ServerWorld serverWorld)) {
            return false;
        }
        LivingEntity livingEntity = this.getTarget();
        if (livingEntity == null && source.getAttacker() instanceof LivingEntity) {
            livingEntity = (LivingEntity)source.getAttacker();
        }
        if (livingEntity != null && (double)this.random.nextFloat() < this.getAttributeValue(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS)) {
            int i = MathHelper.floor(this.getX());
            int j = MathHelper.floor(this.getY());
            int k = MathHelper.floor(this.getZ());
            ZombieEntity zombieEntity = new ZombieEntity(this.getWorld());
            for (int l = 0; l < 50; ++l) {
                int m = i + MathHelper.nextInt(this.random, 7, 10) * MathHelper.nextInt(this.random, -1, 1);
                int n = j + MathHelper.nextInt(this.random, 7, 10) * MathHelper.nextInt(this.random, -1, 1);
                int o = k + MathHelper.nextInt(this.random, 7, 10) * MathHelper.nextInt(this.random, -1, 1);
                BlockPos blockPos = new BlockPos(m, n, o);
                EntityType<?> entityType = zombieEntity.getType();
                SpawnRestriction.Location location = SpawnRestriction.getLocation(entityType);
                if (!SpawnHelper.canSpawn(location, this.getWorld(), blockPos, entityType) || !SpawnRestriction.canSpawn(entityType, serverWorld, SpawnReason.REINFORCEMENT, blockPos, this.getWorld().random)) continue;
                zombieEntity.setPosition(m, n, o);
                if (this.getWorld().isPlayerInRange(m, n, o, 7.0) || !this.getWorld().doesNotIntersectEntities(zombieEntity) || !this.getWorld().isSpaceEmpty(zombieEntity) || this.getWorld().containsFluid(zombieEntity.getBoundingBox())) continue;
                zombieEntity.setTarget(livingEntity);
                zombieEntity.initialize(serverWorld, this.getWorld().getLocalDifficulty(zombieEntity.getBlockPos()), SpawnReason.REINFORCEMENT, null, null);
                serverWorld.spawnEntityAndPassengers(zombieEntity);
                this.getAttributeInstance(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS).addPersistentModifier(new EntityAttributeModifier("Zombie reinforcement caller charge", -1f, EntityAttributeModifier.Operation.ADDITION));
                zombieEntity.getAttributeInstance(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS).addPersistentModifier(new EntityAttributeModifier("Zombie reinforcement callee charge", -0.05f, EntityAttributeModifier.Operation.ADDITION));
                break;
            }
        }
        return true;
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
    }

    @Override
    public boolean canPickupItem(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canGather(ItemStack stack) {
        return false;
    }

    @Override
    protected void applyAttributeModifiers(float chanceMultiplier) {
        this.initAttributes();
        this.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).addPersistentModifier(new EntityAttributeModifier("Random spawn bonus", this.random.nextDouble() * (double)0.05f, EntityAttributeModifier.Operation.ADDITION));
        double d = this.random.nextDouble() * 1.5 * (double)chanceMultiplier;
        if (d > 1.0) {
            this.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).addPersistentModifier(new EntityAttributeModifier("Random zombie-spawn bonus", d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        if (this.random.nextFloat() < chanceMultiplier * 0.05f) {
            this.setCanBreakDoors(this.shouldBreakDoors());
        }
    }

    @Override
    protected void initAttributes() {
        this.getAttributeInstance(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(1);
    }

    @Override
    protected ItemStack getSkull() {
        return new ItemStack(TheAbandonedZoneMod.ROYAL_HEART);
    }
}
