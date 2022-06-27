package net.sirraisinbran.legacy.mixin;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {

    @Inject(method = "useOnBlock",at = @At("HEAD"))
    public void ifAraucariaLog(ItemUsageContext context) {

    }
}
