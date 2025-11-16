package net.orbit.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.orbit.block.entity.custom.PedestalBlockEntity;

public class ModPayloads {
    public static void registerPayloads() {
        PayloadTypeRegistry.playC2S().register(UpdatePedestalFloatPayload.ID, UpdatePedestalFloatPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(UpdatePedestalVec3dPayload.ID, UpdatePedestalVec3dPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(UpdatePedestalFloatPayload.ID,
                (updatePedestalFloatPayload, context) ->
                        context.server().execute(() -> {
                                BlockEntity be = context.player()
                                        .getWorld()
                                        .getBlockEntity(updatePedestalFloatPayload.pos());
                                if (be instanceof PedestalBlockEntity pedestal) {
                                    pedestal.updateConfigField(updatePedestalFloatPayload.fieldName(), updatePedestalFloatPayload.value());
                                }
                            }
                        ));

        ServerPlayNetworking.registerGlobalReceiver(
                UpdatePedestalVec3dPayload.ID,
                (payload, context) -> {
                    context.server().execute(() -> {
                        BlockEntity be = context.player()
                                .getWorld()
                                .getBlockEntity(payload.pos());

                        if (be instanceof PedestalBlockEntity pedestal) {
                            pedestal.updateConfigField(
                                    payload.fieldName(),
                                    payload.getVec3d()
                            );
                        }
                    });
                }
        );
    }
}
