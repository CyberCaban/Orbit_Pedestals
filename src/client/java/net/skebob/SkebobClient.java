package net.skebob;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.skebob.block.entity.ModBlockEntities;
import net.skebob.block.entity.renderer.PedestalBlockEntityRenderer;
import net.skebob.entity.ModEntities;
import net.skebob.entity.GlintstoneRenderer;
import net.skebob.entity.ModModelLayers;
import net.skebob.screen.ModScreenHandlers;
import net.skebob.screen.custom.PedestalScreen;

public class SkebobClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ModModelLayers.registerModelLayers();
		EntityRendererRegistry.register(ModEntities.GLINTSTONE_PROJECTILE_ENTITY, GlintstoneRenderer::new);
		BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);
		HandledScreens.register(ModScreenHandlers.PEDESTAL_SCREEN_HANDLER, PedestalScreen::new);
	}
}