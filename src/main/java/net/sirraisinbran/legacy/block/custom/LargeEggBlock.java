package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.entity.LegacyEntities;
import net.sirraisinbran.legacy.entity.custom.DodoEntity;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

public class LargeEggBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty HATCH;

    static {HATCH = Properties.HATCH;}
    public LargeEggBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(HATCH, 0));
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(6, 0, 6, 10, 7, 10),
            Block.createCuboidShape(7, 7, 7, 9, 8, 9),
            Block.createCuboidShape(10, 1, 7, 11, 6, 9),
            Block.createCuboidShape(5, 1, 7, 6, 6, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(6, 0, 6, 10, 7, 10),
            Block.createCuboidShape(7, 7, 7, 9, 8, 9),
            Block.createCuboidShape(7, 1, 5, 9, 6, 6),
            Block.createCuboidShape(7, 1, 10, 9, 6, 11)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(6, 0, 6, 10, 7, 10),
            Block.createCuboidShape(7, 7, 7, 9, 8, 9),
            Block.createCuboidShape(10, 1, 7, 11, 6, 9),
            Block.createCuboidShape(5, 1, 7, 6, 6, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(6, 0, 6, 10, 7, 10),
            Block.createCuboidShape(7, 7, 7, 9, 8, 9),
            Block.createCuboidShape(7, 1, 5, 9, 6, 6),
            Block.createCuboidShape(7, 1, 10, 9, 6, 11)
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
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HATCH);

    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.shouldHatchProgress(world)) {
            int i = (Integer)state.get(HATCH);
            if (i < 2) {
                world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                world.setBlockState(pos, (BlockState)state.with(HATCH, i + 1), 2);
            } else {
                world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                world.breakBlock(pos, false);
                world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
                DodoEntity dodoEntity = LegacyEntities.DODO_ENTITY_TYPE.create(world);
                dodoEntity.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
                dodoEntity.setBaby(true);
                world.spawnEntity(dodoEntity);
            }
        }
    }

    private boolean shouldHatchProgress(World world) {
        float f = world.getSkyAngle(1.0F);
        if ((double)f < 0.69 && (double)f > 0.65) {
            return true;
        } else {
            return world.random.nextInt(500) == 0;
        }
    }

    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        this.tryBreakEgg(world, state, pos, entity, 100);
        super.onSteppedOn(world, pos, state, entity);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!(entity instanceof ZombieEntity)) {
            this.tryBreakEgg(world, state, pos, entity, 3);
        }

        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    private void tryBreakEgg(World world, BlockState state, BlockPos pos, Entity entity, int inverseChance) {
        if (this.breaksEgg(world, entity)) {
            if (!world.isClient && world.random.nextInt(inverseChance) == 0 && state.isOf(LegacyBlocks.LARGE_EGG)) {
                this.breakEgg(world, pos, state);
            }

        }
    }

    private void breakEgg(World world, BlockPos pos, BlockState state) {
        world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
        world.breakBlock(pos, false);
    }

    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @org.jetbrains.annotations.Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        this.breakEgg(world, pos, state);
    }

    private boolean breaksEgg(World world, Entity entity) {
        if (!(entity instanceof DodoEntity) && !(entity instanceof BatEntity)) {
            if (!(entity instanceof LivingEntity)) {
                return false;
            } else {
                return entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
            }
        } else {
            return false;
        }
    }

}
