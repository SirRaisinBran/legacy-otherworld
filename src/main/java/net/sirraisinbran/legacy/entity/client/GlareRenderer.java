package net.sirraisinbran.legacy.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import net.sirraisinbran.legacy.entity.custom.GlareEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GlareRenderer extends GeoEntityRenderer<GlareEntity> {
    public GlareRenderer(EntityRendererFactory.Context context) {
        super(context, new GlareModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(GlareEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/glare/glare.png");
    }
}