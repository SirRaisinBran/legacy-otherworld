package net.sirraisinbran.legacy.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.CraulerEntity;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CraulerRenderer extends GeoEntityRenderer<CraulerEntity> {
    public CraulerRenderer(EntityRendererFactory.Context context) {
        super(context, new CraulerModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(CraulerEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/crauler/crauler.png");
    }
}