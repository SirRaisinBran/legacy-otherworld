package net.sirraisinbran.legacy.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;

public class ScarletBrainScreenHandler extends ScreenHandler {

    public ScarletBrainScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(LegacyScreenHandlers.SCARLET_BRAIN_SCREEN_HANDLER, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return false;
    }
}