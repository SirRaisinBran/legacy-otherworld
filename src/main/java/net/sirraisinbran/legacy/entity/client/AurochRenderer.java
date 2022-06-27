package net.sirraisinbran.legacy.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.AurochEntity;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AurochRenderer extends GeoEntityRenderer<AurochEntity> {
    public AurochRenderer(EntityRendererFactory.Context context) {
        super(context, new AurochModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(AurochEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/auroch/auroch.png");
    }
}