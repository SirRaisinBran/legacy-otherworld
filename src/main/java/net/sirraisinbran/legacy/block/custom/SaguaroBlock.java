package net.sirraisinbran.legacy.block.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.sirraisinbran.legacy.LegacyMod;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.util.LegacyProperties;
import net.sirraisinbran.legacy.util.SaguaroShape;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Random;

public class SaguaroBlock extends Block {

    public static final EnumProperty<SaguaroShape> SHAPE = LegacyProperties.SAGUARO_SHAPE;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public SaguaroBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(SHAPE, SaguaroShape.TRUNK));
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Iterator var4 = Direction.Type.HORIZONTAL.iterator();

        Direction direction;
        Material material;
        do {
            if (!var4.hasNext()) {
                BlockState blockState2 = world.getBlockState(pos.down());
                BlockState blockState3 = world.getBlockState(pos.north());
                BlockState blockState4 = world.getBlockState(pos.west());
                BlockState blockState5 = world.getBlockState(pos.south());
                BlockState blockState6 = world.getBlockState(pos.east());
                return ((blockState2.isOf(LegacyBlocks.DRY_SAND))
                        || (blockState2.isAir() && blockState3.isOf(LegacyBlocks.SAGUARO))
                        || (blockState2.isAir() && blockState4.isOf(LegacyBlocks.SAGUARO))
                        || (blockState2.isAir() && blockState5.isOf(LegacyBlocks.SAGUARO))
                        || (blockState2.isAir() && blockState6.isOf(LegacyBlocks.SAGUARO)))
                        && !world.getBlockState(pos.up()).getMaterial().isLiquid();
            }

            direction = (Direction)var4.next();
            BlockState blockState = world.getBlockState(pos.offset(direction));
            material = blockState.getMaterial();
        } while(!material.isSolid() && !world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA));

        return false;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, FACING);
    }
}
