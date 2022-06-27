package net.sirraisinbran.legacy.entity.client;

import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class DodoModel extends AnimatedGeoModel<DodoEntity> {
    @Override
    public Identifier getModelLocation(DodoEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "geo/dodo.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DodoEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/dodo/dodo.png");
    }

    @Override
    public Identifier getAnimationFileLocation(DodoEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "animations/dodo.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(DodoEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}