package net.sirraisinbran.legacy.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import org.lwjgl.system.CallbackI;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DodoRenderer extends GeoEntityRenderer<DodoEntity> {
    public DodoRenderer(EntityRendererFactory.Context context) {
        super(context, new DodoModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(DodoEntity entity) {
        return new Identifier(LegacyMod.MOD_ID, "textures/entity/dodo/dodo.png");
    }

    @Override
    public RenderLayer getRenderType(DodoEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        if (animatable.isBaby()) {
            stack.scale(0.5f, 0.5f, 0.5f);
        } else {
            stack.scale(1.0f, 1.0f, 1.0f);
        }

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}