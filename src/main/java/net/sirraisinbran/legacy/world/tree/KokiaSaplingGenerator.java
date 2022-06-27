package net.sirraisinbran.legacy.world.tree;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.sirraisinbran.legacy.world.configured_features.OtherworldTreeConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class KokiaSaplingGenerator extends SaplingGenerator {
    @Override
    protected @Nullable RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bl) {
        return OtherworldTreeConfiguredFeatures.KOKIA_TREE;
    }
}
