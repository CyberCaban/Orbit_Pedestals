package net.skebob.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.skebob.Skebob;

public class GlintstoneRenderer extends EntityRenderer<GlintstoneProjectileEntity, EntityRenderState> {
    protected GlintstoneProjectileModel model;
    public GlintstoneRenderer(EntityRendererFactory.Context context) {
        super(context);
        EntityModelLayer layer = ModModelLayers.GLINTSTONE_PROJECTILE;
        ModelPart part = context.getPart(layer);
        this.model = new GlintstoneProjectileModel(part);
    }

    @Override
    public void render(EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(
                vertexConsumers, this.model.getLayer(Identifier.of(Skebob.MOD_ID, "textures/entity/glintstone_entity/glintstone_entity.png")),
                false, false
        );
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}
