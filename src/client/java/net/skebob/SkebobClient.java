package net.skebob;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.skebob.block.entity.ModBlockEntities;
import net.skebob.block.entity.renderer.PedestalBlockEntityRenderer;
import net.skebob.entity.ModEntities;
import net.skebob.entity.GlintstoneRenderer;
import net.skebob.entity.ModModelLayers;

public class SkebobClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ModModelLayers.registerModelLayers();
		EntityRendererRegistry.register(ModEntities.GLINTSTONE_PROJECTILE_ENTITY, GlintstoneRenderer::new);
		BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);
	}
}