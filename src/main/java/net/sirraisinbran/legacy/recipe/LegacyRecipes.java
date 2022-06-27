package net.sirraisinbran.legacy.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sirraisinbran.legacy.LegacyMod;

public class LegacyRecipes {
    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(LegacyMod.MOD_ID, BloomeryRecipe.Serializer.ID),
                BloomeryRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(LegacyMod.MOD_ID, BloomeryRecipe.Type.ID),
                BloomeryRecipe.Type.INSTANCE);
    }
}
