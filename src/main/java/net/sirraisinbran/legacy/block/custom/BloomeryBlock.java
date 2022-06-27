package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sirraisinbran.legacy.block.entity.BloomeryBlockEntity;
import net.sirraisinbran.legacy.block.entity.LegacyBlockEntities;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

public class BloomeryBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public BloomeryBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(LIT, false));
    }

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(15, 0, 15, 16, 10, 16),
            Block.createCuboidShape(15, 0, 0, 16, 10, 1),
            Block.createCuboidShape(0, 0, 15, 1, 10, 16),
            Block.createCuboidShape(0, 0, 0, 1, 10, 1),
            Block.createCuboidShape(3, 9, 13, 13, 10, 14),
            Block.createCuboidShape(3, 9, 2, 13, 10, 3),
            Block.createCuboidShape(13, 9, 2, 14, 10, 14),
            Block.createCuboidShape(2, 9, 2, 3, 10, 14),
            Block.createCuboidShape(2, 0, 1, 14, 9, 2),
            Block.createCuboidShape(1, 0, 1, 2, 9, 15),
            Block.createCuboidShape(14, 0, 1, 15, 9, 5),
            Block.createCuboidShape(14, 3, 5, 15, 9, 11),
            Block.createCuboidShape(14, 0, 11, 15, 9, 15),
            Block.createCuboidShape(2, 0, 14, 14, 9, 15),
            Block.createCuboidShape(3, 10, 3, 5, 12, 13),
            Block.createCuboidShape(11, 10, 3, 13, 12, 13),
            Block.createCuboidShape(5, 10, 3, 11, 12, 5),
            Block.createCuboidShape(5, 10, 11, 11, 12, 13),
            Block.createCuboidShape(5, 12, 5, 11, 16, 6),
            Block.createCuboidShape(10, 12, 6, 11, 16, 10),
            Block.createCuboidShape(5, 12, 6, 6, 16, 10),
            Block.createCuboidShape(5, 12, 10, 11, 16, 11)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(0, 0, 15, 1, 10, 16),
            Block.createCuboidShape(15, 0, 15, 16, 10, 16),
            Block.createCuboidShape(0, 0, 0, 1, 10, 1),
            Block.createCuboidShape(15, 0, 0, 16, 10, 1),
            Block.createCuboidShape(2, 9, 3, 3, 10, 13),
            Block.createCuboidShape(13, 9, 3, 14, 10, 13),
            Block.createCuboidShape(2, 9, 13, 14, 10, 14),
            Block.createCuboidShape(2, 9, 2, 14, 10, 3),
            Block.createCuboidShape(14, 0, 2, 15, 9, 14),
            Block.createCuboidShape(1, 0, 1, 15, 9, 2),
            Block.createCuboidShape(11, 0, 14, 15, 9, 15),
            Block.createCuboidShape(5, 3, 14, 11, 9, 15),
            Block.createCuboidShape(1, 0, 14, 5, 9, 15),
            Block.createCuboidShape(1, 0, 2, 2, 9, 14),
            Block.createCuboidShape(3, 10, 3, 13, 12, 5),
            Block.createCuboidShape(3, 10, 11, 13, 12, 13),
            Block.createCuboidShape(11, 10, 5, 13, 12, 11),
            Block.createCuboidShape(3, 10, 5, 5, 12, 11),
            Block.createCuboidShape(10, 12, 5, 11, 16, 11),
            Block.createCuboidShape(6, 12, 10, 10, 16, 11),
            Block.createCuboidShape(6, 12, 5, 10, 16, 6),
            Block.createCuboidShape(5, 12, 5, 6, 16, 11)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(0, 0, 0, 1, 10, 1),
            Block.createCuboidShape(0, 0, 15, 1, 10, 16),
            Block.createCuboidShape(15, 0, 0, 16, 10, 1),
            Block.createCuboidShape(15, 0, 15, 16, 10, 16),
            Block.createCuboidShape(3, 9, 2, 13, 10, 3),
            Block.createCuboidShape(3, 9, 13, 13, 10, 14),
            Block.createCuboidShape(2, 9, 2, 3, 10, 14),
            Block.createCuboidShape(13, 9, 2, 14, 10, 14),
            Block.createCuboidShape(2, 0, 14, 14, 9, 15),
            Block.createCuboidShape(14, 0, 1, 15, 9, 15),
            Block.createCuboidShape(1, 0, 11, 2, 9, 15),
            Block.createCuboidShape(1, 3, 5, 2, 9, 11),
            Block.createCuboidShape(1, 0, 1, 2, 9, 5),
            Block.createCuboidShape(2, 0, 1, 14, 9, 2),
            Block.createCuboidShape(11, 10, 3, 13, 12, 13),
            Block.createCuboidShape(3, 10, 3, 5, 12, 13),
            Block.createCuboidShape(5, 10, 11, 11, 12, 13),
            Block.createCuboidShape(5, 10, 3, 11, 12, 5),
            Block.createCuboidShape(5, 12, 10, 11, 16, 11),
            Block.createCuboidShape(5, 12, 6, 6, 16, 10),
            Block.createCuboidShape(10, 12, 6, 11, 16, 10),
            Block.createCuboidShape(5, 12, 5, 11, 16, 6)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(15, 0, 0, 16, 10, 1),
            Block.createCuboidShape(0, 0, 0, 1, 10, 1),
            Block.createCuboidShape(15, 0, 15, 16, 10, 16),
            Block.createCuboidShape(0, 0, 15, 1, 10, 16),
            Block.createCuboidShape(13, 9, 3, 14, 10, 13),
            Block.createCuboidShape(2, 9, 3, 3, 10, 13),
            Block.createCuboidShape(2, 9, 2, 14, 10, 3),
            Block.createCuboidShape(2, 9, 13, 14, 10, 14),
            Block.createCuboidShape(1, 0, 2, 2, 9, 14),
            Block.createCuboidShape(1, 0, 14, 15, 9, 15),
            Block.createCuboidShape(1, 0, 1, 5, 9, 2),
            Block.createCuboidShape(5, 3, 1, 11, 9, 2),
            Block.createCuboidShape(11, 0, 1, 15, 9, 2),
            Block.createCuboidShape(14, 0, 2, 15, 9, 14),
            Block.createCuboidShape(3, 10, 11, 13, 12, 13),
            Block.createCuboidShape(3, 10, 3, 13, 12, 5),
            Block.createCuboidShape(3, 10, 5, 5, 12, 11),
            Block.createCuboidShape(11, 10, 5, 13, 12, 11),
            Block.createCuboidShape(5, 12, 5, 6, 16, 11),
            Block.createCuboidShape(6, 12, 5, 10, 16, 6),
            Block.createCuboidShape(6, 12, 10, 10, 16, 11),
            Block.createCuboidShape(10, 12, 5, 11, 16, 11)
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
        builder.add(FACING, LIT);

    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BloomeryBlockEntity) {
                ItemScatterer.spawn(world, pos, (BloomeryBlockEntity)blockEntity);
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

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if ((Boolean)state.get(LIT)) {
            double d = (double)pos.getX() + 0.5;
            double e = (double)pos.getY();
            double f = (double)pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                world.playSound(d, e, f, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            world.addParticle(ParticleTypes.SMOKE, d, e + 1.1, f, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.LARGE_SMOKE, d, e + 1.1, f, 0.0, 0.0, 0.0);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BloomeryBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, LegacyBlockEntities.BLOOMERY_BLOCK_ENTITY, BloomeryBlockEntity::tick);
    }
}
