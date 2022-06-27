package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class TightropeBlock extends PillarBlock implements Waterloggable {

    public static final BooleanProperty BOTTOM;
    public static final BooleanProperty WATERLOGGED;
    protected static final float field_31054 = 6.5F;
    protected static final float field_31055 = 9.5F;
    protected static final VoxelShape Y_SHAPE;
    protected static final VoxelShape Z_SHAPE;
    protected static final VoxelShape X_SHAPE;

    public TightropeBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(WATERLOGGED, false)).with(AXIS, Direction.Axis.Y)).with(BOTTOM, false));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch ((Direction.Axis)state.get(AXIS)) {
            case X:
            default:
                return X_SHAPE;
            case Z:
                return Z_SHAPE;
            case Y:
                return Y_SHAPE;
        }
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().down());
        boolean bl2 = blockState.isAir();
        return (BlockState)super.getPlacementState(ctx).with(WATERLOGGED, bl).with(BOTTOM, bl2);
    }


    private boolean isBottom(BlockState state, BlockView world, BlockPos pos){
        BlockState blockState = world.getBlockState(pos.down());
        return blockState.isAir();
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return direction.getAxis().isVertical() ? (BlockState)state.with(BOTTOM, isBottom(state, world, pos)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{WATERLOGGED}).add(new Property[]{AXIS}).add(new Property[]{BOTTOM});
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        BOTTOM = Properties.BOTTOM;
        WATERLOGGED = Properties.WATERLOGGED;
        Y_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 16.0, 9.0);
        Z_SHAPE = Block.createCuboidShape(7.0, 7.0, 0.0, 9.0, 9.0, 16.0);
        X_SHAPE = Block.createCuboidShape(0.0, 7.0, 7.0, 16.0, 9.0, 9.0);
    }
}
