package net.sirraisinbran.legacy.util;

import net.minecraft.block.enums.StairShape;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public class LegacyProperties {

    public static final IntProperty CACTI;
    public static final IntProperty SHROOMS;
    public static final IntProperty SCARLET_AGE;
    public static final IntProperty MAX_HEIGHT;
    public static final IntProperty AGE_7;
    public static final IntProperty AGE_4;
    public static final IntProperty SIZE;
    public static final IntProperty TIMES_FED;
    public static final EnumProperty<LogShape> LOG_SHAPE;
    public static final EnumProperty<HungerStatus> HUNGER_STATUS;
    public static final EnumProperty<PearShape> PEAR_GROWTH;
    public static final EnumProperty<SaguaroShape> SAGUARO_SHAPE;
    public static final EnumProperty<StemShape> STEM_SHAPE;

    // Scarlet
    public static final BooleanProperty STATUS = BooleanProperty.of("status");
    public static final EnumProperty<ScarletShape> SCARLET_SHAPE;
    public static final EnumProperty<ScarletMushroomShape> SCARLET_MUSHROOM_SHAPE;

    public static final BooleanProperty HAS_MAX = BooleanProperty.of("has_max");

    public static final BooleanProperty ROCKY = BooleanProperty.of("rocky");


    public LegacyProperties() {
    }

    static {

        CACTI = IntProperty.of("cacti", 1, 6);
        SHROOMS = IntProperty.of("shrooms", 1, 2);
        SCARLET_AGE = IntProperty.of("age", 1, 15);
        MAX_HEIGHT = IntProperty.of("max", 3, 6);
        AGE_7 = IntProperty.of("age", 0, 100);
        AGE_4 = IntProperty.of("age", 0, 4);
        SIZE = IntProperty.of("size", 0, 4);
        TIMES_FED = IntProperty.of("times", 0, 4);
        LOG_SHAPE = EnumProperty.of("shape", LogShape.class);
        HUNGER_STATUS = EnumProperty.of("status", HungerStatus.class);
        PEAR_GROWTH = EnumProperty.of("growth", PearShape.class);
        SAGUARO_SHAPE = EnumProperty.of("shape", SaguaroShape.class);
        STEM_SHAPE = EnumProperty.of("shape", StemShape.class);

        SCARLET_SHAPE = EnumProperty.of("shape", ScarletShape.class);
        SCARLET_MUSHROOM_SHAPE = EnumProperty.of("shape", ScarletMushroomShape.class);

    }
}
