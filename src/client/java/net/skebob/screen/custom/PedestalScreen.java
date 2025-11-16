package net.skebob.screen.custom;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.skebob.Skebob;
import net.skebob.network.UpdatePedestalFloatPayload;
import net.skebob.network.UpdatePedestalVec3dPayload;
import net.skebob.screen.widget.NumericInputWidget;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PedestalScreen extends HandledScreen<PedestalScreenHandler> {
    public static final Identifier GUI_TEXTURE =
            Identifier.of(Skebob.MOD_ID, "textures/gui/pedestal/pedestal_gui.png");

    private Vec3d currentOffset;
    private Vec3d currentRotation;

    public PedestalScreen(PedestalScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.currentOffset = handler.getRenderConfig().itemOffset();
        this.currentRotation = handler.getRenderConfig().itemRotation();
    }

    @Override
    protected void init() {
        super.init();

        int spacingY = 20;
        int spacingX = 10;
        int width = 150;
        int height = 18;
        int startY = (this.height - backgroundHeight) / 2 - height - spacingY;
        int startX = (this.width - backgroundWidth) / 2 - width / 2;

        int currentY = startY;

        // 1st column
        addDrawableChild(new NumericInputWidget(
                startX, currentY, width, height,
                Text.literal("Rotation Speed"),
                this.handler.getRenderConfig().rotationSpeed(),
                0f, 1f, 0.01f,
                value -> sendConfigUpdate("rotationSpeed", value)
        ));
        currentY += spacingY;

        addDrawableChild(new NumericInputWidget(
                startX, currentY, width, height,
                Text.literal("Base Height"),
                this.handler.getRenderConfig().baseHeight(),
                -5f, 5f, 0.1f,
                value -> sendConfigUpdate("baseHeight", value)
        ));
        currentY += spacingY;

        addDrawableChild(new NumericInputWidget(
                startX, currentY, width, height,
                Text.literal("Item Scale"),
                this.handler.getRenderConfig().itemScale(),
                0.1f, 3f, 0.1f,
                value -> sendConfigUpdate("singleItemScale", value)
        ));
        currentY += spacingY;

        // Offset Vec3d - три отдельных поля
        currentY = addVec3dFields(
                startX, currentY, width, height, spacingY,
                "Offset",
                currentOffset,
                -5f, 5f, 0.1f,
                "itemOffset",
                () -> currentOffset,
                newVec -> {
                    currentOffset = newVec;
                    sendConfigUpdate("itemOffset", currentOffset);
                }
        );

        // 2nd column
        currentY = startY;
        int column2X = startX + spacingX + width;

        addDrawableChild(new NumericInputWidget(
                column2X, currentY, width, height,
                Text.literal("Levitation Speed"),
                handler.getRenderConfig().levitationSpeed(),
                0f, 10f, 0.5f,
                value -> sendConfigUpdate("levitationSpeed", value)
        ));
        currentY += spacingY;

        addDrawableChild(new NumericInputWidget(
                column2X, currentY, width, height,
                Text.literal("Levitation Amplitude"),
                handler.getRenderConfig().levitationAmplitude(),
                0f, 10f, 0.1f,
                value -> sendConfigUpdate("levitationAmplitude", value)
        ));
        currentY += spacingY;

        addDrawableChild(new NumericInputWidget(
                column2X, currentY, width, height,
                Text.literal("Radius"),
                handler.getRenderConfig().radius(),
                0f, 5f, 0.1f,
                value -> sendConfigUpdate("radius", value)
        ));
        currentY += spacingY;

        // Rotation Vec3d - три отдельных поля
        currentY = addVec3dFields(
                column2X, currentY, width, height, spacingY,
                "Rotation",
                currentRotation,
                -180f, 180f, 5f,
                "itemRotation",
                () -> currentRotation,
                newVec -> {
                    currentRotation = newVec;
                    sendConfigUpdate("itemRotation", newVec);
                }
        );
    }

    /**
     * Добавляет три поля для редактирования Vec3d (X, Y, Z)
     * @return новую позицию Y после добавления всех полей
     */
    private int addVec3dFields(int x, int y, int width, int height, int spacing,
                               String labelPrefix, Vec3d initialValue,
                               float minValue, float maxValue, float step,
                               String fieldName,
                               Supplier<Vec3d> currentValueGetter,
                               Consumer<Vec3d> onUpdate) {
        // Заголовок группы (опционально)
        addDrawableChild(new TextWidget(
                x, y, width, 10,
                Text.literal(labelPrefix + ":"),
                this.textRenderer
        ).alignLeft().setTextColor(0xFFFF55));
        y += 12;

        // X компонент
        addDrawableChild(new NumericInputWidget(
                x, y, width, height,
                Text.literal("  X"),
                (float) initialValue.x,
                minValue, maxValue, step,
                newX -> {
                    Vec3d current = currentValueGetter.get();
                    onUpdate.accept(new Vec3d(newX, current.y, current.z));
                }
        ));
        y += spacing;

        // Y компонент
        addDrawableChild(new NumericInputWidget(
                x, y, width, height,
                Text.literal("  Y"),
                (float) initialValue.y,
                minValue, maxValue, step,
                newY -> {
                    Vec3d current = currentValueGetter.get();
                    onUpdate.accept(new Vec3d(current.x, newY, current.z));
                }
        ));
        y += spacing;

        // Z компонент
        addDrawableChild(new NumericInputWidget(
                x, y, width, height,
                Text.literal("  Z"),
                (float) initialValue.z,
                minValue, maxValue, step,
                newZ -> {
                    Vec3d current = currentValueGetter.get();
                    onUpdate.accept(new Vec3d(current.x, current.y, newZ));
                }
        ));
        y += spacing;

        return y;
    }

    private void sendConfigUpdate(String fieldName, float value) {
        Skebob.LOGGER.info("Sending config update to {}: {}", fieldName, value);
        ClientPlayNetworking.send(new UpdatePedestalFloatPayload(
                handler.getBlockPos(), fieldName, value
        ));
    }

    private void sendConfigUpdate(String fieldName, Vec3d value) {
        Skebob.LOGGER.info("Sending config update to {}: {}", fieldName, value);
        ClientPlayNetworking.send(new UpdatePedestalVec3dPayload(
                handler.getBlockPos(), fieldName, value
        ));
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        // context.drawTexture(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 1, 1, backgroundWidth, backgroundHeight, 256, 256);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);
    }
}

