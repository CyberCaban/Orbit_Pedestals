package net.skebob;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.skebob.entity.GlintstoneProjectileEntity;
import net.skebob.entity.ModEntities;

public class SkebobClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		EntityRendererRegistry.register(ModEntities.GLINTSTONE_PROJECTILE_ENTITY, FlyingItemEntityRenderer::new);
	}
}