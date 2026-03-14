package net.orbit.screen.custom;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.orbit.block.entity.custom.PedestalBlockEntity;
import net.orbit.block.entity.custom.PedestalRenderConfig;
import net.orbit.screen.ModScreenHandlers;

public class PedestalScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PedestalBlockEntity blockEntity;
    public PedestalScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos blockPos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(blockPos));
    }

    public PedestalScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, buf.readBlockPos());
    }

    public PedestalScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreenHandlers.PEDESTAL_SCREEN_HANDLER, syncId);
        this.inventory = (Inventory) blockEntity;
        this.blockEntity = (PedestalBlockEntity) blockEntity;
//        add27Slots(inventory);
//        addPlayerInventory(playerInventory);
//        addPlayerHotbar(playerInventory);
    }

    public PedestalRenderConfig getRenderConfig() {
        return blockEntity.getRenderConfig();
    }
    public BlockPos getBlockPos() {
        return blockEntity.getPos();
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
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

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void add27Slots(Inventory inventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inventory, j + i * 9, 8 + j * 18, 18 + i * 18) {
                    @Override
                    public int getMaxItemCount() {
                        return 1;
                    }
                });
            }
        }
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
