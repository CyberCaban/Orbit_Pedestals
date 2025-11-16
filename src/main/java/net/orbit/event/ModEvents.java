package net.orbit.event;

import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.orbit.item.custom.ability.AoWAbilities;
import net.orbit.item.custom.ability.AshAbility;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static net.orbit.item.ModComponents.*;

public class ModEvents {
    private static final Map<UUID, Boolean> playerHoldingMap = new HashMap<>();

    public static void registerEvents() {
//        UseItemCallback.EVENT.register((playerEntity, world, hand) -> {
//            ItemStack is = playerEntity.getStackInHand(hand);
//            if (is.isIn(ItemTags.SWORDS) &&
//                    isAowInfusable(is) &&
//                    !isActivating(is)
//            ) {
//                startActivating(is);
//                playerEntity.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 0.5f, 1.0f);
//                playerHoldingMap.put(playerEntity.getUuid(), true);
//                return ActionResult.SUCCESS;
//            }
//            return ActionResult.PASS;
//        });
//
//        ServerTickEvents.END_SERVER_TICK.register(server -> {
//            for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
//                handleActivation(server, playerEntity);
//                checkIfStillHolding(playerEntity);
//            }
//        });


    }

    private static void checkIfStillHolding(ServerPlayerEntity player) {
        UUID playerId = player.getUuid();
        if (playerHoldingMap.containsKey(playerId)) {
            ItemStack mainHand = player.getMainHandStack();
            // Проверяем, все ли еще активируется предмет и в главной руке
            if (!isActivating(mainHand) || !mainHand.equals(player.getStackInHand(player.getActiveHand()))) {
                playerHoldingMap.put(playerId, false);
            }
        }
    }

    private static void handleActivation(MinecraftServer server, ServerPlayerEntity player) {
        ItemStack is = player.getMainHandStack();
        if (isActivating(is)) {
            UUID playerId = player.getUuid();
            Integer currentTime = (Integer) is.getOrDefault(AOW_ACTIVATION_TIME, 0);
            String aowName = (String) is.getOrDefault(AOW_INFUSABLE, "");
            AshAbility ability = AoWAbilities.ABILITIES.get(aowName);
            int maxActivationTime = ability.getActivationTime();

            Boolean isHolding = playerHoldingMap.getOrDefault(playerId, false);

            if (isHolding && currentTime < maxActivationTime) {
                is.set((ComponentType<Integer>) AOW_ACTIVATION_TIME, currentTime + 1);
            } else if (!isHolding && currentTime > 0) {
                is.set((ComponentType<Integer>) AOW_ACTIVATION_TIME, Math.max(0, currentTime - 2));

                if (currentTime - 2 <= 0) {
                    stopActivating(is);
                    playerHoldingMap.remove(playerId);
                }
            } else if (currentTime >= maxActivationTime) {
                ability.use(player, server.getOverworld(), player.getActiveHand(), player.getStackInHand(player.getActiveHand()));
                stopActivating(is);
                playerHoldingMap.remove(playerId);
            }
        } else {
            playerHoldingMap.remove(player.getUuid());
        }
    }

    public static void onPlayerDisconnect(ServerPlayerEntity player) {
        playerHoldingMap.remove(player.getUuid());
    }
}