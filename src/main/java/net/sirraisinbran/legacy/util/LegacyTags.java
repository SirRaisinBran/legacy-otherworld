package net.sirraisinbran.legacy.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.sirraisinbran.legacy.LegacyMod;

public class LegacyTags {

    public static class Blocks {

        public static final TagKey<Block> ARAUCARIA_WOOD =
                createCommonTag("araucaria_wood");
        public static final TagKey<Block> SOIL =
                createCommonTag("soil");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(LegacyMod.MOD_ID, name));
        }

        // Common Tags are just for simply categorizing groups of blocks/items
        private static TagKey<Block> createCommonTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }

    }

    public static class Items {


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(LegacyMod.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }
    }

}
