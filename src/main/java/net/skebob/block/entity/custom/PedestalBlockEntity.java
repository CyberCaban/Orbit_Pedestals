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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.skebob.block.entity.ImplementedInventory;
import net.skebob.block.entity.ModBlockEntities;
import net.skebob.screen.custom.PedestalScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

import static net.skebob.block.entity.custom.PedestalRenderConfig.Builder.configToBuilder;

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
            case "crystalScale" -> builder.crystalScale(value);
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
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, inventory);
        writeRenderConfigToNbt(view);
        view.putFloat("rotation", rotation);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view, inventory);
        this.renderConfig = readRenderConfigFromNbt(view);
        this.rotation = view.getFloat("rotation", 0f);
    }

    private void writeRenderConfigToNbt(WriteView view) {
        view.putFloat("cfg_rotationSpeed", renderConfig.rotationSpeed());
        view.putFloat("cfg_baseHeight", renderConfig.baseHeight());
        view.putFloat("cfg_itemScale", renderConfig.itemScale());
        view.putFloat("cfg_levitationAmplitude", renderConfig.levitationAmplitude());
        view.putFloat("cfg_levitationSpeed", renderConfig.levitationSpeed());
        view.putFloat("cfg_radius", renderConfig.radius());
        view.putFloat("cfg_multiItemLevitationAmplitude", renderConfig.multiItemLevitationAmplitude());
        view.putFloat("cfg_crystalScale", renderConfig.crystalScale());
        view.putBoolean("cfg_crystalShowBottom", renderConfig.crystalShowBottom());

        writeVec3dToNbt(view, "cfg_offset", renderConfig.itemOffset());
        writeVec3dToNbt(view, "cfg_rotation", renderConfig.itemRotation());
        writeVec3dToNbt(view, "cfg_rotStep", renderConfig.rotationStep());
    }

    private PedestalRenderConfig readRenderConfigFromNbt(ReadView view) {
        return new PedestalRenderConfig.Builder()
                .rotationSpeed(view.getFloat("cfg_rotationSpeed", PedestalRenderConfig.DEFAULT_ROTATION_SPEED))
                .baseHeight(view.getFloat("cfg_baseHeight", PedestalRenderConfig.DEFAULT_BASE_HEIGHT))
                .singleItemScale(view.getFloat("cfg_itemScale", PedestalRenderConfig.DEFAULT_SINGLE_ITEM_SCALE))
                .levitationAmplitude(view.getFloat("cfg_levitationAmplitude", PedestalRenderConfig.DEFAULT_LEVITATION_AMPLITUDE))
                .levitationSpeed(view.getFloat("cfg_levitationSpeed", PedestalRenderConfig.DEFAULT_LEVITATION_SPEED))
                .radius(view.getFloat("cfg_radius", PedestalRenderConfig.DEFAULT_RADIUS))
                .multiItemLevitationAmplitude(view.getFloat("cfg_multiItemLevitationAmplitude", PedestalRenderConfig.DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE))
                .crystalScale(view.getFloat("cfg_crystalScale", PedestalRenderConfig.DEFAULT_CRYSTAL_SCALE))
                .crystalShowBottom(view.getBoolean("cfg_crystalShowBottom", PedestalRenderConfig.DEFAULT_CRYSTAL_SHOW_BOTTOM))
                .singleItemOffset(readVec3dFromNbt(view, "cfg_offset", PedestalRenderConfig.DEFAULT_SINGLE_ITEM_OFFSET))
                .itemRotation(readVec3dFromNbt(view, "cfg_rotation", PedestalRenderConfig.DEFAULT_SINGLE_ITEM_ROTATION))
                .rotationStep(readVec3dFromNbt(view, "cfg_rotStep", PedestalRenderConfig.DEFAULT_ROTATION_STEP))
                .build();
    }

    private void writeVec3dToNbt(WriteView view, String prefix, Vec3d vec) {
        view.putDouble(prefix + "X", vec.x);
        view.putDouble(prefix + "Y", vec.y);
        view.putDouble(prefix + "Z", vec.z);
    }

    private Vec3d readVec3dFromNbt(ReadView view, String prefix, Vec3d defaultValue) {
        return new Vec3d(
                view.getDouble(prefix + "X", defaultValue.x),
                view.getDouble(prefix + "Y", defaultValue.y),
                view.getDouble(prefix + "Z", defaultValue.z)
        );
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
        return Text.literal("");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PedestalScreenHandler(syncId, playerInventory, this.pos);
    }
}
