package net.orbit;

import net.fabricmc.api.ModInitializer;
import net.orbit.block.ModBlocks;
import net.orbit.block.entity.ModBlockEntities;
import net.orbit.entity.ModEntities;
import net.orbit.event.ModEvents;
import net.orbit.item.ModComponents;
import net.orbit.item.ModItemGroups;
import net.orbit.item.ModItems;
import net.orbit.network.ModPayloads;
import net.orbit.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OrbitPedestals implements ModInitializer {
	public static final String MOD_ID = "orbit_pedestals";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");
//		AoWAbilities.registerAbilities();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModComponents.registerModComponents();
		ModEvents.registerEvents();
		ModEntities.registerEntities();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModPayloads.registerPayloads();
	}
}
