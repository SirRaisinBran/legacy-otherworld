package net.sirraisinbran.legacy.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.entity.LegacyEntities;
import net.sirraisinbran.legacy.item.custom.*;

public class LegacyItems {

    public static final Item BRONZE_ALLOY = registerItem("bronze_alloy",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item CUPRONICKEL_ALLOY = registerItem("cupronickel_alloy",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item FERROZINC_ALLOY = registerItem("ferrozinc_alloy",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item NICHROME_ALLOY = registerItem("nichrome_alloy",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item LIGHT_GOLD_ALLOY = registerItem("light_gold_alloy",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item RED_GOLD_ALLOY = registerItem("red_gold_alloy",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));

    public static final Item AMALGAM_BLOB = registerItem("amalgam_blob",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item AMALGAM_BOTTLE = registerItem("amalgam_bottle",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item SPORE_BOTTLE = registerItem("spore_bottle",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item CLAY_GLOB = registerItem("clay_glob",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item STONEWARE_BOWL = registerItem("stoneware_bowl",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item BARK = registerItem("bark",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item PULP_GLOB = registerItem("pulp_glob",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));

    public static final Item SICKLE = registerItem("sickle",
            new SickleItem(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS).maxCount(1)));

    public static final Item QUICKSAND_BUCKET = registerItem("quicksand_bucket",
            new QuicksandBucketItem(LegacyBlocks.QUICKSAND, SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW, new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS).maxCount(1)));

    public static final Item CHARMED_ALLOY = registerItem("charmed_alloy",
            new Item(new FabricItemSettings().group(LegacyItemGroups.INCANTATION)));
    public static final Item COPPER_NUGGET = registerItem("copper_nugget",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));

    public static final Item DODO_FEATHER = registerItem("dodo_feather",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item EINKORN_WHEAT = registerItem("einkorn_wheat",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item EINKORN_SEEDS = registerItem("einkorn_seeds",
            new AliasedBlockItem(LegacyBlocks.EINKORN, new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item ESCENCE = registerItem("escence",
            new Item(new FabricItemSettings().group(LegacyItemGroups.INCANTATION)));

    public static final Item PEAR = registerItem("pear",
            new Item(new FabricItemSettings().group(LegacyItemGroups.FOOD).food(LegacyFoodComponents.PEAR)));
    public static final Item SOURBREAD_CHUNK = registerItem("sourbread_chunk",
            new Item(new FabricItemSettings().group(LegacyItemGroups.FOOD).food(LegacyFoodComponents.SOURBREAD).maxCount(16)));

    public static final Item RAW_NICKEL = registerItem("raw_nickel",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item RAW_TIN = registerItem("raw_tin",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item RAW_ZINC = registerItem("raw_zinc",
            new Item(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));

    public static final Item GABBRO_ROCK = registerItem("gabbro_rock",
            new RockItem(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item GRIM_ROCK = registerItem("grim_rock",
            new RockItem(new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));

    public static final Item CYAN_MUSHROOM = registerItem("cyan_mushroom",
            new WallStandingCeilingBlockItem(LegacyBlocks.CYAN_MUSHROOM, LegacyBlocks.CYAN_MUSHROOM_WALL, LegacyBlocks.CYAN_MUSHROOM_CEILING, new FabricItemSettings().group(LegacyItemGroups.DECORATION)));
    public static final Item SCARLET_MUSHROOM = registerItem("scarlet_mushroom",
            new WallStandingCeilingBlockItem(LegacyBlocks.SCARLET_MUSHROOM, LegacyBlocks.SCARLET_MUSHROOM_WALL, LegacyBlocks.SCARLET_MUSHROOM_CEILING, new FabricItemSettings().group(LegacyItemGroups.DECORATION)));
    public static final Item WHITE_MUSHROOM = registerItem("white_mushroom",
            new WallStandingCeilingBlockItem(LegacyBlocks.WHITE_MUSHROOM, LegacyBlocks.WHITE_MUSHROOM_WALL, LegacyBlocks.WHITE_MUSHROOM_CEILING, new FabricItemSettings().group(LegacyItemGroups.DECORATION)));

    public static final Item AUROCH_SPAWN_EGG = registerItem("auroch_spawn_egg",
            new SpawnEggItem(LegacyEntities.AUROCH_ENTITY_TYPE,0x392921, 0x271618,
                    new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item CLAMOR_SPAWN_EGG = registerItem("clamor_spawn_egg",
            new SpawnEggItem(LegacyEntities.CLAMOR_ENTITY_TYPE,0x101011, 0x2b5277,
                    new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item CRAULER_SPAWN_EGG = registerItem("crauler_spawn_egg",
            new SpawnEggItem(LegacyEntities.CRAULER_ENTITY_TYPE,0x4f4e50, 0x6465fb,
                    new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item DARTER_SPAWN_EGG = registerItem("darter_spawn_egg",
            new SpawnEggItem(LegacyEntities.DARTER_ENTITY_TYPE,0x390d0d, 0xc8c8c8,
                    new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item DODO_SPAWN_EGG = registerItem("dodo_spawn_egg",
            new SpawnEggItem(LegacyEntities.DODO_ENTITY_TYPE,0x2b2a37, 0xbda689,
                    new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item GLARE_SPAWN_EGG = registerItem("glare_spawn_egg",
            new SpawnEggItem(LegacyEntities.GLARE_ENTITY_TYPE,0x140e00, 0x37784c,
                    new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));
    public static final Item SHROUD_SPAWN_EGG = registerItem("shroud_spawn_egg",
            new SpawnEggItem(LegacyEntities.SHROUD_ENTITY_TYPE,0x682f0c, 0x689640,
                    new FabricItemSettings().group(LegacyItemGroups.MISCELLANEOUS)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(LegacyMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Items for " + LegacyMod.MOD_ID);

    }
}
