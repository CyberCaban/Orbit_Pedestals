package net.skebob.block.entity.custom;

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
        boolean singleItemLevitation,
        float levitationAmplitude,
        float levitationSpeed,

        float radius,
        boolean multiItemLevitation,
        float multiItemLevitationAmplitude,

        boolean crystalShowBottom,
        float crystalScale
) {

    public static final Vec3d DEFAULT_ROTATION_STEP = new Vec3d(100, 0, 50);
    public static final float DEFAULT_ROTATION_SPEED = 0.01f;
    public static final float DEFAULT_BASE_HEIGHT = 1.15f;
    public static final Vec3d DEFAULT_SINGLE_ITEM_OFFSET = new Vec3d(0.5, 1.15, 0.5);
    public static final Vec3d DEFAULT_SINGLE_ITEM_ROTATION = new Vec3d(0, 0, 45);
    public static final float DEFAULT_SINGLE_ITEM_SCALE = 0.5f;
    public static final boolean DEFAULT_SINGLE_ITEM_LEVITATION = true;
    public static final float DEFAULT_LEVITATION_AMPLITUDE = 0.1f;
    public static final float DEFAULT_LEVITATION_SPEED = 2.0f;
    public static final float DEFAULT_RADIUS = 1.2f;
    public static final boolean DEFAULT_MULTI_ITEM_LEVITATION = true;
    public static final float DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE = 0.33f;
    public static final boolean DEFAULT_CRYSTAL_SHOW_BOTTOM = false;
    public static final float DEFAULT_CRYSTAL_SCALE = 0.8f;

    public static class Builder {
        private Vec3d rotationStep = DEFAULT_ROTATION_STEP;
        private float rotationSpeed = DEFAULT_ROTATION_SPEED;
        private float baseHeight = DEFAULT_BASE_HEIGHT;
        private Vec3d singleItemOffset = DEFAULT_SINGLE_ITEM_OFFSET;
        private Vec3d singleItemRotation = DEFAULT_SINGLE_ITEM_ROTATION;
        private float singleItemScale = DEFAULT_SINGLE_ITEM_SCALE;
        private boolean singleItemLevitation = DEFAULT_SINGLE_ITEM_LEVITATION;
        private float levitationAmplitude = DEFAULT_LEVITATION_AMPLITUDE;
        private float levitationSpeed = DEFAULT_LEVITATION_SPEED;
        private float radius = DEFAULT_RADIUS;
        private boolean multiItemLevitation = DEFAULT_MULTI_ITEM_LEVITATION;
        private float multiItemLevitationAmplitude = DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE;
        private boolean crystalShowBottom = DEFAULT_CRYSTAL_SHOW_BOTTOM;
        private float crystalScale = DEFAULT_CRYSTAL_SCALE;

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

        public Builder singleItemRotation(Vec3d singleItemRotation) {
            this.singleItemRotation = singleItemRotation;
            return this;
        }

        public Builder singleItemScale(float singleItemScale) {
            this.singleItemScale = singleItemScale;
            return this;
        }

        public Builder singleItemLevitation(boolean singleItemLevitation) {
            this.singleItemLevitation = singleItemLevitation;
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

        public Builder multiItemLevitation(boolean multiItemLevitation) {
            this.multiItemLevitation = multiItemLevitation;
            return this;
        }

        public Builder multiItemLevitationAmplitude(float multiItemLevitationAmplitude) {
            this.multiItemLevitationAmplitude = multiItemLevitationAmplitude;
            return this;
        }

        public Builder crystalShowBottom(boolean crystalShowBottom) {
            this.crystalShowBottom = crystalShowBottom;
            return this;
        }

        public Builder crystalScale(float crystalScale) {
            this.crystalScale = crystalScale;
            return this;
        }

        public PedestalRenderConfig build() {
            return new PedestalRenderConfig(
                    rotationSpeed,
                    baseHeight,
                    singleItemOffset,
                    singleItemRotation,
                    rotationStep,
                    singleItemScale,
                    singleItemLevitation,
                    levitationAmplitude,
                    levitationSpeed,
                    radius,
                    multiItemLevitation,
                    multiItemLevitationAmplitude,
                    crystalShowBottom,
                    crystalScale
            );
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
                DEFAULT_SINGLE_ITEM_LEVITATION,
                DEFAULT_LEVITATION_AMPLITUDE,
                DEFAULT_LEVITATION_SPEED,
                DEFAULT_RADIUS,
                DEFAULT_MULTI_ITEM_LEVITATION,
                DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE,
                DEFAULT_CRYSTAL_SHOW_BOTTOM,
                DEFAULT_CRYSTAL_SCALE
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
                DEFAULT_SINGLE_ITEM_LEVITATION,
                DEFAULT_LEVITATION_AMPLITUDE,
                DEFAULT_LEVITATION_SPEED,
                DEFAULT_RADIUS,
                DEFAULT_MULTI_ITEM_LEVITATION,
                DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE,
                DEFAULT_CRYSTAL_SHOW_BOTTOM,
                DEFAULT_CRYSTAL_SCALE
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
                false,
                DEFAULT_LEVITATION_AMPLITUDE,
                DEFAULT_LEVITATION_SPEED,
                DEFAULT_RADIUS,
                DEFAULT_MULTI_ITEM_LEVITATION,
                DEFAULT_MULTI_ITEM_LEVITATION_AMPLITUDE,
                DEFAULT_CRYSTAL_SHOW_BOTTOM,
                DEFAULT_CRYSTAL_SCALE
        );
    }
}

