package net.sirraisinbran.legacy.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {


    @Inject(method = "tickMovement", at = @At("HEAD"))
    public void tickMovement() {

    }
}
