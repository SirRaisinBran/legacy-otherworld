package net.sirraisinbran.legacy.util;

import net.minecraft.util.StringIdentifiable;

public enum ScarletShape implements StringIdentifiable {

    FLOOR("floor"),
    CORNER("corner"),
    WALL("wall");

    private final String name;

    ScarletShape(String name) { this.name = name; }

    public String toString() {
        return this.name;
    }

    public String asString() { return this.name; }
}
