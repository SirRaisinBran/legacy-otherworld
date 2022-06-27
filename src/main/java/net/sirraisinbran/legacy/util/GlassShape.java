package net.sirraisinbran.legacy.util;

import net.minecraft.util.StringIdentifiable;

public enum GlassShape implements StringIdentifiable {
    FULL("trunk"),
    LEFT("trunk_n"),
    RIGHT("trunk_w"),
    TOP("trunk_n"),
    BOTTOM("trunk_w"),
    TOP_LEFT("trunk_s"),
    TOP_RIGHT("trunk_e"),
    BOTTOM_LEFT("arm"),
    BOTTOM_RIGHT("arm_n");

    private final String name;

    GlassShape(String name) { this.name = name; }

    public String toString() {
        return this.name;
    }

    public String asString() { return this.name; }
}
