/*
package net.sirraisinbran.legacy.world.tree.decorator;


import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.sirraisinbran.legacy.block.LegacyBlocks;
import net.sirraisinbran.legacy.block.custom.CyanShroomWallBlock;
import net.sirraisinbran.legacy.block.custom.WhiteShroomWallBlock;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class WhiteMushroomTreeDecorator extends TreeDecorator {

    public static final Codec<WhiteMushroomTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(WhiteMushroomTreeDecorator::new, (decorator) -> {
        return decorator.probability;
    }).codec();
    private final float probability;

    public WhiteMushroomTreeDecorator(float probability) {
        this.probability = probability;
    }

    protected TreeDecoratorType<?> getType() {
        return LegacyTreeDecorator.MUSHROOM_WHITE;
    }

    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
        if (!(random.nextFloat() >= this.probability)) {
            int i = ((BlockPos)logPositions.get(0)).getY();
            logPositions.stream().filter((pos) -> {
                return pos.getY() - i <= 2;
            }).forEach((pos) -> {
                Iterator var4 = Direction.Type.HORIZONTAL.iterator();

                while(var4.hasNext()) {
                    Direction direction = (Direction)var4.next();
                    if (random.nextFloat() <= 0.25F) {
                        Direction direction2 = direction.getOpposite();
                        BlockPos blockPos = pos.add(direction2.getOffsetX(), 0, direction2.getOffsetZ());
                        if (Feature.isAir(world, blockPos)) {
                            replacer.accept(blockPos, ((BlockState) LegacyBlocks.WHITE_MUSHROOM_WALL.getDefaultState().with(WhiteShroomWallBlock.FACING, direction)));
                        }
                    }
                }

            });
        }
    }
}

 */