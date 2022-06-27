package net.sirraisinbran.legacy.entity.client;

import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.CraulerEntity;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CraulerModel extends AnimatedGeoModel<CraulerEntity> {
    @Override
    public Identifier getModelLocation(CraulerEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "geo/crauler.geo.json");
    }

    @Override
    public Identifier getTextureLocation(CraulerEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/crauler/crauler.png");
    }

    @Override
    public Identifier getAnimationFileLocation(CraulerEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "animations/crauler.animation.json");
    }
}