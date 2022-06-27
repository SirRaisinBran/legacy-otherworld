package net.sirraisinbran.legacy.util;

import net.minecraft.util.StringIdentifiable;

public enum PearShape implements StringIdentifiable {
    START("start"),
    TWO("two"),
    THREE("three"),
    RIPE("ripe");

    private final String name;

    PearShape(String name) { this.name = name; }

    public String toString() {
        return this.name;
    }

    public String asString() { return this.name; }
}
