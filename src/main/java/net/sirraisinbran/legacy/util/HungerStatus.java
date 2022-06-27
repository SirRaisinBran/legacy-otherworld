package net.sirraisinbran.legacy.util;

import net.minecraft.util.StringIdentifiable;

public enum HungerStatus implements StringIdentifiable {
    FED("fed"),
    HUNGRY("hungry");

    private final String name;

    HungerStatus(String name) { this.name = name; }

    public String toString() {
        return this.name;
    }

    public String asString() { return this.name; }
}
