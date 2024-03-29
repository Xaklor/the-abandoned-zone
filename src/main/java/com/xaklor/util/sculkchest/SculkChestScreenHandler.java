package com.xaklor.util.sculkchest;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class SculkChestScreenHandler extends GenericContainerScreenHandler {
    private final Inventory sculkInventory;


    public static SculkChestScreenHandler createGeneric9x3(int syncId, PlayerInventory playerInventory, Inventory inventory, Inventory sculkChest) {
        return new SculkChestScreenHandler(ScreenHandlerType.GENERIC_9X3, syncId, playerInventory, inventory, 3, sculkChest);
    }

    public static SculkChestScreenHandler createGeneric9x6(int syncId, PlayerInventory playerInventory, Inventory inventory, Inventory sculkChest) {
        return new SculkChestScreenHandler(ScreenHandlerType.GENERIC_9X6, syncId, playerInventory, inventory, 6, sculkChest);
    }

    public SculkChestScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, int rows, Inventory sculkInventory) {
        super(type, syncId, playerInventory, inventory, rows);
        this.sculkInventory = sculkInventory;
        sculkInventory.onOpen(playerInventory.player);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.sculkInventory.canPlayerUse(player);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        sculkInventory.onClose(player);
    }
}
