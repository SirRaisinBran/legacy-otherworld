package net.sirraisinbran.legacy.world.configured_features;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.CocoaBeansTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.*;

import java.util.List;

import static net.sirraisinbran.legacy.LegacyMod.locate;
import static net.sirraisinbran.legacy.block.LegacyBlocks.*;

public class OtherworldTreeConfiguredFeatures extends OtherworldConfiguredFeatures{

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ARAUCARIA_TREE =
            ConfiguredFeatures.register("araucaria_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ARAUCARIA_LOG),
                    new StraightTrunkPlacer(8, 2, 0),
                    BlockStateProvider.of(ARAUCARIA_LEAVES),
                    new SpruceFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), ConstantIntProvider.create(4)),
                    new TwoLayersFeatureSize(1, 0, 2))
                    .dirtProvider(BlockStateProvider.of(SOIL)).build());


    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CLADOXY_TREE =
            ConfiguredFeatures.register("cladoxy_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(CLADOXY_LOG),
                    new LargeOakTrunkPlacer(15, 2, 3),
                    BlockStateProvider.of(CLADOXY_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2),
                    new TwoLayersFeatureSize(1, 0, 2))
                    .dirtProvider(BlockStateProvider.of(SOIL)).build());


    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> KOKIA_TREE =
            ConfiguredFeatures.register("kokia_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(KOKIA_LOG),
                    new ForkingTrunkPlacer(2, 3, 5),
                    BlockStateProvider.of(KOKIA_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0), 2),
                    new TwoLayersFeatureSize(1, 0, 2))
                    .dirtProvider(BlockStateProvider.of(SOIL)).build());


    public static void init(){}
}
