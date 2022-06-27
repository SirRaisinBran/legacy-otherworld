package net.sirraisinbran.legacy.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;

import static net.sirraisinbran.legacy.block.LegacyBlocks.LUSH_SOIL;
import static net.sirraisinbran.legacy.block.LegacyBlocks.LUSH_GABBRO;

@Environment(EnvType.CLIENT)
public class LegacyColorProviders {

    public static void initClient() {
        initBlocks();
        initItems();
    }

    private static void initBlocks() {
        ColorProviderRegistryImpl.BLOCK.register(((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D)), LUSH_SOIL);
        ColorProviderRegistryImpl.BLOCK.register(((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D)), LUSH_GABBRO);
    }

    private static void initItems() {

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x3fb58e, LUSH_SOIL);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x3fb58e, LUSH_GABBRO);
    }
}
