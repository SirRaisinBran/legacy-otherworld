package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.item.LegacyItems;

public class AraucariaLogBlock extends PillarBlock {
    
    public AraucariaLogBlock(Settings settings) {
        super(settings);
    }

}
