package net.sirraisinbran.legacy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.resource.GrassColormapResourceSupplier;
import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.entity.LegacyEntities;
import net.sirraisinbran.legacy.entity.client.*;
import net.sirraisinbran.legacy.fluid.LegacyFluids;
import net.sirraisinbran.legacy.screen.BloomeryScreen;
import net.sirraisinbran.legacy.screen.IncantationScreen;
import net.sirraisinbran.legacy.screen.LegacyScreenHandlers;
import net.sirraisinbran.legacy.screen.WorkbenchScreen;
import net.sirraisinbran.legacy.util.LegacyColorProviders;

@Environment(EnvType.CLIENT)
public class LegacyClient implements ClientModInitializer {

    // Possibly redundant
    public static final Identifier LUSH_COLORMAP = new Identifier(LegacyMod.MOD_ID, "textures/colormap/grass.png");

    @Override
    public void onInitializeClient() {

        // Entity Rendering Registry
        EntityRendererRegistry.register(LegacyEntities.AUROCH_ENTITY_TYPE, AurochRenderer::new);
        EntityRendererRegistry.register(LegacyEntities.CRAULER_ENTITY_TYPE, CraulerRenderer::new);
        EntityRendererRegistry.register(LegacyEntities.CLAMOR_ENTITY_TYPE, ClamorRenderer::new);
        EntityRendererRegistry.register(LegacyEntities.DARTER_ENTITY_TYPE, DarterRenderer::new);
        EntityRendererRegistry.register(LegacyEntities.DODO_ENTITY_TYPE, DodoRenderer::new);
        EntityRendererRegistry.register(LegacyEntities.GLARE_ENTITY_TYPE, GlareRenderer::new);
        EntityRendererRegistry.register(LegacyEntities.SHROUD_ENTITY_TYPE, ShroudRenderer::new);
        EntityRendererRegistry.register(LegacyEntities.SPORE_ENTITY_TYPE, SporeRenderer::new);

        // Block Entity Rendering Registry
        ScreenRegistry.register(LegacyScreenHandlers.BLOOMERY_SCREEN_HANDLER, BloomeryScreen::new);
        ScreenRegistry.register(LegacyScreenHandlers.INCANTATION_SCREEN_HANDLER, IncantationScreen::new);
        ScreenRegistry.register(LegacyScreenHandlers.WORKBENCH_SCREEN_HANDLER, WorkbenchScreen::new);

        // Fluids

        // Possibly redundant
        LegacyColorProviders.initClient();
    }
}
