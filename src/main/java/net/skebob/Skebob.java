package net.skebob;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.skebob.block.ModBlocks;
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

		UseItemCallback.EVENT.register((playerEntity, world, hand) -> {
			ItemStack is = playerEntity.getStackInHand(hand);
			if (is.isIn(ItemTags.SWORDS) &&
          is.contains(ModComponents.AOW_INFUSABLE) &&
          !is.contains(ModComponents.AOW_IS_ACTIVATING) &&
          !is.contains(ModComponents.AOW_ACTIVATION_TIME)
         ) {
          is.set((ComponentType<Boolean>) ModComponents.AOW_IS_ACTIVATING, true);
          is.set((ComponentType<Integer>) ModComponents.AOW_ACTIVATION_TIME, 0);
          playerEntity.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 0.5f, 1.0f);
				// String ability = (String) is.get(ModComponents.AOW_INFUSABLE);
				// if (AoWAbilities.ABILITIES.containsKey(ability)) {
				// 	AoWAbilities.ABILITIES.get(ability).use(playerEntity, world, hand, playerEntity.getStackInHand(hand));
				// }
				return ActionResult.PASS;
			} else {
				return ActionResult.PASS;
			}
		});

    ServerTickEvents.END_SERVER_TICK.register(server -> {
      for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
        handleActivation(server, playerEntity);
      }
    });

	}
  private static void handleActivation(MinecraftServer server, ServerPlayerEntity player) {
    ItemStack is = player.getMainHandStack();
    if (is.contains(ModComponents.AOW_ACTIVATION_TIME) && 
      is.contains(ModComponents.AOW_IS_ACTIVATING)) {
      Skebob.LOGGER.info("activating");
      Integer currentTime = (Integer) is.getOrDefault(ModComponents.AOW_ACTIVATION_TIME, 0);
      String aowName = (String) is.getOrDefault(ModComponents.AOW_INFUSABLE, "");
      int maxActivationTime = AoWAbilities.ABILITIES.get(aowName).getActivationTime();

      if (currentTime < maxActivationTime) {
        is.set((ComponentType<Integer>) ModComponents.AOW_ACTIVATION_TIME, Integer.valueOf(currentTime + 1));
      } else {
        AoWAbilities.ABILITIES.get(aowName).use((PlayerEntity) player, server.getOverworld(), player.getActiveHand(), player.getStackInHand(player.getActiveHand()));
        is.remove(ModComponents.AOW_ACTIVATION_TIME);
        is.remove(ModComponents.AOW_IS_ACTIVATING);
      }
    }
  }
}
