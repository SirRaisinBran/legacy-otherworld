package net.sirraisinbran.legacy.util;

import net.minecraft.util.StringIdentifiable;

public enum StemShape implements StringIdentifiable {
    SOLO("start"),
    ONE("one"),
    TWO("two"),
    TWO_C("two_c"),
    THREE("three"),
    FOUR("four");

    private final String name;

    StemShape(String name) { this.name = name; }

    public String toString() {
        return this.name;
    }

    public String asString() { return this.name; }
}

