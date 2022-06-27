package net.sirraisinbran.legacy.item.custom;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import java.util.Map;

public class DoubleBlockItem extends BlockItem {

    protected final Block brainBlock;

    public DoubleBlockItem(Block block, Block brainBlock, Item.Settings settings) {
        super(block, settings);
        this.brainBlock = brainBlock;
    }

    public void appendBlocks(Map<Block, Item> map, Item item) {
        super.appendBlocks(map, item);
        map.put(this.brainBlock, item);
    }

}
