package net.orbit;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.orbit.block.entity.ModBlockEntities;
import net.orbit.screen.ModScreenHandlers;

import net.orbit.entity.ModModelLayers;
import net.orbit.block.entity.renderer.PedestalBlockEntityRenderer;
import net.orbit.screen.custom.PedestalScreen;

public class OrbitPedestalsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ModModelLayers.registerModelLayers();
		BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);
		HandledScreens.register(ModScreenHandlers.PEDESTAL_SCREEN_HANDLER, PedestalScreen::new);
	}
}