package net.sirraisinbran.legacy.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.DirtPathBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DirtPathBlock.class)
public interface PathBlockAccess {

    @Invoker("<init>")
    static DirtPathBlock create(AbstractBlock.Settings properties) {
        throw new Error("Mixin did not apply!");
    }
}
