package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sirraisinbran.legacy.block.entity.LegacyBlockEntities;
import net.sirraisinbran.legacy.block.entity.WorkbenchBlockEntity;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class WorkbenchBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public WorkbenchBlock(Settings settings) {

        super(settings);
    }

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(5, 7, 3, 12, 8, 13),
            Block.createCuboidShape(3, 13, 1, 14, 16, 15),
            Block.createCuboidShape(10, 0, 12, 13, 13, 14),
            Block.createCuboidShape(4, 0, 2, 7, 13, 4),
            Block.createCuboidShape(3, 0, 1, 14, 1, 5),
            Block.createCuboidShape(3, 0, 11, 14, 1, 15),
            Block.createCuboidShape(10, 0, 2, 13, 13, 4),
            Block.createCuboidShape(4, 0, 12, 7, 13, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(3, 7, 4, 13, 8, 11),
            Block.createCuboidShape(1, 13, 2, 15, 16, 13),
            Block.createCuboidShape(12, 0, 3, 14, 13, 6),
            Block.createCuboidShape(2, 0, 9, 4, 13, 12),
            Block.createCuboidShape(1, 0, 2, 5, 1, 13),
            Block.createCuboidShape(11, 0, 2, 15, 1, 13),
            Block.createCuboidShape(2, 0, 3, 4, 13, 6),
            Block.createCuboidShape(12, 0, 9, 14, 13, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(4, 7, 3, 11, 8, 13),
            Block.createCuboidShape(2, 13, 1, 13, 16, 15),
            Block.createCuboidShape(3, 0, 2, 6, 13, 4),
            Block.createCuboidShape(9, 0, 12, 12, 13, 14),
            Block.createCuboidShape(2, 0, 11, 13, 1, 15),
            Block.createCuboidShape(2, 0, 1, 13, 1, 5),
            Block.createCuboidShape(3, 0, 12, 6, 13, 14),
            Block.createCuboidShape(9, 0, 2, 12, 13, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(3, 7, 5, 13, 8, 12),
            Block.createCuboidShape(1, 13, 3, 15, 16, 14),
            Block.createCuboidShape(2, 0, 10, 4, 13, 13),
            Block.createCuboidShape(12, 0, 4, 14, 13, 7),
            Block.createCuboidShape(11, 0, 3, 15, 1, 14),
            Block.createCuboidShape(1, 0, 3, 5, 1, 14),
            Block.createCuboidShape(12, 0, 10, 14, 13, 13),
            Block.createCuboidShape(2, 0, 4, 4, 13, 7)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);

    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof WorkbenchBlockEntity) {
                ItemScatterer.spawn(world, pos, (WorkbenchBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WorkbenchBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, LegacyBlockEntities.WORKBENCH_BLOCK_ENTITY, WorkbenchBlockEntity::tick);
    }
}