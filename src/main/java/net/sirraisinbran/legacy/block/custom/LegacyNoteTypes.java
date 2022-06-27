package net.sirraisinbran.legacy.block.custom;
import net.minecraft.util.SignType;
import net.sirraisinbran.legacy.mixin.SignTypeAccessor;

public class LegacyNoteTypes {
    public static final SignType COMMON_NOTE =
            SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("common_note"));
}
