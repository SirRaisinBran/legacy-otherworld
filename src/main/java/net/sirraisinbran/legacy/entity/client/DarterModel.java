package net.sirraisinbran.legacy.entity.client;

import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.DarterEntity;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DarterModel extends AnimatedGeoModel<DarterEntity> {
    @Override
    public Identifier getModelLocation(DarterEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "geo/darter.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DarterEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/darter/darter.png");
    }

    @Override
    public Identifier getAnimationFileLocation(DarterEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "animations/darter.animation.json");
    }
}