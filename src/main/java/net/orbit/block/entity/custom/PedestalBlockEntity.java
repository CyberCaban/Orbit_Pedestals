package net.orbit.block.entity.custom;

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
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.orbit.block.entity.ImplementedInventory;
import net.orbit.block.entity.ModBlockEntities;
import net.orbit.screen.custom.PedestalScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

import static net.orbit.block.entity.custom.PedestalRenderConfig.Builder.configToBuilder;

public class PedestalBlockEntity extends BlockEntity implements ImplementedInventory, SidedInventory, ExtendedScreenHandlerFactory {
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
        for (int i = inventory.size() - 1; i >= 0; i--) {
            if (!inventory.get(i).isEmpty()) {
                inventory.set(i, ItemStack.EMPTY);
                break;
            }
        }
    }

    public ItemStack peek() {
        for (int i = inventory.size() - 1; i >= 0; i--) {
            ItemStack item = inventory.get(i);
            if (!item.isEmpty()) return item;
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
        markDirty();
        if (world != null && !world.isClient) {
            world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), Block.NOTIFY_ALL);
        }
    }
    public void updateConfigField(String fieldName, float value) {
        PedestalRenderConfig.Builder builder = configToBuilder(renderConfig);
        switch (fieldName) {
            case "rotationSpeed" -> builder.rotationSpeed(value);
            case "baseHeight" -> builder.baseHeight(value);
            case "singleItemScale" -> builder.singleItemScale(value);
            case "levitationAmplitude" -> builder.levitationAmplitude(value);
            case "levitationSpeed" -> builder.levitationSpeed(value);
            case "radius" -> builder.radius(value);
            case "multiItemLevitationAmplitude" -> builder.multiItemLevitationAmplitude(value);
        }
        setRenderConfig(builder.build());
    }

    public void updateConfigField(String fieldName, boolean value) {
        PedestalRenderConfig.Builder builder = configToBuilder(renderConfig);
        switch (fieldName) {
            case "forceRenderItem" -> builder.forceRenderItem(value);
            case "multiItemFancyRotation" -> builder.multiItemFancyRotation(value);
        }
        setRenderConfig(builder.build());
    }

    public void updateConfigField(String fieldName, Vec3d value) {
        PedestalRenderConfig.Builder builder = configToBuilder(renderConfig);

        switch (fieldName) {
            case "itemOffset" -> builder.singleItemOffset(value);
            case "itemRotation" -> builder.itemRotation(value);
            case "rotationStep" -> builder.rotationStep(value);
        }

        setRenderConfig(builder.build());
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
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        writeRenderConfigToNbt(nbt);
        nbt.putFloat("rotation", rotation);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        // Important for incremental sync packets: clear stale stacks first.
        inventory.clear();
        Inventories.readNbt(nbt, inventory);
        this.renderConfig = readRenderConfigFromNbt(nbt);
        this.rotation = nbt.getFloat("rotation");
    }

    private void writeRenderConfigToNbt(NbtCompound nbt) {
        nbt.putFloat("cfg_rotationSpeed", renderConfig.rotationSpeed());
        nbt.putFloat("cfg_baseHeight", renderConfig.baseHeight());
        nbt.putFloat("cfg_itemScale", renderConfig.itemScale());
        nbt.putFloat("cfg_levitationAmplitude", renderConfig.levitationAmplitude());
        nbt.putFloat("cfg_levitationSpeed", renderConfig.levitationSpeed());
        nbt.putFloat("cfg_radius", renderConfig.radius());
        nbt.putFloat("cfg_multiItemLevitationAmplitude", renderConfig.multiItemLevitationAmplitude());
        nbt.putBoolean("cfg_forceRenderItem", renderConfig.forceRenderItem());
        nbt.putBoolean("cfg_multiItemFancyRotation", renderConfig.multiItemFancyRotation());

        writeVec3dToNbt(nbt, "cfg_offset", renderConfig.itemOffset());
        writeVec3dToNbt(nbt, "cfg_rotation", renderConfig.itemRotation());
        writeVec3dToNbt(nbt, "cfg_rotStep", renderConfig.rotationStep());
    }

    private PedestalRenderConfig readRenderConfigFromNbt(NbtCompound nbt) {
        return new PedestalRenderConfig.Builder()
                .rotationSpeed(nbt.getFloat("cfg_rotationSpeed"))
                .baseHeight(nbt.getFloat("cfg_baseHeight"))
                .singleItemScale(nbt.getFloat("cfg_itemScale"))
                .levitationAmplitude(nbt.getFloat("cfg_levitationAmplitude"))
                .levitationSpeed(nbt.getFloat("cfg_levitationSpeed"))
                .radius(nbt.getFloat("cfg_radius"))
                .multiItemLevitationAmplitude(nbt.getFloat("cfg_multiItemLevitationAmplitude"))
                .singleItemOffset(readVec3dFromNbt(nbt, "cfg_offset"))
                .itemRotation(readVec3dFromNbt(nbt, "cfg_rotation"))
                .rotationStep(readVec3dFromNbt(nbt, "cfg_rotStep"))
                .multiItemFancyRotation(nbt.getBoolean("cfg_multiItemFancyRotation"))
                .forceRenderItem(nbt.getBoolean("cfg_forceRenderItem"))
                .build();
    }

    private void writeVec3dToNbt(NbtCompound nbt, String prefix, Vec3d vec) {
        nbt.putDouble(prefix + "X", vec.x);
        nbt.putDouble(prefix + "Y", vec.y);
        nbt.putDouble(prefix + "Z", vec.z);
    }

    private Vec3d readVec3dFromNbt(NbtCompound nbt, String prefix) {
        return new Vec3d(
                nbt.getDouble(prefix + "X"),
                nbt.getDouble(prefix + "Y"),
                nbt.getDouble(prefix + "Z")
        );
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
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (this.world != null) {
            this.world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PedestalScreenHandler(syncId, playerInventory, this.pos);
    }
}
