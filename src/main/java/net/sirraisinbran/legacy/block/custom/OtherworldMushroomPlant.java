package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.util.LegacyTags;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

public class OtherworldMushroomPlant extends PlantBlock implements Fertilizable {

    protected static final float field_31195 = 3.0F;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);
    private final Supplier<RegistryEntry<? extends ConfiguredFeature<?, ?>>> feature;

    public OtherworldMushroomPlant(AbstractBlock.Settings settings, Supplier<RegistryEntry<? extends ConfiguredFeature<?, ?>>> feature) {
        super(settings);
        this.feature = feature;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(25) == 0) {
            int i = 5;
            int j = 2;
            Iterator var7 = BlockPos.iterate(pos.add(-4, -1, -4), pos.add(4, 1, 4)).iterator();

            while(var7.hasNext()) {
                BlockPos blockPos = (BlockPos)var7.next();
                if (world.getBlockState(blockPos).isOf(this)) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos blockPos2 = pos.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

            for(int k = 0; k < 4; ++k) {
                if (world.isAir(blockPos2) && state.canPlaceAt(world, blockPos2)) {
                    pos = blockPos2;
                }

                blockPos2 = pos.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            }

            if (world.isAir(blockPos2) && state.canPlaceAt(world, blockPos2)) {
                world.setBlockState(blockPos2, state, 2);
            }
        }

    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }

    public boolean trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        world.removeBlock(pos, false);
        if (((ConfiguredFeature)((RegistryEntry)this.feature.get()).value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos)) {
            return true;
        } else {
            world.setBlockState(pos, state, 3);
            return false;
        }
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)random.nextFloat() < 0.4;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.trySpawningBigMushroom(world, pos, state, random);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(LegacyMod.SOIL) || floor.isOf(LegacyBlocks.DRY_SAND) || floor.isOf(LegacyBlocks.LUSH_GABBRO);
    }
}
