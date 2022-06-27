package net.sirraisinbran.legacy.entity.client;

import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.AurochEntity;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import net.sirraisinbran.legacy.entity.custom.ShroudEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ShroudModel extends AnimatedGeoModel<ShroudEntity> {
    @Override
    public Identifier getModelLocation(ShroudEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "geo/shroud.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ShroudEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/shroud/shroud.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ShroudEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "animations/shroud.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(ShroudEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}