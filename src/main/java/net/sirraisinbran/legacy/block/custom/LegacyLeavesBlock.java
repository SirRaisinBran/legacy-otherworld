package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class LegacyLeavesBlock extends LeavesBlock {

    private static final VoxelShape NORMAL_OUTLINE_SHAPE;
    private static final VoxelShape BOTTOM_OUTLINE_SHAPE;
    private static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    private static final VoxelShape OUTLINE_SHAPE = VoxelShapes.fullCube().offset(0.0, -1.0, 0.0);
    public static final BooleanProperty BOTTOM;


    public static final int MAX_DISTANCE = 7;
    public static final IntProperty DISTANCE;
    public static final BooleanProperty PERSISTENT;
    private static final int field_31112 = 1;

    public LegacyLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(BOTTOM, false)).with(DISTANCE, 7)).with(PERSISTENT, false));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (!context.isHolding(state.getBlock().asItem())) {
            return (Boolean)state.get(BOTTOM) ? BOTTOM_OUTLINE_SHAPE : NORMAL_OUTLINE_SHAPE;
        } else {
            return VoxelShapes.fullCube();
        }
    }

    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context.isAbove(VoxelShapes.fullCube(), pos, true) && !context.isDescending()) {
            return NORMAL_OUTLINE_SHAPE;
        } else {
            return (Boolean)state.get(BOTTOM) && context.isAbove(OUTLINE_SHAPE, pos, true) ? COLLISION_SHAPE : VoxelShapes.empty();
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{BOTTOM, DISTANCE, PERSISTENT});
    }

    static {
        DISTANCE = Properties.DISTANCE_1_7;
        PERSISTENT = Properties.PERSISTENT;
        BOTTOM = Properties.BOTTOM;
        VoxelShape voxelShape = Block.createCuboidShape(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
        VoxelShape voxelShape2 = Block.createCuboidShape(0.0, 0.0, 0.0, 2.0, 16.0, 2.0);
        VoxelShape voxelShape3 = Block.createCuboidShape(14.0, 0.0, 0.0, 16.0, 16.0, 2.0);
        VoxelShape voxelShape4 = Block.createCuboidShape(0.0, 0.0, 14.0, 2.0, 16.0, 16.0);
        VoxelShape voxelShape5 = Block.createCuboidShape(14.0, 0.0, 14.0, 16.0, 16.0, 16.0);
        NORMAL_OUTLINE_SHAPE = VoxelShapes.union(voxelShape, new VoxelShape[]{voxelShape2, voxelShape3, voxelShape4, voxelShape5});
        VoxelShape voxelShape6 = Block.createCuboidShape(0.0, 0.0, 0.0, 2.0, 2.0, 16.0);
        VoxelShape voxelShape7 = Block.createCuboidShape(14.0, 0.0, 0.0, 16.0, 2.0, 16.0);
        VoxelShape voxelShape8 = Block.createCuboidShape(0.0, 0.0, 14.0, 16.0, 2.0, 16.0);
        VoxelShape voxelShape9 = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 2.0);
        BOTTOM_OUTLINE_SHAPE = VoxelShapes.union(COLLISION_SHAPE, new VoxelShape[]{NORMAL_OUTLINE_SHAPE, voxelShape7, voxelShape6, voxelShape9, voxelShape8});
    }

}
