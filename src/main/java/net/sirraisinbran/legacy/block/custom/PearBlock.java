package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.util.LegacyProperties;
import net.sirraisinbran.legacy.util.PearShape;

public class PearBlock extends Block {

    public static final EnumProperty<PearShape> PEAR_GROWTH;
    public static final IntProperty AGE;

    public PearBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(PEAR_GROWTH, PearShape.START)).with(AGE, 0));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return this.canGrowUnder(world.getBlockState(blockPos), world, blockPos);
    }

    protected boolean canGrowUnder(BlockState state, BlockView world, BlockPos pos) {
        return state.isOf(LegacyBlocks.KOKIA_LEAVES);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PEAR_GROWTH, AGE);
    }

    static {
        PEAR_GROWTH = LegacyProperties.PEAR_GROWTH;
        AGE = Properties.AGE_15;
    }
}
