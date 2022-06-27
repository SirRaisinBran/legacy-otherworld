package net.sirraisinbran.legacy.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;

public class LegacyRegistries {

    public static void registerModStuffs() {

        registerAttributes();
        registerStrippables();

    }

    private static void registerAttributes() {


    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(LegacyBlocks.ARAUCARIA_LOG, LegacyBlocks.STRIPPED_ARAUCARIA_LOG);
        StrippableBlockRegistry.register(LegacyBlocks.CLADOXY_LOG, LegacyBlocks.STRIPPED_CLADOXY_LOG);
        StrippableBlockRegistry.register(LegacyBlocks.KOKIA_LOG, LegacyBlocks.STRIPPED_KOKIA_LOG);
    }
}
