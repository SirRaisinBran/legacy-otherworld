package net.sirraisinbran.legacy.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.GlareEntity;
import net.sirraisinbran.legacy.entity.custom.SporeEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SporeRenderer extends GeoEntityRenderer<SporeEntity> {
    public SporeRenderer(EntityRendererFactory.Context context) {
        super(context, new SporeModel());
        this.shadowRadius = 0f;
    }

    @Override
    public Identifier getTextureLocation(SporeEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/scarlet_spore/scarlet_spore.png");
    }
}