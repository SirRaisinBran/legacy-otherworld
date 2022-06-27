package net.sirraisinbran.legacy.entity.client;

import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.AurochEntity;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class AurochModel extends AnimatedGeoModel<AurochEntity> {
    @Override
    public Identifier getModelLocation(AurochEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "geo/auroch.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AurochEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/auroch/auroch.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AurochEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "animations/auroch.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(AurochEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}