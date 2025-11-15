package net.skebob.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.skebob.block.entity.ImplementedInventory;
import net.skebob.block.entity.ModBlockEntities;
import net.skebob.screen.custom.PedestalScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

public class PedestalBlockEntity extends BlockEntity implements ImplementedInventory, SidedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(99, ItemStack.EMPTY);
    private PedestalRenderConfig renderConfig = PedestalRenderConfig.defaultSingleItem();
    private float rotation = 0;

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PEDESTAL_BE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    public DefaultedList<ItemStack> getNonNullItems() {
        return inventory.stream().filter(itemStack -> !itemStack.isEmpty())
                .collect(Collectors.toCollection(DefaultedList::of));
    }

    public void push(ItemStack item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).isEmpty()) {
                inventory.set(i, item);
                break;
            }
        }
    }

    public void pop() {
        for (int i = inventory.size()-1; i >= 0; i--) {
            if (!inventory.get(i).isEmpty()) {
                inventory.set(i, ItemStack.EMPTY);
                break;
            }
        }
    }

    public ItemStack peek() {
        for (ItemStack item : getItems().reversed()) {
            if (!item.isEmpty()) {
                return item;
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean hasRoom() {
        for (ItemStack item : getItems()) {
            if (item.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public float getRenderingRotation() {
        return rotation;
    }
    public void setRenderConfig(PedestalRenderConfig renderConfig) {
        this.renderConfig = renderConfig;
    }
    public PedestalRenderConfig getRenderConfig() {
        return renderConfig;
    }

    public void updateRotation() {
        updateRotation(0.5f);
    }
    public void updateRotation(float rotation) {
        this.rotation += rotation;
        if (this.rotation > 360) this.rotation = 0;
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, inventory);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, inventory);
    }

    @Override
    public void onBlockReplaced(BlockPos pos, BlockState oldState) {
        ItemScatterer.spawn(world, pos, this);
        super.onBlockReplaced(pos, oldState);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return false;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[0];
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (this.world != null) {
            this.world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
        Block block = state.getBlock();
        world.addSyncedBlockEvent(pos, block, 1, newViewerCount);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Pedestal");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PedestalScreenHandler(syncId, playerInventory, this.pos);
    }
}
