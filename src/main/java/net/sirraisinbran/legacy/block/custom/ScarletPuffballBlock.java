package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.entity.LegacyEntities;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;
import net.sirraisinbran.legacy.entity.custom.SporeEntity;
import net.sirraisinbran.legacy.util.LegacyProperties;
import net.sirraisinbran.legacy.util.ScarletMushroomShape;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Random;

public class ScarletPuffballBlock extends Block {

    protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);
    public static final IntProperty AGE;

    public ScarletPuffballBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.getStateManager().getDefaultState()).with(AGE, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return TOP_SHAPE;
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Iterator var4 = Direction.Type.HORIZONTAL.iterator();

        Direction direction;
        Material material;
        do {
            if (!var4.hasNext()) {
                BlockState blockState2 = world.getBlockState(pos.down());
                return blockState2.isOf(LegacyBlocks.SCARLET_MUSHROOM) && !world.getBlockState(pos.up()).getMaterial().isLiquid();
            }

            direction = (Direction)var4.next();
            BlockState blockState = world.getBlockState(pos.offset(direction));
            material = blockState.getMaterial();
        } while(!material.isSolid() && !world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA));

        return false;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void puffSpore(BlockState state, ServerWorld world, BlockPos pos, Random random){
        BlockPos pos2 = pos.up(1);
        world.playSound((PlayerEntity)null, pos2, SoundEvents.BLOCK_DISPENSER_DISPENSE, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
        SporeEntity sporeEntity = LegacyEntities.SPORE_ENTITY_TYPE.create(world);
        world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
        sporeEntity.refreshPositionAndAngles(pos2.getX(), pos2.getY(), pos2.getZ(), 0F, 0F);
        sporeEntity.setVelocity(sporeEntity.getVelocity().add(0.0, 0.3, 0.0));
        world.spawnEntity(sporeEntity);

        world.playSound((PlayerEntity)null, pos2, SoundEvents.BLOCK_DISPENSER_DISPENSE, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
        SporeEntity sporeEntity2 = LegacyEntities.SPORE_ENTITY_TYPE.create(world);
        world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
        sporeEntity2.refreshPositionAndAngles(pos2.getX(), pos2.getY(), pos2.getZ(), 0F, 0F);
        sporeEntity2.setVelocity(sporeEntity2.getVelocity().add(0.0, 0.32, 0.0));
        world.spawnEntity(sporeEntity2);

        world.playSound((PlayerEntity)null, pos2, SoundEvents.BLOCK_DISPENSER_DISPENSE, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
        SporeEntity sporeEntity3 = LegacyEntities.SPORE_ENTITY_TYPE.create(world);
        world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
        sporeEntity3.refreshPositionAndAngles(pos2.getX(), pos2.getY(), pos2.getZ(), 0F, 0F);
        sporeEntity3.setVelocity(sporeEntity3.getVelocity().add(0.0, 0.42, 0.0));
        world.spawnEntity(sporeEntity3);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
        puffSpore(state, world, pos, random);
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        puffSpore(state, world, pos, random);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    static {
        AGE = LegacyProperties.AGE_4;
    }
}
