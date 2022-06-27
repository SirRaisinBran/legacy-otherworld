package net.sirraisinbran.legacy.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.sirraisinbran.legacy.block.custom.QuicksandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Shadow
    private static float red;
    @Shadow
    private static float green;
    @Shadow
    private static float blue;
    @Shadow
    private static long lastWaterFogColorUpdateTime = -1L;


    @Environment(EnvType.CLIENT)
    @Inject(method = "render", at = @At("TAIL"))
    private static void quicksandRenderColor(Camera camera, float tickDelta, ClientWorld world, int i, float f, CallbackInfo ci) {
        BlockPos playerPos = new BlockPos(MinecraftClient.getInstance().player.getEyePos());
        if (camera.getSubmersionType() == CameraSubmersionType.WATER
                && world.getBlockState(playerPos).getBlock() instanceof QuicksandBlock) {
            red = 0.6F;
            green = 0.1F;
            blue = 0.0F;
            lastWaterFogColorUpdateTime = -1L;
            RenderSystem.clearColor(red, green, blue, 0.0F);
        }
    }
}