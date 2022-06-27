package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.sirraisinbran.legacy.util.HungerStatus;
import net.sirraisinbran.legacy.util.LegacyProperties;
import net.sirraisinbran.legacy.util.PearShape;

public class SourbreadStarterBlock extends Block {

    public static final IntProperty TIMES_FED;
    public static final EnumProperty<HungerStatus> HUNGER_STATUS;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 14.0, 12.0);

    public SourbreadStarterBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(TIMES_FED, 0)).with(HUNGER_STATUS, HungerStatus.FED));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TIMES_FED, HUNGER_STATUS);
    }

    static {
        TIMES_FED = LegacyProperties.TIMES_FED;
        HUNGER_STATUS = LegacyProperties.HUNGER_STATUS;
    }
}
