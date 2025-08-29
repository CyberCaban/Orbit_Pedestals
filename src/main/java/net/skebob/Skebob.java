package net.skebob;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.skebob.block.ModBlocks;
import net.skebob.item.ModItemGroups;
import net.skebob.item.ModItems;
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
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			BlockPos pos = hitResult.getBlockPos();
			BlockState state = world.getBlockState(pos);
			ItemStack is = player.getStackInHand(hand);
			if (is.isIn(ItemTags.HOES) && state.isIn(BlockTags.SAND)) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						BlockPos newPos = pos.add(i, 0, j);
						if (!world.getBlockState(newPos).isIn(BlockTags.SAND) ||
								(i + j) % 2 == 0) continue;
						world.setBlockState(
								newPos,
								Blocks.AIR.getDefaultState()
						);
					}
				}
				is.damage(1, player);
			}
			return ActionResult.PASS;
		});
	}
}