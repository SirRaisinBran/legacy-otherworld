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
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.util.ScarletMushroomShape;
import org.jetbrains.annotations.Nullable;

public class CyanescenStemBlock extends Block implements Waterloggable {

    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape Y_SHAPE;
    protected static final VoxelShape FULL_SHAPE;
    protected static final VoxelShape X_SHAPE;

    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty WEST = BooleanProperty.of("west");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty EAST = BooleanProperty.of("east");

    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");

    public CyanescenStemBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)
                this.stateManager.getDefaultState()).with(WATERLOGGED, false))
                .with(NORTH, false)).with(WEST, false)).with(SOUTH, false)).with(EAST, false))
                .with(UP, false)).with(DOWN, false));

    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(world.getBlockState(pos.north(1)).isAir() && world.getBlockState(pos.west(1)).isAir() && world.getBlockState(pos.east(1)).isAir() && world.getBlockState(pos.south(1)).isAir()) {
            return Y_SHAPE;
        } else {
            return FULL_SHAPE;
        }
    }

    private boolean northCheck(BlockState state, BlockView world, BlockPos pos){
        BlockState blockState = world.getBlockState(pos.north(1));
        return blockState.isOf(LegacyBlocks.CYANESCEN_STEM);
    }
    private boolean westCheck(BlockState state, BlockView world, BlockPos pos){
        BlockState blockState = world.getBlockState(pos.west(1));
        return blockState.isOf(LegacyBlocks.CYANESCEN_STEM);
    }
    private boolean southCheck(BlockState state, BlockView world, BlockPos pos){
        BlockState blockState = world.getBlockState(pos.south(1));
        return blockState.isOf(LegacyBlocks.CYANESCEN_STEM);
    }
    private boolean eastCheck(BlockState state, BlockView world, BlockPos pos){
        BlockState blockState = world.getBlockState(pos.east(1));
        return blockState.isOf(LegacyBlocks.CYANESCEN_STEM);
    }
    private boolean upCheck(BlockState state, BlockView world, BlockPos pos){
        BlockState blockState = world.getBlockState(pos.up());
        return !blockState.isOf(LegacyBlocks.CYANESCEN_STEM);
    }
    private boolean downCheck(BlockState state, BlockView world, BlockPos pos){
        BlockState blockState = world.getBlockState(pos.down());
        return !blockState.isOf(LegacyBlocks.CYANESCEN_STEM);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().north(1));
        BlockState blockState2 = ctx.getWorld().getBlockState(ctx.getBlockPos().west(1));
        BlockState blockState3 = ctx.getWorld().getBlockState(ctx.getBlockPos().south(1));
        BlockState blockState4 = ctx.getWorld().getBlockState(ctx.getBlockPos().east(1));
        BlockState blockState5 = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
        BlockState blockState6 = ctx.getWorld().getBlockState(ctx.getBlockPos().down());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        boolean bl2 = blockState.isOf(LegacyBlocks.CYANESCEN_STEM);
        boolean bl3 = blockState2.isOf(LegacyBlocks.CYANESCEN_STEM);
        boolean bl4 = blockState3.isOf(LegacyBlocks.CYANESCEN_STEM);
        boolean bl5 = blockState4.isOf(LegacyBlocks.CYANESCEN_STEM);
        boolean bl6 = !blockState5.isOf(LegacyBlocks.CYANESCEN_STEM);
        boolean bl7 = !blockState6.isOf(LegacyBlocks.CYANESCEN_STEM);
        return (BlockState)(BlockState)(BlockState)(BlockState)(BlockState)(BlockState)(BlockState)super.getPlacementState(ctx).with(WATERLOGGED, bl).with(NORTH, bl2).with(WEST, bl3).with(SOUTH, bl4).with(EAST, bl5).with(UP, bl6).with(DOWN, bl7);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return (BlockState)(BlockState)(BlockState)(BlockState)(BlockState)(BlockState)state.with(NORTH, northCheck(state, world, pos)).with(WEST, westCheck(state, world, pos)).with(SOUTH, southCheck(state, world, pos)).with(EAST, eastCheck(state, world, pos)).with(UP, upCheck(state, world, pos)).with(DOWN, downCheck(state, world, pos));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{WATERLOGGED}).add(NORTH, WEST, SOUTH, EAST, UP, DOWN);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        Y_SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
        FULL_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
        X_SHAPE = Block.createCuboidShape(0.0, 3.0, 3.0, 16.0, 13.0, 13.0);
    }
}
