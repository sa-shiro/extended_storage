package com.github.sa_shiro.extended_storage.container;

import com.github.sa_shiro.extended_storage.event.DeferredRegistryEvent;
import com.github.sa_shiro.extended_storage.tileentity.IronChestTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class IronChestContainer extends Container {

    public static final int numRows = 6;
    public static final int numColumns = 9;
    public final IronChestTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;


    public IronChestContainer(final int windowId, final PlayerInventory playerInventoryIn, final IronChestTileEntity tileEntity) {
        super(DeferredRegistryEvent.IRON_CHEST_CONTAINER.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(Objects.requireNonNull(tileEntity.getWorld()), tileEntity.getPos());

        int slotSize = 16 + 2;
        int startX = 8;
        int startY = 18;
        int playerStartY = 140;
        int hotBarY = 198;

        // Chest Inventory
        for (int row = 0; row < numRows; ++row) {
            for (int column = 0; column < numColumns; ++column) {
                this.addSlot(new Slot(tileEntity, (row * numColumns) + column, startX + (column * slotSize), startY + (row * slotSize)));
            }
        }

        // Player Inventory
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < numColumns; ++column) {
                this.addSlot(new Slot(playerInventoryIn, column + (row * numColumns) + numColumns, startX + (column * slotSize), playerStartY + (row * slotSize)));
            }
        }

        // Player HotBar
        for (int column = 0; column < numColumns; ++column) {
            this.addSlot(new Slot(playerInventoryIn, column, startX + (column * slotSize), hotBarY));
        }

    }

    public IronChestContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static IronChestTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory can't be null!");
        Objects.requireNonNull(data, "data can't be null!");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof IronChestTileEntity) {
            return (IronChestTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not valid!" + tileAtPos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, DeferredRegistryEvent.IRON_CHEST.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (index < numRows * numColumns) {
                if (!this.mergeItemStack(itemStack1, numRows * numColumns, this.inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!this.mergeItemStack(itemStack1, 0, numRows * numColumns, false))
                return ItemStack.EMPTY;

            if (itemStack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
            else slot.onSlotChanged();

        }
        return itemStack;
    }
}
