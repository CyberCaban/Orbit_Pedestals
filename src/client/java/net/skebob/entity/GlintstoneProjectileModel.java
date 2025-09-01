package net.skebob.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.util.Identifier;
import net.skebob.Skebob;

public class GlintstoneProjectileModel extends EntityModel<EntityRenderState> {
	public static final EntityModelLayer GLINTSTONE_ENTITY = new EntityModelLayer(
			Identifier.of(Skebob.MOD_ID, "glintstone_projectile"), "main");

	private final ModelPart glintstone;
	public GlintstoneProjectileModel(ModelPart root) {
        super(root);
        this.glintstone = root.getChild("glintstone_entity");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData glintstone_entity = modelPartData.addChild("glintstone_entity", ModelPartBuilder.create().uv(0, 6).cuboid(
				-2.0F, -3.0F, -3.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)
		)
		.uv(0, 0).cuboid(-1.5F, -2.5F, -3.5F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F))
		.uv(10, 6).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}

	@Override
	public void setAngles(EntityRenderState state) {
	}
}