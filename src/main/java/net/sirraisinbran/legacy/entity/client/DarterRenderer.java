package net.sirraisinbran.legacy.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.DarterEntity;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DarterRenderer extends GeoEntityRenderer<DarterEntity> {
    public DarterRenderer(EntityRendererFactory.Context context) {
        super(context, new DarterModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(DarterEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/darter/darter.png");
    }
}