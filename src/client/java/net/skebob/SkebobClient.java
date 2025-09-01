package net.skebob;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.skebob.entity.ModEntities;
import net.skebob.entity.GlintstoneRenderer;
import net.skebob.entity.ModModelLayers;

public class SkebobClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ModModelLayers.registerModelLayers();
		EntityRendererRegistry.register(ModEntities.GLINTSTONE_PROJECTILE_ENTITY, GlintstoneRenderer::new);
	}
}