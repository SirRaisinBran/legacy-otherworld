package net.sirraisinbran.legacy.util;

import net.minecraft.util.StringIdentifiable;

public enum LogShape implements StringIdentifiable {

    STRAIGHT("straight"),
    TOP_RIGHT("top_right"),
    TOP_LEFT("top_left"),
    BOTTOM_RIGHT("bottom_right"),
    BOTTOM_LEFT("bottom_left");

    private final String name;

    LogShape(String name) { this.name = name; }

    public String toString() {
        return this.name;
    }

    public String asString() { return this.name(); }
}
