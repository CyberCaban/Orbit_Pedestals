package net.orbit.block.entity.custom;

import net.minecraft.util.math.Vec3d;

/**
 * Record class for configuring item rendering on pedestal
 */
public record PedestalRenderConfig(
        float rotationSpeed,
        float baseHeight,

        Vec3d itemOffset,
        Vec3d itemRotation,
        Vec3d rotationStep,
        float itemScale,
        float levitationAmplitude,
        float levitationSpeed,

        float radius,
        float multiItemLevitationAmplitude,

        boolean forceRenderItem,
        boolean multiItemFancyRotation
) {

    public static final Vec3d DEFAULT_ROTATION_STEP = new Vec3d(100, 0, 50);
    public static final float DEFAULT_ROTATION_SPEED = 0.01f;
    public static final float DEFAULT_BASE_HEIGHT = 1.15f;
    public static final Vec3d DEFAULT_SINGLE_ITEM_OFFSET = new Vec3d(0.5, 1.15, 0.5);
    public static final Vec3d DEFAULT_SINGLE_ITEM_ROTATION = new Vec3d(0, 0, 0);
    public static final float DEFAULT_SINGLE_ITEM_SCALE = 0.5f;
    public static final float DEFAULT_LEVITATION_AMPLITUDE = 0.1f;
    public static final float DEFAULT_LEVITATION_SPEED = 2.0f;
    public static final float DEFAULT_RADIUS = 1.2f;
    public static final float DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE = 0.10f;
    public static final boolean DEFAULT_FORCE_RENDER_ITEM = false;
    public static final boolean DEFAULT_MULTI_ITEM_FANCY_ROTATION = false;

    public static class Builder {
        private Vec3d rotationStep = DEFAULT_ROTATION_STEP;
        private float rotationSpeed = DEFAULT_ROTATION_SPEED;
        private float baseHeight = DEFAULT_BASE_HEIGHT;
        private Vec3d singleItemOffset = DEFAULT_SINGLE_ITEM_OFFSET;
        private Vec3d itemRotation = DEFAULT_SINGLE_ITEM_ROTATION;
        private float singleItemScale = DEFAULT_SINGLE_ITEM_SCALE;
        private float levitationAmplitude = DEFAULT_LEVITATION_AMPLITUDE;
        private float levitationSpeed = DEFAULT_LEVITATION_SPEED;
        private float radius = DEFAULT_RADIUS;
        private float multiItemLevitationAmplitude = DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE;
        private boolean forceRenderItem = DEFAULT_FORCE_RENDER_ITEM;
        private boolean multiItemFancyRotation = DEFAULT_MULTI_ITEM_FANCY_ROTATION;

        public Builder multiItemFancyRotation(boolean multiItemFancyRotation) {
            this.multiItemFancyRotation = multiItemFancyRotation;
            return this;
        }

        public Builder forceRenderItem(boolean forceRenderItem) {
            this.forceRenderItem = forceRenderItem;
            return this;
        }

        public Builder rotationStep(Vec3d rotationStep) {
            this.rotationStep = rotationStep;
            return this;
        }

        public Builder rotationSpeed(float rotationSpeed) {
            this.rotationSpeed = rotationSpeed;
            return this;
        }

        public Builder baseHeight(float baseHeight) {
            this.baseHeight = baseHeight;
            return this;
        }

        public Builder singleItemOffset(Vec3d singleItemOffset) {
            this.singleItemOffset = singleItemOffset;
            return this;
        }

        public Builder itemRotation(Vec3d singleItemRotation) {
            this.itemRotation = singleItemRotation;
            return this;
        }

        public Builder singleItemScale(float singleItemScale) {
            this.singleItemScale = singleItemScale;
            return this;
        }

        public Builder levitationAmplitude(float levitationAmplitude) {
            this.levitationAmplitude = levitationAmplitude;
            return this;
        }

        public Builder levitationSpeed(float levitationSpeed) {
            this.levitationSpeed = levitationSpeed;
            return this;
        }

        public Builder radius(float radius) {
            this.radius = radius;
            return this;
        }

        public Builder multiItemLevitationAmplitude(float multiItemLevitationAmplitude) {
            this.multiItemLevitationAmplitude = multiItemLevitationAmplitude;
            return this;
        }

        public PedestalRenderConfig build() {
            return new PedestalRenderConfig(
                    rotationSpeed,
                    baseHeight,
                    singleItemOffset,
                    itemRotation,
                    rotationStep,
                    singleItemScale,
                    levitationAmplitude,
                    levitationSpeed,
                    radius,
                    multiItemLevitationAmplitude,
                    forceRenderItem,
                    multiItemFancyRotation
                );
        }

        public static PedestalRenderConfig.Builder configToBuilder(PedestalRenderConfig config) {
            return new Builder()
                    .multiItemFancyRotation(config.multiItemFancyRotation())
                    .forceRenderItem(config.forceRenderItem())
                    .rotationSpeed(config.rotationSpeed())
                    .baseHeight(config.baseHeight())
                    .singleItemOffset(config.itemOffset())
                    .itemRotation(config.itemRotation())
                    .rotationStep(config.rotationStep())
                    .singleItemScale(config.itemScale())
                    .levitationAmplitude(config.levitationAmplitude())
                    .levitationSpeed(config.levitationSpeed())
                    .radius(config.radius())
                    .multiItemLevitationAmplitude(config.multiItemLevitationAmplitude());
        }
    }

    public static PedestalRenderConfig defaultMultiItem() {
        return new PedestalRenderConfig(
                DEFAULT_ROTATION_SPEED,
                DEFAULT_BASE_HEIGHT,
                DEFAULT_SINGLE_ITEM_OFFSET,
                DEFAULT_SINGLE_ITEM_ROTATION,
                DEFAULT_ROTATION_STEP,
                DEFAULT_SINGLE_ITEM_SCALE,
                DEFAULT_LEVITATION_AMPLITUDE,
                DEFAULT_LEVITATION_SPEED,
                DEFAULT_RADIUS,
                DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE,
                DEFAULT_FORCE_RENDER_ITEM,
                DEFAULT_MULTI_ITEM_FANCY_ROTATION
        );
    }

    public static PedestalRenderConfig defaultSingleItem() {
        return new PedestalRenderConfig(
                DEFAULT_ROTATION_SPEED,
                DEFAULT_BASE_HEIGHT,
                DEFAULT_SINGLE_ITEM_OFFSET,
                DEFAULT_SINGLE_ITEM_ROTATION,
                DEFAULT_ROTATION_STEP,
                DEFAULT_SINGLE_ITEM_SCALE,
                DEFAULT_LEVITATION_AMPLITUDE,
                DEFAULT_LEVITATION_SPEED,
                DEFAULT_RADIUS,
                DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE,
                DEFAULT_FORCE_RENDER_ITEM,
                DEFAULT_MULTI_ITEM_FANCY_ROTATION
        );
    }

    public static PedestalRenderConfig stationarySingleItem() {
        return new PedestalRenderConfig(
                0,
                DEFAULT_BASE_HEIGHT,
                new Vec3d(0, 0.1, 0),
                DEFAULT_SINGLE_ITEM_ROTATION,
                DEFAULT_ROTATION_STEP,
                DEFAULT_SINGLE_ITEM_SCALE,
                DEFAULT_LEVITATION_AMPLITUDE,
                DEFAULT_LEVITATION_SPEED,
                DEFAULT_RADIUS,
                DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE,
                DEFAULT_FORCE_RENDER_ITEM,
                DEFAULT_MULTI_ITEM_FANCY_ROTATION
        );
    }
}

