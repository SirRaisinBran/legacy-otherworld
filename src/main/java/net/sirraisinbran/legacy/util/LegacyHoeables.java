package net.sirraisinbran.legacy.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.mixin.HoeItemAccess;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LegacyHoeables {

    public static void tillablesLegacy() {
        LegacyMod.LOGGER.debug("Legacy: Adding tillables...");
        Map<Block, Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> tillables = new IdentityHashMap<>(HoeItemAccess.getTillables());
        tillables.put(LegacyBlocks.LUSH_SOIL, Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(LegacyBlocks.TILLED_SOIL.getDefaultState())));
        tillables.put(LegacyBlocks.SOIL, Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(LegacyBlocks.TILLED_SOIL.getDefaultState())));
        HoeItemAccess.setTillables(tillables);
        LegacyMod.LOGGER.info("Legacy: Added tillables!");
    }

}
