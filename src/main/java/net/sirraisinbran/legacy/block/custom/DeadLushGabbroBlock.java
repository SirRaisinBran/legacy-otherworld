package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.sirraisinbran.legacy.block.LegacyBlocks;

import java.util.Random;

public class DeadLushGabbroBlock extends Block {

    public DeadLushGabbroBlock(Settings settings) {
        super(settings);
    }

    public boolean airAbove(BlockView world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.up()).isAir();
    }

    public boolean isScarlet(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.up()).isOf(LegacyBlocks.SCARLET_MUSHROOM);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(10) == 0) {
            world.addParticle(ParticleTypes.MYCELIUM, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.1, (double)pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        }

    }

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
        if (!isScarlet(world, pos, state, true)) {
            if (!canSurvive(state, world, pos)) {
                world.setBlockState(pos, LegacyBlocks.GABBRO.getDefaultState());
            } else if (!airAbove(world, pos, state)) {
                world.setBlockState(pos, LegacyBlocks.GABBRO.getDefaultState());
            } else {
                if (world.getLightLevel(pos.up()) >= 9) {
                    BlockState blockState = this.getDefaultState();

                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (world.getBlockState(blockPos).isOf(LegacyBlocks.GABBRO) && canSpread(blockState, world, blockPos)) {
                            world.setBlockState(blockPos, (BlockState) blockState);
                        }
                    }
                }

            }
        }
    }

}
