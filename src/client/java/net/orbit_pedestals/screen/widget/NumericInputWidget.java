package net.orbit_pedestals.screen.widget;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class NumericInputWidget extends ClickableWidget {
    private final TextWidget labelWidget;
    private final TextFieldWidget textField;
    private final ButtonWidget incrementButton;
    private final ButtonWidget decrementButton;
    private float value;
    private final float minValue;
    private final float maxValue;
    private final float step;
    private final Consumer<Float> onChange;

    public NumericInputWidget(int x, int y, int width, int height,
                              Text label, float initialValue,
                              float minValue, float maxValue, float step,
                              Consumer<Float> onChange) {
        super(x, y, width, height, Text.empty());
        this.value = initialValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.step = step;
        this.onChange = onChange;

        int labelWidth = 70;
        int buttonWidth = 18;
        int fieldWidth = width - labelWidth - buttonWidth * 2 - 4;

        // Label
        this.labelWidget = new TextWidget(
                x, y, labelWidth, height,
                label,
                MinecraftClient.getInstance().textRenderer
        );
        labelWidget.alignLeft();
        labelWidget.setTextColor(0xFFFFFF);

        // Number input
        this.textField = new TextFieldWidget(
                MinecraftClient.getInstance().textRenderer,
                x + labelWidth + 2, y, fieldWidth, height,
                Text.empty()
        );
        textField.setMaxLength(10);
        textField.setText(String.format("%.2f", value));
        textField.setChangedListener(this::onTextChanged);
        textField.setEditable(true);

        // +- buttons
        this.decrementButton = ButtonWidget.builder(
                        Text.literal("-"),
                        button -> adjustValue(-step)
                ).dimensions(x + labelWidth + fieldWidth + 2, y, buttonWidth, height)
                .build();

        this.incrementButton = ButtonWidget.builder(
                        Text.literal("+"),
                        button -> adjustValue(step)
                ).dimensions(x + labelWidth + fieldWidth + buttonWidth + 4, y, buttonWidth, height)
                .build();
    }

    private void adjustValue(float delta) {
        this.value = Math.max(minValue, Math.min(maxValue, value + delta));
        textField.setText(String.format("%.2f", value));
        onChange.accept(value);
    }

    private void onTextChanged(String text) {
        try {
            float newValue = Float.parseFloat(text.replace(',', '.'));
            if (newValue >= minValue && newValue <= maxValue) {
                this.value = newValue;
                onChange.accept(value);
            }
        } catch (NumberFormatException ignored) {
        }
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        labelWidget.render(context, mouseX, mouseY, deltaTicks);
        textField.render(context, mouseX, mouseY, deltaTicks);
        decrementButton.render(context, mouseX, mouseY, deltaTicks);
        incrementButton.render(context, mouseX, mouseY, deltaTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean textFieldClicked = textField.mouseClicked(mouseX, mouseY, button);
        boolean decrementClicked = decrementButton.mouseClicked(mouseX, mouseY, button);
        boolean incrementClicked = incrementButton.mouseClicked(mouseX, mouseY, button);

        if (textFieldClicked) {
            textField.setFocused(true);
        }

        return textFieldClicked || decrementClicked || incrementClicked;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return textField.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return textField.charTyped(chr, modifiers);
    }

    @Override
    public void setFocused(boolean focused) {
        super.setFocused(focused);
        textField.setFocused(focused);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        builder.put(NarrationPart.TITLE, labelWidget.getMessage());
    }
}

