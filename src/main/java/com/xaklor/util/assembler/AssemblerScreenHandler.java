package com.xaklor.util.assembler;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AssemblerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    private static final int INVENTORY_SIZE = 5;

    public AssemblerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(INVENTORY_SIZE), new ArrayPropertyDelegate(2));
    }
    public AssemblerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(TheAbandonedZoneMod.ASSEMBLER_SCREEN_HANDLER, syncId);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        checkSize(inventory, INVENTORY_SIZE);
        inventory.onOpen(playerInventory.player);

        this.addSlot(new Slot(inventory, 0,  44, 15));
        this.addSlot(new Slot(inventory, 1, 116, 15));
        this.addSlot(new Slot(inventory, 2,  44, 55));
        this.addSlot(new Slot(inventory, 3, 116, 55));
        this.addSlot(new Slot(inventory, 4,  80, 35));

        // player inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, i * 9 + j + 9, 8 + j * 18, 18 * i + 84));
            }
        }

        // player hotbar
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotId) {
        Slot slot = this.slots.get(slotId);
        if (slot.hasStack()) {
            // get two copies of the stack to be inserted so we can compare if anything was actually inserted
            ItemStack newStack = slot.getStack();
            ItemStack originalStack = newStack.copy();
            // if the quick move takes from output slot, insert into inventory slots
            if (slotId == 4) {
                if (!this.insertItem(newStack, 5, 41, true)) { return ItemStack.EMPTY; }
                slot.onQuickTransfer(newStack, originalStack);

            // if the quick move takes from an input slot, insert into inventory slots
            } else if (0 <= slotId && slotId < 4) {
                if(!this.insertItem(newStack, 5, 41, false)) { return ItemStack.EMPTY; }

            // if the quick move takes from the inventory, insert into input
            } else {
                boolean result = this.insertItem(newStack, 0, 4, false);
                // if that failed and this is a proper inventory slot, insert into hotbar
                if (!result && slotId >= 5 && slotId < 32) {
                    if(!this.insertItem(newStack, 32, 41, false)) { return ItemStack.EMPTY; }

                // if that failed and this is a hotbar slot, insert into inventory
                } else if (!result && slotId >= 32 && slotId < 41) {
                    if(!this.insertItem(newStack, 5, 32, false)) { return ItemStack.EMPTY; }
                }
                // if that failed and we somehow have a different slot, return nothing
                return ItemStack.EMPTY;
            }

            // if after inserting the stack is empty, clear the slot, otherwise mark the slot for an update
            if (newStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
            // if the final counts are the same, return nothing
            if (newStack.getCount() == originalStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, newStack);
        }
        // if the inserting stack was empty to begin with, return nothing
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public boolean isOn() {
        return propertyDelegate.get(0) > 0;
    }

    public int getWorkProgress() {
        return propertyDelegate.get(0);
    }

    public int getLevel() {
        return propertyDelegate.get(1);
    }
}
