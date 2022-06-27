package net.sirraisinbran.legacy.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

public class WorkbenchScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final World world;

    public WorkbenchScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(18));

    }

    public WorkbenchScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(LegacyScreenHandlers.WORKBENCH_SCREEN_HANDLER, syncId);
        checkSize(inventory, 18);
        this.inventory = inventory;
        this.world = playerInventory.player.world;
        inventory.onOpen(playerInventory.player);

        // Our Slots
        this.addSlot(new Slot(inventory, 0, 17, 9));
        this.addSlot(new Slot(inventory, 1, 35, 9));
        this.addSlot(new Slot(inventory, 2, 53, 9));
        this.addSlot(new Slot(inventory, 3, 71, 9));
        this.addSlot(new Slot(inventory, 4, 17, 27));
        this.addSlot(new Slot(inventory, 5, 35, 27));
        this.addSlot(new Slot(inventory, 6, 53, 27));
        this.addSlot(new Slot(inventory, 7, 71, 27));
        this.addSlot(new Slot(inventory, 8, 17, 45));
        this.addSlot(new Slot(inventory, 9, 35, 45));
        this.addSlot(new Slot(inventory, 10, 53, 45));
        this.addSlot(new Slot(inventory, 11, 71, 45));
        this.addSlot(new Slot(inventory, 12, 17, 63));
        this.addSlot(new Slot(inventory, 13, 35, 63));
        this.addSlot(new Slot(inventory, 14, 53, 63));
        this.addSlot(new Slot(inventory, 15, 71, 63));

        this.addSlot(new Slot(inventory, 16, 136, 14));
        this.addSlot(new Slot(inventory, 17, 146, 44));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}