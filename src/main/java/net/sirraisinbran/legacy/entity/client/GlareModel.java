package net.sirraisinbran.legacy.entity.client;

import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import net.sirraisinbran.legacy.entity.custom.GlareEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GlareModel extends AnimatedGeoModel<GlareEntity> {
    @Override
    public Identifier getModelLocation(GlareEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "geo/glare.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GlareEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/glare/glare.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GlareEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "animations/glare.animation.json");
    }
}