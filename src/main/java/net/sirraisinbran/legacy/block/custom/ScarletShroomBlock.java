package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.block.custom.OtherworldMushroomPlant;
import net.sirraisinbran.legacy.util.LegacyProperties;
import net.sirraisinbran.legacy.util.ScarletMushroomShape;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static net.sirraisinbran.legacy.util.ScarletMushroomShape.*;

public class ScarletShroomBlock extends OtherworldMushroomPlant {

    protected static final VoxelShape LONE_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.5D, 11.0D);
    protected static final VoxelShape STEM_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    public static final EnumProperty<ScarletMushroomShape> SHAPE = LegacyProperties.SCARLET_MUSHROOM_SHAPE;
    public static final IntProperty MAX_HEIGHT = LegacyProperties.MAX_HEIGHT;
    public boolean isRoot = false;
    Random random = new Random();
    public static final IntProperty AGE;

    public static final BooleanProperty HAS_MAX = LegacyProperties.HAS_MAX;

    public ScarletShroomBlock(AbstractBlock.Settings settings, Supplier<RegistryEntry<? extends ConfiguredFeature<?, ?>>> feature) {
        super(settings, feature);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.getStateManager().getDefaultState()).with(SHAPE, ScarletMushroomShape.STEM)).with(AGE, 0)).with(MAX_HEIGHT, 3)).with(HAS_MAX, false));
    }

    public static boolean rootCheck(BlockState state, BlockView world, BlockPos pos){
        BlockState blockState = world.getBlockState(pos.up());
        BlockState blockState2 = world.getBlockState(pos.down());
        return !blockState2.isIn(LegacyMod.SCARLET_MUSHROOMS);
    }

    public static boolean stemCheck(BlockState state, BlockView world, BlockPos pos){
        BlockState blockState = world.getBlockState(pos.up());
        BlockState blockState2 = world.getBlockState(pos.down());
        return blockState2.isIn(LegacyMod.SCARLET_MUSHROOMS);
    }

    public static int randomInt(Random random, int min, int max) {
        max = max + 1;
        return random.nextInt(max - min) + min;
    }

    private static ScarletMushroomShape getShroomShape (BlockState state, BlockView world, BlockPos pos) {
        if (canReproduce(state, world, pos)){
            if (world.getBlockState(pos.up()).isAir()) {
                return LONE;
            } else {
                return ScarletMushroomShape.BOTTOM;
            }
        } else if (!canReproduce(state, world, pos) && rootCheck(state, world, pos)){
            return LONE;
        } else if (stemCheck(state, world, pos)) {
            return ScarletMushroomShape.STEM;
        } else {
            return LONE;
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = this.getDefaultState();
        int height = randomInt(random, 3, 6);
        return (BlockState)(BlockState)blockState.with(SHAPE, getShroomShape(blockState, ctx.getWorld(), blockPos)).with(MAX_HEIGHT, height);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }

        return direction.getAxis().isVertical() ? (BlockState)state.with(SHAPE, getShroomShape(state, world, pos)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        BlockState blockState = world.getBlockState(pos.up());
        BlockState blockState2 = world.getBlockState(pos.down());
        if(blockState.isAir() && !blockState2.isIn(LegacyMod.SCARLET_MUSHROOMS)) {
            return LONE_SHAPE;
        } else if (blockState.isAir() && blockState2.isIn(LegacyMod.SCARLET_MUSHROOMS)) {
            return STEM_SHAPE;
        } else if (blockState.isIn(LegacyMod.SCARLET_MUSHROOMS) && blockState2.isIn(LegacyMod.SCARLET_MUSHROOMS)) {
            return STEM_SHAPE;
        } else if (blockState.isIn(LegacyMod.SCARLET_MUSHROOMS) && !blockState2.isIn(LegacyMod.SCARLET_MUSHROOMS)) {
            return STEM_SHAPE;
        } else {
            return LONE_SHAPE;
        }
    }


    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Iterator var4 = Direction.Type.HORIZONTAL.iterator();

        Direction direction;
        Material material;
        do {
            if (!var4.hasNext()) {
                BlockState blockState2 = world.getBlockState(pos.down());
                BlockState blockState3 = world.getBlockState(pos.down(2));
                BlockState blockState4 = world.getBlockState(pos.down(3));
                BlockState blockState5 = world.getBlockState(pos.down(4));
                BlockState blockState6 = world.getBlockState(pos.down(5));
                BlockState blockState7 = world.getBlockState(pos.down(6));
                return ((blockState2.isIn(LegacyMod.SOIL)
                        || blockState2.isOf(LegacyBlocks.DRY_SAND)
                        || blockState2.isOf(LegacyBlocks.LUSH_GABBRO)
                        || blockState2.isOf(LegacyBlocks.DEAD_LUSH)
                        || blockState2.isOf(LegacyBlocks.DEAD_LUSH_GABBRO))
                        || (blockState2.isOf(LegacyBlocks.SCARLET_MUSHROOM) && blockState3.isIn(LegacyMod.ORGANIC)) || (blockState2.isOf(LegacyBlocks.SCARLET_MUSHROOM) && blockState4.isIn(LegacyMod.ORGANIC)) || (blockState2.isOf(LegacyBlocks.SCARLET_MUSHROOM) && blockState5.isIn(LegacyMod.ORGANIC)) || (blockState2.isOf(LegacyBlocks.SCARLET_MUSHROOM) && blockState6.isIn(LegacyMod.ORGANIC)) || (blockState2.isOf(LegacyBlocks.SCARLET_MUSHROOM) && blockState7.isIn(LegacyMod.ORGANIC)))
                        && !world.getBlockState(pos.up()).getMaterial().isLiquid();
            }

            direction = (Direction)var4.next();
            BlockState blockState = world.getBlockState(pos.offset(direction));
            material = blockState.getMaterial();
        } while(!material.isSolid() && !world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA));

        return false;
    }

    public static boolean canReproduce(BlockState state, BlockView world, BlockPos pos){
        return world.getBlockState(pos.down()).isIn(LegacyMod.ORGANIC);
    }

    public void heightTick(BlockState state, ServerWorld world, BlockPos pos){
        int height = randomInt(random, 3, 6);
        int i;
        for (i = 1; i < 2; ++i){
            world.setBlockState(pos, (BlockState) state.with(MAX_HEIGHT, height));
            world.setBlockState(pos, (BlockState) state.with(HAS_MAX, true));
        }
    }

    public void tickMethodTwo(BlockState state, ServerWorld world, BlockPos pos, ScarletShroomBlock block){
        if (world.getBlockState(pos.down()).isIn(LegacyMod.ORGANIC)) {
            int HEIGHT;
            int age = state.get(AGE);
            int max_height = state.get(MAX_HEIGHT);
            for (HEIGHT = 0; world.getBlockState(pos.up(HEIGHT)).isOf(this); ++HEIGHT) {
            }
            if (world.getBlockState(pos.up()).isAir()) {
                if (age >= 7) {
                    world.setBlockState(pos.up(), LegacyBlocks.SCARLET_MUSHROOM.getDefaultState());
                    world.setBlockState(pos, (BlockState) state.with(SHAPE, getShroomShape(state, world, pos)));
                    world.setBlockState(pos, (BlockState) state.with(AGE, 0));
                } else {
                    world.setBlockState(pos, (BlockState) state.with(AGE, age + 1));
                }
            } else if (world.getBlockState(pos.up()).isOf(LegacyBlocks.SCARLET_MUSHROOM)) {
                if (age >= 3) {
                    if (HEIGHT == max_height) {
                        world.setBlockState(pos.up(max_height), LegacyBlocks.SCARLET_PUFFBALL.getDefaultState());
                        world.setBlockState(pos, (BlockState) state.with(AGE, 0));
                    } else if (HEIGHT < max_height) {
                        world.setBlockState(pos.up(HEIGHT), LegacyBlocks.SCARLET_MUSHROOM.getDefaultState());
                        world.setBlockState(pos, (BlockState) state.with(AGE, 0));
                    } else {
                        world.breakBlock(pos, false);
                    }
                } else {
                    world.setBlockState(pos, (BlockState) state.with(AGE, age + 1));
                }
            }
        }
    }

    public void tickMethod(BlockState state, ServerWorld world, BlockPos pos, ScarletShroomBlock block){
        if (world.getBlockState(pos.down()).isIn(LegacyMod.ORGANIC)) {
            int HEIGHT;
            int age = state.get(AGE);
            int max_height = state.get(MAX_HEIGHT);
            for (HEIGHT = 0; world.getBlockState(pos.up(HEIGHT)).isOf(this); ++HEIGHT) {
            }
            if (age == 7) {
                if (world.getBlockState(pos.up()).isAir()) {
                    world.setBlockState(pos.up(), LegacyBlocks.SCARLET_MUSHROOM.getDefaultState());
                    world.setBlockState(pos, (BlockState) state.with(AGE, 8).with(SHAPE, getShroomShape(state, world, pos)));
                } else if (world.getBlockState(pos.up()).isOf(LegacyBlocks.SCARLET_MUSHROOM)) {
                    if (HEIGHT == max_height) {
                        world.setBlockState(pos.up(max_height), LegacyBlocks.SCARLET_PUFFBALL.getDefaultState());
                        world.setBlockState(pos, (BlockState) state.with(AGE, age + 1));
                    } else if (HEIGHT < max_height) {
                        world.setBlockState(pos.up(HEIGHT), LegacyBlocks.SCARLET_MUSHROOM.getDefaultState());
                    } else {
                        world.breakBlock(pos, false);
                    }
                }
            } else if (age == 11) {
                if (world.getBlockState(pos.up()).isOf(LegacyBlocks.SCARLET_MUSHROOM)) {
                    if (HEIGHT == max_height) {
                        world.setBlockState(pos.up(max_height), LegacyBlocks.SCARLET_PUFFBALL.getDefaultState());
                        world.setBlockState(pos, (BlockState) state.with(AGE, age + 1));
                    } else if (HEIGHT < max_height) {
                        world.setBlockState(pos.up(HEIGHT), LegacyBlocks.SCARLET_MUSHROOM.getDefaultState());
                        world.setBlockState(pos, (BlockState) state.with(AGE, 8));
                    } else {
                        world.breakBlock(pos, false);
                    }
                }
            } else if (age == 14) {
                world.breakBlock(pos, false);
            } else {
                world.setBlockState(pos, (BlockState) state.with(AGE, age + 1).with(SHAPE, getShroomShape(state, world, pos)));
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        //boolean hasMax = state.get(HAS_MAX);
        tickMethod(state, world, pos, this);
        //if (!hasMax){
            //heightTick(state, world, pos);
        //}
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean isOrganic = world.getBlockState(pos.down()).isIn(LegacyMod.ORGANIC);
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
        if (isOrganic && world.getBlockState(pos.up()).isAir()){
            this.isRoot = true;
        }
        if (!isOrganic && !world.getBlockState(pos.up()).isAir()) {
            world.breakBlock(pos, false);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, SHAPE, MAX_HEIGHT, HAS_MAX);
    }

    static {
        AGE = LegacyProperties.AGE_7;
    }
}
