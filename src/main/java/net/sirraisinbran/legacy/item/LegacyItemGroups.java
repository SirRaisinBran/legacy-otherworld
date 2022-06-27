package net.sirraisinbran.legacy.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;

public class LegacyItemGroups {

    public static final ItemGroup BUILDING = FabricItemGroupBuilder.build(new Identifier(LegacyMod.MOD_ID, "building"),
            () -> new ItemStack(LegacyBlocks.CHISELED_REDSPOT));
    public static final ItemGroup DECORATION = FabricItemGroupBuilder.build(new Identifier(LegacyMod.MOD_ID, "decoration"),
            () -> new ItemStack(LegacyBlocks.CYAN_MUSHROOM));
    public static final ItemGroup INCANTATION = FabricItemGroupBuilder.build(new Identifier(LegacyMod.MOD_ID, "incantation"),
            () -> new ItemStack(LegacyItems.ESCENCE));
    public static final ItemGroup MISCELLANEOUS = FabricItemGroupBuilder.build(new Identifier(LegacyMod.MOD_ID, "miscellaneous"),
            () -> new ItemStack(LegacyBlocks.LARGE_EGG));
    public static final ItemGroup FOOD = FabricItemGroupBuilder.build(new Identifier(LegacyMod.MOD_ID, "food"),
            () -> new ItemStack(LegacyItems.PEAR));



}
