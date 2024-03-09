package com.xaklor.mobs;

import com.xaklor.TheAbandonedZoneMod;
import com.xaklor.TheAbandonedZoneModClient;
import com.xaklor.util.mobs.ZombieKingEntity;
import net.minecraft.client.render.entity.*;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class ZombieKingEntityRenderer extends ZombieBaseEntityRenderer<ZombieKingEntity, ZombieKingEntityModel<ZombieKingEntity>> {

    private static final Identifier TEXTURE = new Identifier(TheAbandonedZoneMod.MOD_ID, "textures/entity/zombie_king/zombie_king.png");

    public ZombieKingEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ZombieKingEntityModel<>(context.getPart(TheAbandonedZoneModClient.ZOMBIE_KING_MODEL)),
                new ZombieKingEntityModel<>(context.getPart(TheAbandonedZoneModClient.ZOMBIE_KING_INNER_ARMOR)),
                new ZombieKingEntityModel<>(context.getPart(TheAbandonedZoneModClient.ZOMBIE_KING_OUTER_ARMOR)));
        this.addFeature(new ZombieKingOverlayFeatureRenderer<>(this, context.getModelLoader()));
    }

    @Override
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
