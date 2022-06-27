package net.sirraisinbran.legacy.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.ClamorEntity;
import net.sirraisinbran.legacy.entity.custom.CraulerEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ClamorRenderer extends GeoEntityRenderer<ClamorEntity> {
    public ClamorRenderer(EntityRendererFactory.Context context) {
        super(context, new ClamorModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(ClamorEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/clamor/clamor.png");
    }
}