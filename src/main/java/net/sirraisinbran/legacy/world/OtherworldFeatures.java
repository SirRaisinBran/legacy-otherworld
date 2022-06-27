package net.sirraisinbran.legacy.world;

import net.sirraisinbran.legacy.world.configured_features.OtherworldConfiguredFeatures;
import net.sirraisinbran.legacy.world.placed_features.OtherworldPlacedFeatures;

public class OtherworldFeatures {

    public static void init() {
        OtherworldConfiguredFeatures.init();
        OtherworldPlacedFeatures.init();
    }
}
