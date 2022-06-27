package net.sirraisinbran.legacy.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.block.entity.ScarletBrainEntity;
import net.sirraisinbran.legacy.util.LegacyProperties;
import net.sirraisinbran.legacy.util.ScarletShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ScarletBlock extends Block implements Fertilizable{

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
    BlockPos brainPos;
    BlockPos scarletPos;
    BlockEntity brainEntity;
    public boolean isBrain;
    Random random = new Random();
    public static final BooleanProperty STATUS = LegacyProperties.STATUS;
    public boolean bottomBlock;

    public ScarletBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getStateManager().getDefaultState().with(STATUS, false));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    /*
    public void isBrainMethod(BlockView world, BlockPos pos, BlockState state, boolean isClient, ScarletBlock block) {
        boolean northBlock = world.getBlockState(pos.north()).isIn(LegacyMod.SCARLET);
        boolean westBlock = world.getBlockState(pos.west()).isIn(LegacyMod.SCARLET);
        boolean southBlock = world.getBlockState(pos.south()).isIn(LegacyMod.SCARLET);
        boolean eastBlock = world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET);
        if(northBlock || westBlock || southBlock || eastBlock) {
            block.isBrain = false;
        } else {
            block.isBrain = true;
        }
    }

     */

    public void findBottomBlock(BlockState state, BlockView world, BlockPos pos, ScarletBlock block) {
        block.bottomBlock = world.getBlockState(pos.down()).isOf(LegacyBlocks.SOIL);
    }

    public void findBrain(BlockState state, ServerWorld world, BlockPos pos, ScarletBlock block) {
        BlockEntity blockState = world.getBlockEntity(pos.north(1));
        BlockEntity blockState2 = world.getBlockEntity(pos.west(1));
        BlockEntity blockState3 = world.getBlockEntity(pos.south(1));
        BlockEntity blockState4 = world.getBlockEntity(pos.east(1));
        BlockEntity blockState5 = world.getBlockEntity(pos.north(1).west(1));
        BlockEntity blockState6 = world.getBlockEntity(pos.west(1).south(1));
        BlockEntity blockState7 = world.getBlockEntity(pos.south(1).east(1));
        BlockEntity blockState8 = world.getBlockEntity(pos.east(1).north(1));
        BlockEntity blockState9 = world.getBlockEntity(pos.north(2));
        BlockEntity blockState10 = world.getBlockEntity(pos.west(2));
        BlockEntity blockState11 = world.getBlockEntity(pos.south(2));
        BlockEntity blockState12 = world.getBlockEntity(pos.east(2));
        BlockEntity blockState13 = world.getBlockEntity(pos.north(1).west(2));
        BlockEntity blockState14 = world.getBlockEntity(pos.west(1).south(2));
        BlockEntity blockState15 = world.getBlockEntity(pos.south(1).east(2));
        BlockEntity blockState16 = world.getBlockEntity(pos.east(1).north(2));
        BlockEntity blockState17 = world.getBlockEntity(pos.north(2).west(1));
        BlockEntity blockState18 = world.getBlockEntity(pos.west(2).south(1));
        BlockEntity blockState19 = world.getBlockEntity(pos.south(2).east(1));
        BlockEntity blockState20 = world.getBlockEntity(pos.east(2).north(1));
        BlockEntity blockState21 = world.getBlockEntity(pos.north(2).west(2));
        BlockEntity blockState22 = world.getBlockEntity(pos.west(2).south(2));
        BlockEntity blockState23 = world.getBlockEntity(pos.south(2).east(2));
        BlockEntity blockState24 = world.getBlockEntity(pos.east(2).north(2));
        /*
        BlockEntity blockState25 = world.getBlockEntity(pos.north(3));
        BlockEntity blockState26 = world.getBlockEntity(pos.west(3));
        BlockEntity blockState27 = world.getBlockEntity(pos.south(3));
        BlockEntity blockState28 = world.getBlockEntity(pos.east(3));
        BlockEntity blockState29 = world.getBlockEntity(pos.north(3).west(2));
        BlockEntity blockState30 = world.getBlockEntity(pos.west(3).south(2));
        BlockEntity blockState31 = world.getBlockEntity(pos.south(3).east(2));
        BlockEntity blockState32 = world.getBlockEntity(pos.east(3).north(2));
         */

        if(blockState instanceof ScarletBrainEntity be) {
            block.brainPos = pos.north(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState2 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.west(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState3 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.south(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState4 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.east(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState5 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.north(1).west(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState6 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.west(1).south(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState7 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.south(1).east(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState8 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.east(1).north(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState9 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.north(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState10 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.west(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState11 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.south(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState12 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.east(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState13 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.north(1).west(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState14 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.west(1).south(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState15 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.south(1).east(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState16 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.east(1).north(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState17 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.north(2).west(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState18 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.west(2).south(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState19 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.south(2).east(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState20 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.east(2).north(1);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState21 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.north(2).west(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState22 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.west(2).south(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState23 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.south(2).east(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else if(blockState24 instanceof ScarletBrainEntity be) {
            block.brainPos = pos.east(2).north(2);
            block.brainEntity = be;
            block.isBrain = false;
        } else {
            block.brainPos = pos;
            world.setBlockState(pos, (BlockState)state.with(STATUS, true));
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(DamageSource.GENERIC, 1.0F);
    }

    public void tickMethod(BlockState state, ServerWorld world, BlockPos pos, ScarletBlock block){
        findBrain(state, world, pos, block);
        findBottomBlock(state, world, pos, block);
        if (block.brainEntity instanceof ScarletBrainEntity be && !isBrain) {
            if (be.newBrain){
                if (world.getBlockState(pos.north().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.north()).isAir() || world.getBlockState(pos.north()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.north(), LegacyBlocks.SCARLET_BRAIN.getDefaultState());
                    be.updateSpread(world, pos, be);
                } else if (world.getBlockState(pos.west().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.west()).isAir() || world.getBlockState(pos.west()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.west(), LegacyBlocks.SCARLET_BRAIN.getDefaultState());
                    be.updateSpread(world, pos, be);
                } else if (world.getBlockState(pos.south().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.south()).isAir() || world.getBlockState(pos.south()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.south(), LegacyBlocks.SCARLET_BRAIN.getDefaultState());
                    be.updateSpread(world, pos, be);
                } else if (world.getBlockState(pos.east().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.east()).isAir() || world.getBlockState(pos.east()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.east(), LegacyBlocks.SCARLET_BRAIN.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
            }
            if (canSpread(world, pos, block)) {
                if (world.getBlockState(pos.north().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.north()).isAir() || world.getBlockState(pos.north()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.north(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                } else if (world.getBlockState(pos.west().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.west()).isAir() || world.getBlockState(pos.west()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.west(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                } else if (world.getBlockState(pos.south().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.south()).isAir() || world.getBlockState(pos.south()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.south(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                } else if (world.getBlockState(pos.east().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.east()).isAir() || world.getBlockState(pos.east()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.east(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
            }
            if (canSpread(world, pos, block)) {
                if (world.getBlockState(pos.north().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.north()).isAir() || world.getBlockState(pos.north()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.north(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                } else if (world.getBlockState(pos.west().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.west()).isAir() || world.getBlockState(pos.west()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.west(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                } else if (world.getBlockState(pos.south().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.south()).isAir() || world.getBlockState(pos.south()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.south(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                } else if (world.getBlockState(pos.east().down()).isIn(LegacyMod.ORGANIC) && (world.getBlockState(pos.east()).isAir() || world.getBlockState(pos.east()).isIn(LegacyMod.OW_FOLIAGE))) {
                    world.setBlockState(pos.east(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                    be.updateSpread(world, pos, be);
                }
            }
        } else if (isBrain) {
            boolean northBlock = world.getBlockState(pos.north()).isIn(LegacyMod.SCARLET);
            boolean westBlock = world.getBlockState(pos.west()).isIn(LegacyMod.SCARLET);
            boolean southBlock = world.getBlockState(pos.south()).isIn(LegacyMod.SCARLET);
            boolean eastBlock = world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET);
            if(!northBlock && !westBlock && !southBlock && !eastBlock) {
                world.setBlockState(pos, LegacyBlocks.SCARLET_BRAIN.getDefaultState());
            }
        }
        if(canSpread(world, pos, block) && block.isBrain){
            world.breakBlock(pos, false);
        }
        if(bottomBlock) {
            world.breakBlock(pos, false);
        }
    }

    private boolean canSpread(World world, BlockPos pos, ScarletBlock block) {
        BlockEntity blockEntity = world.getBlockEntity(block.brainPos);
        if (blockEntity instanceof ScarletBrainEntity scarletBrainEntity) {
            return scarletBrainEntity.canSpread;
        } else {
            return false;
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        tickMethod(state, world, pos, this);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STATUS);
    }
    /*

    public void isShmoo(){
        this.brainBlock.totalShmoo = this.brainBlock.totalShmoo  + 1;
    }

    public void isOuter() {
        this.brainBlock.totalOuter = this.brainBlock.totalOuter + 1;
    }

    public void isEdge() {
        this.brainBlock.totalEdge = this.brainBlock.totalEdge + 1;
    }

    public void isCenter() {
        this.brainBlock.totalCenter = this.brainBlock.totalCenter + 1;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (isBrain(world, pos, state, true)) {
            world.setBlockState(pos, LegacyBlocks.SCARLET_BRAIN.getDefaultState());
        } else if(--this.brainBlock.spreadTime <= 0 && this.brainBlock.availableSpread > 0) {
            boolean northBlock = world.getBlockState(pos.north().down()).isIn(LegacyMod.ORGANIC) && world.getBlockState(pos.north()).isAir();
            boolean westBlock = world.getBlockState(pos.west().down()).isIn(LegacyMod.ORGANIC) && world.getBlockState(pos.north()).isAir();
            boolean southBlock = world.getBlockState(pos.south().down()).isIn(LegacyMod.ORGANIC) && world.getBlockState(pos.north()).isAir();
            boolean eastBlock = world.getBlockState(pos.east().down()).isIn(LegacyMod.ORGANIC) && world.getBlockState(pos.north()).isAir();
            if (northBlock) {
                world.setBlockState(pos.north(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                this.brainBlock.availableSpread = this.brainBlock.availableSpread - 1;
            } else if (westBlock) {
                world.setBlockState(pos.west(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                this.brainBlock.availableSpread = this.brainBlock.availableSpread - 1;
            } else if (southBlock) {
                world.setBlockState(pos.south(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                this.brainBlock.availableSpread = this.brainBlock.availableSpread - 1;
            } else if (eastBlock) {
                world.setBlockState(pos.east(), LegacyBlocks.SCARLET_BLOCK.getDefaultState());
                this.brainBlock.availableSpread = this.brainBlock.availableSpread - 1;
            }

            //Classifying
            if(world.getBlockState(pos.north()).isIn(LegacyMod.SCARLET)) {
                if(world.getBlockState(pos.west()).isIn(LegacyMod.SCARLET)) {
                    if(world.getBlockState(pos.south()).isIn(LegacyMod.SCARLET)) {
                        if(world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET)) {
                            isCenter();
                        } else {
                            isEdge();
                        }
                    } else if(world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET)) {
                        isEdge();
                    } else {
                        isOuter();
                    }
                } else if(world.getBlockState(pos.south()).isIn(LegacyMod.SCARLET)) {
                    if(world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET)) {
                        isEdge();
                    } else {
                        isOuter();
                    }
                } else if(world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET)) {
                    isOuter();
                } else {
                    isShmoo();
                }
            } else if(world.getBlockState(pos.west()).isIn(LegacyMod.SCARLET)) {
                if(world.getBlockState(pos.south()).isIn(LegacyMod.SCARLET)) {
                    if(world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET)) {
                        isEdge();
                    } else {
                        isOuter();
                    }
                } else if(world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET)) {
                    isOuter();
                } else {
                    isShmoo();
                }
            } else if(world.getBlockState(pos.south()).isIn(LegacyMod.SCARLET)) {
                if(world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET)) {
                    isOuter();
                } else {
                    isShmoo();
                }
            } else if(world.getBlockState(pos.east()).isIn(LegacyMod.SCARLET)) {
                isShmoo();
            }
        }
        if(!isBrain(world, pos, state, true)) {

        }
    }

     */

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {

    }



    /*
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;
    public static final EnumProperty<ScarletShape> SHAPE = LegacyProperties.SCARLET_SHAPE;

    public ScarletBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(HALF, BlockHalf.BOTTOM)).with(SHAPE, ScarletShape.FLOOR));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        BlockState blockState = (BlockState)((BlockState)((BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing())).with(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double)blockPos.getY() > 0.5)) ? BlockHalf.BOTTOM : BlockHalf.TOP));
        return (BlockState)blockState.with(SHAPE, getScarletShape(blockState, ctx.getWorld(), blockPos));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction.getAxis().isHorizontal() ? (BlockState)state.with(SHAPE, getScarletShape(state, world, pos)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private static ScarletShape getScarletShape(BlockState state, BlockView world, BlockPos pos) {
        return ScarletShape.FLOOR;
    }

    private static boolean isDifferentOrientation(BlockState state, BlockView world, BlockPos pos, Direction dir) {
        BlockState blockState = world.getBlockState(pos.offset(dir));
        return !isStairs(blockState) || blockState.get(FACING) != state.get(FACING) || blockState.get(HALF) != state.get(HALF);
    }

    public static boolean isStairs(BlockState state) {
        return state.getBlock() instanceof ScarletBlock;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return super.mirror(state, mirror);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, SHAPE);
    }

     */
}
