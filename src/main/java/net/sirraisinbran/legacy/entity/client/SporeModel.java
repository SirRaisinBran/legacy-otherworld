package net.sirraisinbran.legacy.entity.client;

import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.SporeEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SporeModel extends AnimatedGeoModel<SporeEntity> {
    @Override
    public Identifier getModelLocation(SporeEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "geo/scarlet_spore.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SporeEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/scarlet_spore/scarlet_spore.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SporeEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "animations/spore.animation.json");
    }
}