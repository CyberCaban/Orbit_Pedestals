package net.orbit.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.orbit.block.entity.custom.PedestalBlockEntity;

public class ModPayloads {
    public static void registerPayloads() {
        ServerPlayNetworking.registerGlobalReceiver(UpdatePedestalFloatPayload.ID,
                (server, player, handler, buf, responseSender) -> {
                    UpdatePedestalFloatPayload payload = new UpdatePedestalFloatPayload(buf);
                    server.execute(() -> {
                        BlockEntity be = player.getWorld().getBlockEntity(payload.pos());
                        if (be instanceof PedestalBlockEntity pedestal) {
                            pedestal.updateConfigField(payload.fieldName(), payload.value());
                        }
                    });
                }
        );

        ServerPlayNetworking.registerGlobalReceiver(
                UpdatePedestalVec3dPayload.ID,
                (server, player, handler, buf, responseSender) -> {
                    UpdatePedestalVec3dPayload payload = new UpdatePedestalVec3dPayload(buf);
                    server.execute(() -> {
                        BlockEntity be = player.getWorld().getBlockEntity(payload.pos());

                        if (be instanceof PedestalBlockEntity pedestal) {
                            pedestal.updateConfigField(payload.fieldName(), payload.getVec3d());
                        }
                    });
                }
        );

        ServerPlayNetworking.registerGlobalReceiver(
                UpdatePedestalBooleanPayload.ID,
                (server, player, handler, buf, responseSender) -> {
                    UpdatePedestalBooleanPayload payload = new UpdatePedestalBooleanPayload(buf);
                    server.execute(() -> {
                        BlockEntity be = player.getWorld().getBlockEntity(payload.pos());

                        if (be instanceof PedestalBlockEntity pedestal) {
                            pedestal.updateConfigField(payload.fieldName(), payload.value());
                        }
                    });
                }
        );
    }
}
