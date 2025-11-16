package net.orbit_pedestals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.orbit_pedestals.block.entity.ModBlockEntities;
import net.orbit_pedestals.entity.ModEntities;
import net.orbit_pedestals.screen.ModScreenHandlers;

import net.orbit_pedestals.entity.GlintstoneRenderer;
import net.orbit_pedestals.entity.ModModelLayers;
import net.orbit_pedestals.block.entity.renderer.PedestalBlockEntityRenderer;
import net.orbit_pedestals.screen.custom.PedestalScreen;

public class OrbitPedestalsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ModModelLayers.registerModelLayers();
		EntityRendererRegistry.register(ModEntities.GLINTSTONE_PROJECTILE_ENTITY, GlintstoneRenderer::new);
		BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);
		HandledScreens.register(ModScreenHandlers.PEDESTAL_SCREEN_HANDLER, PedestalScreen::new);
	}
}