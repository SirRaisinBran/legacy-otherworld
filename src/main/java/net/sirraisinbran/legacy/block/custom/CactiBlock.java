package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.util.LegacyProperties;

import java.util.Iterator;

public class CactiBlock extends Block implements Waterloggable {

    public static final IntProperty CACTI;
    public static final BooleanProperty WATERLOGGED;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape NORTH_ONE_CACTI_SHAPE;
    private static final VoxelShape WEST_ONE_CACTI_SHAPE;
    private static final VoxelShape SOUTH_ONE_CACTI_SHAPE;
    private static final VoxelShape EAST_ONE_CACTI_SHAPE;

    private static final VoxelShape NORTH_TWO_CACTI_SHAPE;
    private static final VoxelShape WEST_TWO_CACTI_SHAPE;
    private static final VoxelShape SOUTH_TWO_CACTI_SHAPE;
    private static final VoxelShape EAST_TWO_CACTI_SHAPE;

    private static final VoxelShape NORTH_THREE_CACTI_SHAPE;
    private static final VoxelShape WEST_THREE_CACTI_SHAPE;
    private static final VoxelShape SOUTH_THREE_CACTI_SHAPE;
    private static final VoxelShape EAST_THREE_CACTI_SHAPE;

    private static final VoxelShape NORTH_FOUR_CACTI_SHAPE;
    private static final VoxelShape WEST_FOUR_CACTI_SHAPE;
    private static final VoxelShape SOUTH_FOUR_CACTI_SHAPE;
    private static final VoxelShape EAST_FOUR_CACTI_SHAPE;

    private static final VoxelShape NORTH_FIVE_CACTI_SHAPE;
    private static final VoxelShape WEST_FIVE_CACTI_SHAPE;
    private static final VoxelShape SOUTH_FIVE_CACTI_SHAPE;
    private static final VoxelShape EAST_FIVE_CACTI_SHAPE;

    private static final VoxelShape NORTH_SIX_CACTI_SHAPE;
    private static final VoxelShape WEST_SIX_CACTI_SHAPE;
    private static final VoxelShape SOUTH_SIX_CACTI_SHAPE;
    private static final VoxelShape EAST_SIX_CACTI_SHAPE;


    public CactiBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(CACTI, 1)).with(WATERLOGGED, false));
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && (Integer)state.get(CACTI) < 6 ? true : super.canReplace(state, context);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return (BlockState)blockState.with(FACING, ctx.getPlayerFacing().getOpposite()).cycle(CACTI);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
            boolean bl = fluidState.getFluid() == Fluids.WATER;
            return (BlockState)super.getPlacementState(ctx).with(WATERLOGGED, bl);
        }
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch ((Integer)state.get(CACTI)) {
            case 1:
            default:
                switch (state.get(FACING)) {
                    case NORTH:
                        return NORTH_ONE_CACTI_SHAPE;
                    case WEST:
                        return WEST_ONE_CACTI_SHAPE;
                    case SOUTH:
                        return SOUTH_ONE_CACTI_SHAPE;
                    case EAST:
                        return EAST_ONE_CACTI_SHAPE;
                }
            case 2:
                switch (state.get(FACING)) {
                    case NORTH:
                        return NORTH_TWO_CACTI_SHAPE;
                    case WEST:
                        return WEST_TWO_CACTI_SHAPE;
                    case SOUTH:
                        return SOUTH_TWO_CACTI_SHAPE;
                    case EAST:
                        return EAST_TWO_CACTI_SHAPE;
                }
            case 3:
                switch (state.get(FACING)) {
                    case NORTH:
                        return NORTH_THREE_CACTI_SHAPE;
                    case WEST:
                        return WEST_THREE_CACTI_SHAPE;
                    case SOUTH:
                        return SOUTH_THREE_CACTI_SHAPE;
                    case EAST:
                        return EAST_THREE_CACTI_SHAPE;
                }
            case 4:
                switch (state.get(FACING)) {
                    case NORTH:
                        return NORTH_FOUR_CACTI_SHAPE;
                    case WEST:
                        return WEST_FOUR_CACTI_SHAPE;
                    case SOUTH:
                        return SOUTH_FOUR_CACTI_SHAPE;
                    case EAST:
                        return EAST_FOUR_CACTI_SHAPE;
                }
            case 5:
                switch (state.get(FACING)) {
                    case NORTH: return NORTH_FIVE_CACTI_SHAPE;
                    case WEST: return WEST_FIVE_CACTI_SHAPE;
                    case SOUTH: return SOUTH_FIVE_CACTI_SHAPE;
                    case EAST: return EAST_FIVE_CACTI_SHAPE;
                }
            case 6:
                switch (state.get(FACING)) {
                    case NORTH: return NORTH_SIX_CACTI_SHAPE;
                    case WEST: return WEST_SIX_CACTI_SHAPE;
                    case SOUTH: return SOUTH_SIX_CACTI_SHAPE;
                    case EAST: return EAST_SIX_CACTI_SHAPE;
                }
        }
        return null;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{CACTI, WATERLOGGED});
        builder.add(FACING);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());
        boolean bl = !world.getBlockState(pos.up()).isAir() && !blockState.isAir();
        Iterator var6 = Direction.Type.HORIZONTAL.iterator();

        BlockState blockState3;
        do {
            BlockPos blockPos;
            BlockState blockState2;
            do {
                if (!var6.hasNext()) {
                    return blockState.isOf(this) || blockState.isOf(LegacyBlocks.DRY_SAND);
                }

                Direction direction = (Direction)var6.next();
                blockPos = pos.offset(direction);
                blockState2 = world.getBlockState(blockPos);
            } while(!blockState2.isOf(this));

            if (bl) {
                return false;
            }

            blockState3 = world.getBlockState(blockPos.down());
        } while(!blockState3.isOf(this) && !blockState3.isOf(LegacyBlocks.DRY_SAND));

        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    static {
        CACTI = LegacyProperties.CACTI;
        WATERLOGGED = Properties.WATERLOGGED;


        NORTH_ONE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
        WEST_ONE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
        SOUTH_ONE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
        EAST_ONE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);

        NORTH_TWO_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        WEST_TWO_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        SOUTH_TWO_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        EAST_TWO_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);

        NORTH_THREE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        WEST_THREE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        SOUTH_THREE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        EAST_THREE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);

        NORTH_FOUR_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        WEST_FOUR_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        SOUTH_FOUR_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        EAST_FOUR_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);

        NORTH_FIVE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        WEST_FIVE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        SOUTH_FIVE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        EAST_FIVE_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);

        NORTH_SIX_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        WEST_SIX_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        SOUTH_SIX_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
        EAST_SIX_CACTI_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 27.0, 12.0);
    }
}
