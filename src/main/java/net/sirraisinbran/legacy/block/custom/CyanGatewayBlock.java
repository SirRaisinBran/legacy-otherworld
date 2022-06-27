package net.sirraisinbran.legacy.block.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.interfaces.EntityInCustomPortal;
import net.kyrptonaught.customportalapi.portal.frame.PortalFrameTester;
import net.kyrptonaught.customportalapi.util.CustomPortalHelper;
import net.kyrptonaught.customportalapi.util.PortalLink;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.sirraisinbran.legacy.block.LegacyBlocks;

import java.util.Random;

public class CyanGatewayBlock extends CustomPortalBlock {
    public CyanGatewayBlock(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(!(MinecraftClient.getInstance().player instanceof EntityInCustomPortal entity && entity.didTeleport()) && random.nextInt(200) == 0){
            world.getProfiler().push("portal");
            world.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
            world.getProfiler().pop();
        }

        double d = (double) pos.getX() + random.nextDouble();
        double e = (double) pos.getY() + random.nextDouble();
        double f = (double) pos.getZ() + random.nextDouble();
        double g = ((double) random.nextFloat() - 0.5D) * 0.5D;
        double h = ((double) random.nextFloat() - 0.5D) * 0.5D;
        double j = ((double) random.nextFloat() - 0.5D) * 0.5D;
        int k = random.nextInt(2) * 2 - 1;
        if (!world.getBlockState(pos.west()).isOf(this) && !world.getBlockState(pos.east()).isOf(this)) {
            d = (double) pos.getX() + 0.5D + 0.25D * (double) k;
            g = random.nextFloat() * 2.0F * (float) k;
        } else {
            f = (double) pos.getZ() + 0.5D + 0.25D * (double) k;
            j = random.nextFloat() * 2.0F * (float) k;
        }
        if (world.getRandom().nextInt(6) != 0) world.addParticle(ParticleTypes.WARPED_SPORE, d, e, f, g, h, j);
        else world.addParticle(ParticleTypes.SPORE_BLOSSOM_AIR, d, e, f, 0, 0, 0);
    }

    @Override
    public Block getPortalBase(World world, BlockPos pos) {
        return LegacyBlocks.CHISELED_REDSPOT;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        Block block = getPortalBase((World) world, pos);
        PortalLink link = CustomPortalApiRegistry.getPortalLinkFromBase(block);
        if (link != null) {
            PortalFrameTester portalFrameTester = link.getFrameTester().createInstanceOfPortalFrameTester().init(world, pos, CustomPortalHelper.getAxisFrom(state), block);
            if (portalFrameTester.isAlreadyLitPortalFrame())
                return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
        }

        return LegacyBlocks.CYAN_GATEWAY.getDefaultState();
    }
}
