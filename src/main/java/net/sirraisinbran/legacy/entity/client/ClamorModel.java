package net.sirraisinbran.legacy.entity.client;

import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.ClamorEntity;
import net.sirraisinbran.legacy.entity.custom.CraulerEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ClamorModel extends AnimatedGeoModel<ClamorEntity> {
    @Override
    public Identifier getModelLocation(ClamorEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "geo/clamor.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ClamorEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/clamor/clamor.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ClamorEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "animations/clamor.animation.json");
    }
}