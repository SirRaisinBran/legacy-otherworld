package net.sirraisinbran.legacy.world.placed_features;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.world.configured_features.OtherworldTreeConfiguredFeatures;

import java.util.List;
import static net.sirraisinbran.legacy.block.LegacyBlocks.*;

public class OtherworldTreePlacedFeatures extends OtherworldPlacedFeatures {

    public static final List<Block> OTHERWORLD_GROUND = List.of(SOIL, LUSH_SOIL, ROCKY_SOIL, DRY_SAND, DENSE_CLAY);

    public static final RegistryEntry<PlacedFeature> ARAUCARIA_PLACED = PlacedFeatures.register("araucaria_placed",
            OtherworldTreeConfiguredFeatures.ARAUCARIA_TREE, PlacedFeatures.wouldSurvive(LegacyBlocks.ARAUCARIA_SAPLING));

    public static final RegistryEntry<PlacedFeature> CLADOXY_PLACED = PlacedFeatures.register("cladoxy_placed",
            OtherworldTreeConfiguredFeatures.CLADOXY_TREE, PlacedFeatures.wouldSurvive(LegacyBlocks.CLADOXY_SAPLING));

    public static final RegistryEntry<PlacedFeature> KOKIA_PLACED = PlacedFeatures.register("kokia_placed",
            OtherworldTreeConfiguredFeatures.KOKIA_TREE, PlacedFeatures.wouldSurvive(LegacyBlocks.KOKIA_SAPLING));

    public static void init(){}
}
