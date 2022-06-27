package net.sirraisinbran.legacy.fluid;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sirraisinbran.legacy.LegacyMod;

public class LegacyFluids {

    //public static final FlowableFluid QUICKSAND = register("quicksand", new QuicksandFluid.Still());
    //public static final FlowableFluid AMALGAM = register("amalgam", new AmalgamFluid.Flowing());

    public static FlowableFluid register(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registry.FLUID, new Identifier(LegacyMod.MOD_ID, name), flowableFluid);
    }
}
