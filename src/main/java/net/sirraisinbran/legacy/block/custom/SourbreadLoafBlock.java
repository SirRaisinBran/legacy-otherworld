package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.sirraisinbran.legacy.item.LegacyItems;
import net.sirraisinbran.legacy.util.LegacyProperties;
import net.sirraisinbran.legacy.util.PearShape;

public class SourbreadLoafBlock extends Block {

    public static final IntProperty SIZE;
    public static final int MAX_BITES = 4;
    protected static final VoxelShape LOAF_SIZE_FULL = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    protected static final VoxelShape LOAF_SIZE_HALF = Block.createCuboidShape(0.0, 0.0, 7.0, 16.0, 8.0, 16.0);
    protected static final VoxelShape LOAF_SIZE_ONE = Block.createCuboidShape(0.0, 0.0, 7.0, 7.0, 8.0, 16.0);

    public SourbreadLoafBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(SIZE, 4));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int size = state.get(SIZE);
        if (size == 4){
            return LOAF_SIZE_FULL;
        } else if (size == 2){
            return LOAF_SIZE_HALF;
        } else if (size == 1){
            return LOAF_SIZE_ONE;
        } else {
            return LOAF_SIZE_FULL;
        }
    }

    public static ActionResult tryEat(BlockState state, WorldAccess world, BlockPos pos, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        int size = state.get(SIZE);
        if (size > 1){
            ItemStack itemStack2 = new ItemStack(LegacyItems.SOURBREAD_CHUNK);
            if (itemStack.isEmpty()) {
                player.setStackInHand(hand, itemStack2);
            } else if (!player.giveItemStack(itemStack2)) {
                player.dropItem(itemStack2, false);
            }
            world.setBlockState(pos, (BlockState) state.with(SIZE, size - 1), 3);
        } else if (size == 1){
            ItemStack itemStack2 = new ItemStack(LegacyItems.SOURBREAD_CHUNK);
            if (itemStack.isEmpty()) {
                player.setStackInHand(hand, itemStack2);
            } else if (!player.giveItemStack(itemStack2)) {
                player.dropItem(itemStack2, false);
            }
            world.breakBlock(pos, false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return tryEat(state, world, pos, player, hand);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SIZE);
    }

    static {
        SIZE = LegacyProperties.SIZE;
    }
}
