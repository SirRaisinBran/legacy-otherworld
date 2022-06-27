package net.sirraisinbran.legacy.util;

import net.minecraft.util.StringIdentifiable;

public enum SaguaroShape implements StringIdentifiable {
    TRUNK("trunk"),
    TRUNK_N("trunk_n"),
    TRUNK_W("trunk_w"),
    TRUNK_S("trunk_s"),
    TRUNK_E("trunk_e"),
    ARM("arm"),
    ARM_N("arm_n"),
    ARM_W("arm_w"),
    ARM_S("arm_s"),
    ARM_E("arm_e");

    private final String name;

    SaguaroShape(String name) { this.name = name; }

    public String toString() {
        return this.name;
    }

    public String asString() { return this.name; }
}
