package net.sirraisinbran.legacy.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.mixin.ShovelItemAccess;

import java.util.IdentityHashMap;
import java.util.Map;

public class LegacyFlattenables {

    public static void addFlattenables() {
        Map<Block, BlockState> flattenables = new IdentityHashMap<>(ShovelItemAccess.getFlattenables());
        flattenables.put(LegacyBlocks.LUSH_SOIL, LegacyBlocks.LUSH_PATH.getDefaultState());
        ShovelItemAccess.setFlattenables(flattenables);
    }
}
