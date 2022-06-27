package net.sirraisinbran.legacy.item;

import net.minecraft.item.FoodComponent;

public class LegacyFoodComponents {

    public static FoodComponent PEAR = new FoodComponent.Builder().hunger(4).saturationModifier(0.3f).build();
    public static FoodComponent SOURBREAD = new FoodComponent.Builder().hunger(14).saturationModifier(0.1f).build();
}
