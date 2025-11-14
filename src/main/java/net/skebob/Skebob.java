package net.skebob;

import net.fabricmc.api.ModInitializer;
import net.skebob.block.ModBlocks;
import net.skebob.block.entity.ModBlockEntities;
import net.skebob.entity.ModEntities;
import net.skebob.event.ModEvents;
import net.skebob.item.ModComponents;
import net.skebob.item.ModItemGroups;
import net.skebob.item.ModItems;
import net.skebob.item.custom.ability.AoWAbilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Skebob implements ModInitializer {
	public static final String MOD_ID = "skebob";

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
		AoWAbilities.registerAbilities();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModComponents.registerModComponents();
		ModEvents.registerEvents();
		ModEntities.registerEntities();
		ModBlockEntities.registerBlockEntities();
	}
}
