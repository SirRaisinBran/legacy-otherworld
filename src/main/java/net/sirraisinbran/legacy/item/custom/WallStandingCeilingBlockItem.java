package net.sirraisinbran.legacy.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallStandingCeilingBlockItem extends BlockItem {
    protected final Block wallBlock;
    protected final Block ceilingBlock;

    public WallStandingCeilingBlockItem(Block standingBlock, Block wallBlock, Block ceilingBlock, Item.Settings settings) {
        super(standingBlock, settings);
        this.wallBlock = wallBlock;
        this.ceilingBlock = ceilingBlock;
    }

    @Nullable
    protected BlockState getPlacementState(ItemPlacementContext context) {
        BlockState blockState = this.wallBlock.getPlacementState(context);
        BlockState blockState2 = null;
        BlockState blockState4 = this.ceilingBlock.getPlacementState(context);
        WorldView worldView = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        Direction[] var6 = context.getPlacementDirections();
        int var7 = var6.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Direction direction = var6[var8];
            if (direction != Direction.UP) {
                BlockState blockState3 = direction == Direction.DOWN ? this.getBlock().getPlacementState(context) : blockState;
                if (blockState3 != null && blockState3.canPlaceAt(worldView, blockPos)) {
                    blockState2 = blockState3;
                    break;
                }
            } else {
                if (blockState4 != null && blockState4.canPlaceAt(worldView, blockPos)) {
                    blockState2 = blockState4;
                    break;
                }
            }
        }

        return blockState2 != null && worldView.canPlace(blockState2, blockPos, ShapeContext.absent()) ? blockState2 : null;
    }

    public void appendBlocks(Map<Block, Item> map, Item item) {
        super.appendBlocks(map, item);
        map.put(this.wallBlock, item);
        map.put(this.ceilingBlock, item);
    }
}
