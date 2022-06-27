package net.sirraisinbran.legacy.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Mixin(HoeItem.class)
public interface HoeItemAccess {

    @Invoker("<init>")
    static HoeItem create(ToolMaterial tier, int i, float f, Item.Settings properties) {
        throw new Error("Mixin did not apply");
    }

    @Accessor("TILLING_ACTIONS")
    static Map<Block, Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> getTillables() {
        throw new Error("Mixin did not apply");
    }

    @Accessor("TILLING_ACTIONS")
    @Mutable
    static void setTillables(Map<Block, Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> tillables) {
        throw new Error("Mixin did not apply");
    }
}
