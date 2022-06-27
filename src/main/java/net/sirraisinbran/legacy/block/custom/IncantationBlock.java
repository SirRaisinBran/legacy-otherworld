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
import net.sirraisinbran.legacy.block.entity.IncantationBlockEntity;
import net.sirraisinbran.legacy.block.entity.LegacyBlockEntities;
import net.sirraisinbran.legacy.block.entity.WorkbenchBlockEntity;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class IncantationBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public IncantationBlock(Settings settings) {

        super(settings);
    }

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(2, 0, 11, 14, 1, 15),
            Block.createCuboidShape(2, 0, 1, 14, 1, 5),
            Block.createCuboidShape(9, 1, 2, 12, 10, 4),
            Block.createCuboidShape(6, 1, 12, 12, 10, 14),
            Block.createCuboidShape(5, 1, 12, 6, 6, 14),
            Block.createCuboidShape(4, 1, 12, 5, 4, 14),
            Block.createCuboidShape(4, 1, 2, 6, 10, 4),
            Block.createCuboidShape(1, 10, 2, 2, 12, 10),
            Block.createCuboidShape(14, 10, 2, 15, 12, 10),
            Block.createCuboidShape(2, 10, 1, 14, 12, 11),
            Block.createCuboidShape(5, 8, 11, 14, 12, 15),
            Block.createCuboidShape(12, 12, 2, 15, 14, 11)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(11, 0, 2, 15, 1, 14),
            Block.createCuboidShape(1, 0, 2, 5, 1, 14),
            Block.createCuboidShape(2, 1, 4, 4, 10, 7),
            Block.createCuboidShape(12, 1, 4, 14, 10, 10),
            Block.createCuboidShape(12, 1, 10, 14, 6, 11),
            Block.createCuboidShape(12, 1, 11, 14, 4, 12),
            Block.createCuboidShape(2, 1, 10, 4, 10, 12),
            Block.createCuboidShape(2, 10, 14, 10, 12, 15),
            Block.createCuboidShape(2, 10, 1, 10, 12, 2),
            Block.createCuboidShape(1, 10, 2, 11, 12, 14),
            Block.createCuboidShape(11, 8, 2, 15, 12, 11),
            Block.createCuboidShape(2, 12, 1, 11, 14, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(2, 0, 1, 14, 1, 5),
            Block.createCuboidShape(2, 0, 11, 14, 1, 15),
            Block.createCuboidShape(4, 1, 12, 7, 10, 14),
            Block.createCuboidShape(4, 1, 2, 10, 10, 4),
            Block.createCuboidShape(10, 1, 2, 11, 6, 4),
            Block.createCuboidShape(11, 1, 2, 12, 4, 4),
            Block.createCuboidShape(10, 1, 12, 12, 10, 14),
            Block.createCuboidShape(14, 10, 6, 15, 12, 14),
            Block.createCuboidShape(1, 10, 6, 2, 12, 14),
            Block.createCuboidShape(2, 10, 5, 14, 12, 15),
            Block.createCuboidShape(2, 8, 1, 11, 12, 5),
            Block.createCuboidShape(1, 12, 5, 4, 14, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(1, 0, 2, 5, 1, 14),
            Block.createCuboidShape(11, 0, 2, 15, 1, 14),
            Block.createCuboidShape(12, 1, 9, 14, 10, 12),
            Block.createCuboidShape(2, 1, 6, 4, 10, 12),
            Block.createCuboidShape(2, 1, 5, 4, 6, 6),
            Block.createCuboidShape(2, 1, 4, 4, 4, 5),
            Block.createCuboidShape(12, 1, 4, 14, 10, 6),
            Block.createCuboidShape(6, 10, 1, 14, 12, 2),
            Block.createCuboidShape(6, 10, 14, 14, 12, 15),
            Block.createCuboidShape(5, 10, 2, 15, 12, 14),
            Block.createCuboidShape(1, 8, 5, 5, 12, 14),
            Block.createCuboidShape(5, 12, 12, 14, 14, 15)
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
            if (blockEntity instanceof IncantationBlockEntity) {
                ItemScatterer.spawn(world, pos, (IncantationBlockEntity)blockEntity);
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
        return new IncantationBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, LegacyBlockEntities.INCANTATION_BLOCK_ENTITY, IncantationBlockEntity::tick);
    }
}