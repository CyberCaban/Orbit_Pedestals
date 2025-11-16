package net.orbit.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.orbit.OrbitPedestals;

public class GlintstoneRenderer extends EntityRenderer<GlintstoneProjectileEntity, GlintstoneRenderState> {
    protected GlintstoneProjectileModel model;
    public GlintstoneRenderer(EntityRendererFactory.Context context) {
        super(context);
        EntityModelLayer layer = ModModelLayers.GLINTSTONE_PROJECTILE;
        ModelPart part = context.getPart(layer);
        this.model = new GlintstoneProjectileModel(part);
    }

    @Override
    public void render(GlintstoneRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        float tickDelta = state.tickDelta;
        float yaw = state.yaw;
        float pitch = state.pitch;
        float lastYaw = state.lastYaw;
        float lastPitch = state.lastPitch;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, lastYaw, yaw) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, lastPitch, pitch)));

        matrices.translate(0.0, -1.5, 0.0);
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(
                vertexConsumers, this.model.getLayer(Identifier.of(OrbitPedestals.MOD_ID, "textures/entity/glintstone_entity/glintstone_entity.png")),
                false, false
        );
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }


    @Override
    public GlintstoneRenderState createRenderState() {
        return new GlintstoneRenderState();
    }

    @Override
    public void updateRenderState(GlintstoneProjectileEntity entity, GlintstoneRenderState state, float tickProgress) {
        super.updateRenderState(entity, state, tickProgress);
        state.tickDelta = tickProgress;
        state.yaw = entity.getYaw();
        state.pitch = entity.getPitch();
        state.lastYaw = entity.lastYaw;
        state.lastPitch = entity.lastPitch;
    }
}
