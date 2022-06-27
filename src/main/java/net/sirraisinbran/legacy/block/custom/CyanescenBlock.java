package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.Direction;

public class CyanescenBlock extends MushroomBlock {
    public CyanescenBlock(AbstractBlock.Settings settings) {
        super(settings);
    }
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) ? true : super.isSideInvisible(state, stateFrom, direction);
    }
}
