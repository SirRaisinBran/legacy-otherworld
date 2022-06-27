package net.sirraisinbran.legacy.util;

import net.minecraft.util.StringIdentifiable;

public enum ScarletMushroomShape implements StringIdentifiable {
    LONE("lone"),
    STEM("stem"),
    BOTTOM("bottom");

    private final String name;

    ScarletMushroomShape(String name) { this.name = name; }

    public String toString() {
        return this.name;
    }

    public String asString() { return this.name; }
}
