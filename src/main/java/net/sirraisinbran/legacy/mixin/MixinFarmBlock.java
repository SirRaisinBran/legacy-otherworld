package net.sirraisinbran.legacy.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public class MixinFarmBlock {

    // Is there a better way to do this?
    // Doesn't seem like it sadly :(
    @Inject(method = "setToDirt", at = @At("RETURN"))
    private static void turnTillToSoil(BlockState state, World level, BlockPos pos, CallbackInfo ci) {
        if (state.isOf(LegacyBlocks.LUSH_SOIL) ||state.isOf(LegacyBlocks.TILLED_SOIL)) {
            level.setBlockState(pos, Block.pushEntitiesUpBeforeBlockChange(state, LegacyBlocks.SOIL.getDefaultState(), level, pos));
        } else if (state.isOf(LegacyBlocks.ROCKY_SOIL) ||state.isOf(LegacyBlocks.TILLED_SOIL)) {
            level.setBlockState(pos, Block.pushEntitiesUpBeforeBlockChange(state, LegacyBlocks.SOIL.getDefaultState(), level, pos));
        }
    }
}
