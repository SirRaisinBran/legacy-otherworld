package net.sirraisinbran.legacy.block.custom;

import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.block.entity.LegacyBlockEntities;
import net.sirraisinbran.legacy.block.entity.ScarletBrainEntity;
import org.lwjgl.system.CallbackI;

import java.util.List;
import java.util.Random;

public class ScarletBrain extends BlockWithEntity implements BlockEntityProvider  {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

    Random random = new Random();
    public boolean bottomBlock;

    public ScarletBrain(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ScarletBrainEntity(pos, state);
    }

    public void findBottomBlock(BlockState state, BlockView world, BlockPos pos, ScarletBrain block) {
        boolean northBlock = world.getBlockState(pos.north()).isIn(LegacyMod.SCARLET);
        boolean westBlock = world.getBlockState(pos.west()).isIn(LegacyMod.SCARLET);
        boolean southBlock = world.getBlockState(pos.south()).isIn(LegacyMod.SCARLET);
        boolean eastBlock = world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET);
        if(northBlock && westBlock && southBlock && eastBlock) {
            block.bottomBlock = false;
        } else {
            block.bottomBlock = world.getBlockState(pos.down()).isOf(LegacyBlocks.SOIL);
        }
    }


    private boolean canSpread(World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ScarletBrainEntity scarletBrainEntity) {
            return scarletBrainEntity.canSpread;
        } else {
            return false;
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(DamageSource.GENERIC, 1.0F);
    }


    public void tickMethod(BlockState state, ServerWorld world, BlockPos pos, ScarletBrain block){
        findBottomBlock(state, world, pos, block);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        //Spreading
        if (blockEntity instanceof ScarletBrainEntity be) {
            if (!canSpread(world, pos)) {
                if (world.getBlockState(pos.north().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.north()).isAir() || world.getBlockState(pos.north()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.north(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
                if (world.getBlockState(pos.west().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.west()).isAir() || world.getBlockState(pos.west()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.west(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
                if (world.getBlockState(pos.south().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.south()).isAir() || world.getBlockState(pos.south()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.south(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
                if (world.getBlockState(pos.east().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.east()).isAir() || world.getBlockState(pos.east()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.east(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
            } else if (canSpread(world, pos)){
                if (world.getBlockState(pos.north().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.north()).isAir() || world.getBlockState(pos.north()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.north(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
                if (world.getBlockState(pos.west().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.west()).isAir() || world.getBlockState(pos.west()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.west(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
                if (world.getBlockState(pos.south().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.south()).isAir() || world.getBlockState(pos.south()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.south(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
                if (world.getBlockState(pos.east().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.east()).isAir() || world.getBlockState(pos.east()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.east(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
            }
            tickMethod(state, world, pos, this);
            if(bottomBlock) {
                be.decreaseSpread(world, pos, be);
                world.breakBlock(pos, false);
            }
        }
        if (!world.getBlockState(pos.down()).isIn(LegacyMod.ORGANIC) && !world.getBlockState(pos.down()).isOf(LegacyBlocks.SOIL) && !world.getBlockState(pos.down()).isOf(LegacyBlocks.GABBRO) && !world.getBlockState(pos.down()).isIn(LegacyMod.DEAD_LUSH_BLOCKS)){
            world.breakBlock(pos, false);
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, LegacyBlockEntities.SCARLET_BRAIN_BLOCK_ENTITY, ScarletBrainEntity::tick);
    }
}
