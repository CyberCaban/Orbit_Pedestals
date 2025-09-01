package net.skebob.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.skebob.item.custom.ability.AoWAbilities;
import net.skebob.item.custom.ability.AshAbility;

import static net.skebob.item.ModComponents.*;

public class ModEvents {
    public static void registerEvents() {
        UseItemCallback.EVENT.register((playerEntity, world, hand) -> {
            ItemStack is = playerEntity.getStackInHand(hand);
            if (is.isIn(ItemTags.SWORDS) &&
                    isAowInfusable(is) &&
                    !isActivating(is)
            ) {
                startActivating(is);
                playerEntity.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 0.5f, 1.0f);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
                handleActivation(server, playerEntity);
            }
        });
    }

    private static void handleActivation(MinecraftServer server, ServerPlayerEntity player) {
        ItemStack is = player.getMainHandStack();
        if (isActivating(is)) {
            Integer currentTime = (Integer) is.getOrDefault(AOW_ACTIVATION_TIME, 0);
            String aowName = (String) is.getOrDefault(AOW_INFUSABLE, "");
            AshAbility ability = AoWAbilities.ABILITIES.get(aowName);
            int maxActivationTime = ability.getActivationTime();

            if (currentTime < maxActivationTime) {
                is.set((ComponentType<Integer>) AOW_ACTIVATION_TIME, Integer.valueOf(currentTime + 1));
            } else {
                ability.use(player, server.getOverworld(), player.getActiveHand(), player.getStackInHand(player.getActiveHand()));
                stopActivating(is);
            }
        }
    }
}
