package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;

import java.util.function.Supplier;

public class WhiteShroomCeilingBlock extends OtherworldMushroomPlant {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0D, 6.5D, 5.0D, 11.0D, 16.0D, 11.0D);

    public WhiteShroomCeilingBlock(Settings settings, Supplier<RegistryEntry<? extends ConfiguredFeature<?, ?>>> feature) {
        super(settings, feature);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return this.canPlantUnder(world.getBlockState(blockPos), world, blockPos);
    }

    protected boolean canPlantUnder(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(LegacyMod.SOIL) || floor.isOf(LegacyBlocks.DRY_SAND) || floor.isOf(LegacyBlocks.LUSH_GABBRO) || floor.isOf(LegacyBlocks.GABBRO);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}