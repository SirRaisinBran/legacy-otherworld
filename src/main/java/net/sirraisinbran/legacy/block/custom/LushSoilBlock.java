package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;

import java.util.Random;

public class LushSoilBlock extends GrassBlock implements Fertilizable{

    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);

    public LushSoilBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public boolean isScarlet(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.up()).isIn(LegacyMod.SCARLET);
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.up()).isAir()
                || world.getBlockState(pos.up()).isIn(LegacyMod.OW_FOLIAGE)
                || world.getBlockState(pos.up()).isIn(BlockTags.SIGNS)
                || world.getBlockState(pos.up()).isOf(Blocks.TORCH)
                || world.getBlockState(pos.up()).isIn(LegacyMod.SCARLET);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) { return true; }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) { }

    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos){
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.getFluidState().getLevel() == 8) {
            return false;
        } else {
            int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos){
        BlockPos blockPos = pos.up();
        return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        canSpread(state, world, pos);
        canSurvive(state, world, pos);
        isScarlet(world, pos, state, true);
        isFertilizable(world, pos, state, true);
        if (!canSurvive(state, world, pos)) {
            world.setBlockState(pos, LegacyBlocks.SOIL.getDefaultState());
        } else if (!isFertilizable(world, pos, state, true)) {
            world.setBlockState(pos, LegacyBlocks.SOIL.getDefaultState());
        } else if (isScarlet(world, pos, state, true)) {
            for (int i = 0; i <= 5000; i++) {
                if(i >= 5000){
                    for (int h = 0; h <= 5000; h++) {
                        if(h >= 5000){
                            world.setBlockState(pos, LegacyBlocks.DEAD_LUSH.getDefaultState());
                        }
                    }
                }
            }
        } else {
            if (world.getLightLevel(pos.up()) >= 9) {
                BlockState blockState = this.getDefaultState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (world.getBlockState(blockPos).isOf(LegacyBlocks.SOIL) && canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos, (BlockState)blockState);
                    }
                }
            }

        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

}
