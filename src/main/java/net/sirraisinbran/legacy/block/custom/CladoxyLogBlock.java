/*

package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;
import net.sirraisinbran.legacy.util.LegacyProperties;
import net.sirraisinbran.legacy.util.LogShape;

import java.util.Random;

public class CladoxyLogBlock extends PillarBlock implements Waterloggable{

    public static final DirectionProperty FACING;
    public static final EnumProperty<LogShape> SHAPE;
    public static final BooleanProperty WATERLOGGED;
    private final Block baseBlock;
    private final BlockState baseBlockState;

    public CladoxyLogBlock(BlockState baseBlockState, AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH))).with(SHAPE, LogShape.STRAIGHT)).with(WATERLOGGED, false));
        this.baseBlock = baseBlockState.getBlock();
        this.baseBlockState = baseBlockState;
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        this.baseBlock.randomDisplayTick(state, world, pos, random);
    }

    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        this.baseBlockState.onBlockBreakStart(world, pos, player);
    }

    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        this.baseBlock.onBroken(world, pos, state);
    }

    public float getBlastResistance() {
        return this.baseBlock.getBlastResistance();
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!state.isOf(state.getBlock())) {
            this.baseBlockState.neighborUpdate(world, pos, Blocks.AIR, pos, false);
            this.baseBlock.onBlockAdded(this.baseBlockState, world, pos, oldState, false);
        }
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            this.baseBlockState.onStateReplaced(world, pos, newState, moved);
        }
    }

    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        this.baseBlock.onSteppedOn(world, pos, state, entity);
    }

    public boolean hasRandomTicks(BlockState state) {
        return this.baseBlock.hasRandomTicks(state);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.baseBlock.randomTick(state, world, pos, random);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.baseBlock.scheduledTick(state, world, pos, random);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return this.baseBlockState.onUse(world, player, hand, hit);
    }

    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        this.baseBlock.onDestroyedByExplosion(world, pos, explosion);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        BlockState blockState = (BlockState)((BlockState)((BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing()))).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        return (BlockState)blockState.with(SHAPE, getLogShape(blockState, ctx.getWorld(), blockPos));
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return direction.getAxis().isHorizontal() ? (BlockState)state.with(SHAPE, getLogShape(state, world, pos)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private static LogShape getLogShape(BlockState state, BlockView world, BlockPos pos) {
        Direction direction = (Direction)state.get(FACING);
        BlockState blockState = world.getBlockState(pos.offset(direction));
        if (isLog(blockState)) {
            Direction direction2 = (Direction)blockState.get(FACING);
            if (direction2.getAxis() != ((Direction)state.get(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction2.getOpposite())) {
                if (direction2 == direction.rotateYCounterclockwise()) {
                    return LogShape.TOP_LEFT;
                }

                return LogShape.TOP_RIGHT;
            }
        }

        BlockState blockState2 = world.getBlockState(pos.offset(direction.getOpposite()));
        if (isLog(blockState2)) {
            Direction direction3 = (Direction)blockState2.get(FACING);
            if (direction3.getAxis() != ((Direction)state.get(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction3)) {
                if (direction3 == direction.rotateYCounterclockwise()) {
                    return LogShape.BOTTOM_LEFT;
                }

                return LogShape.BOTTOM_RIGHT;
            }
        }

        return LogShape.STRAIGHT;
    }

    private static boolean isDifferentOrientation(BlockState state, BlockView world, BlockPos pos, Direction dir) {
        BlockState blockState = world.getBlockState(pos.offset(dir));
        return !isLog(blockState) || blockState.get(FACING) != state.get(FACING);
    }

    public static boolean isLog(BlockState state) {
        return state.getBlock() instanceof CladoxyLogBlock;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        Direction direction = (Direction)state.get(FACING);
        LogShape logShape = (LogShape)state.get(SHAPE);
        switch (mirror) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {
                    switch (logShape) {
                        case BOTTOM_LEFT:
                            return (BlockState)state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, LogShape.BOTTOM_RIGHT);
                        case BOTTOM_RIGHT:
                            return (BlockState)state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, LogShape.BOTTOM_LEFT);
                        case TOP_LEFT:
                            return (BlockState)state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, LogShape.TOP_RIGHT);
                        case TOP_RIGHT:
                            return (BlockState)state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, LogShape.TOP_LEFT);
                        default:
                            return state.rotate(BlockRotation.CLOCKWISE_180);
                    }
                }
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {
                    switch (logShape) {
                        case BOTTOM_LEFT:
                            return (BlockState)state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, LogShape.BOTTOM_LEFT);
                        case BOTTOM_RIGHT:
                            return (BlockState)state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, LogShape.BOTTOM_RIGHT);
                        case TOP_LEFT:
                            return (BlockState)state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, LogShape.TOP_RIGHT);
                        case TOP_RIGHT:
                            return (BlockState)state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, LogShape.TOP_LEFT);
                        case STRAIGHT:
                            return state.rotate(BlockRotation.CLOCKWISE_180);
                    }
                }
        }

        return super.mirror(state, mirror);
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        SHAPE = LegacyProperties.LOG_SHAPE;
        WATERLOGGED = Properties.WATERLOGGED;
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, SHAPE, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}


 */