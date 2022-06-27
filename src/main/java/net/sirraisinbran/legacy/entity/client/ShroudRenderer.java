package net.sirraisinbran.legacy.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import net.sirraisinbran.legacy.entity.custom.ShroudEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShroudRenderer extends GeoEntityRenderer<ShroudEntity> {
    public ShroudRenderer(EntityRendererFactory.Context context) {
        super(context, new ShroudModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(ShroudEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/shroud/shroud.png");
    }
}