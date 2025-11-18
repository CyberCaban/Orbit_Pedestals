package net.orbit.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.orbit.OrbitPedestals;

public record UpdatePedestalBooleanPayload(
        BlockPos pos,
        String fieldName,
        boolean value
) implements CustomPayload {

    public static final Id<UpdatePedestalBooleanPayload> ID =
            new Id<>(Identifier.of(OrbitPedestals.MOD_ID, "update_pedestal_boolean"));

    public static final PacketCodec<RegistryByteBuf, UpdatePedestalBooleanPayload> CODEC =
            PacketCodec.tuple(
                    BlockPos.PACKET_CODEC, UpdatePedestalBooleanPayload::pos,
                    PacketCodecs.STRING, UpdatePedestalBooleanPayload::fieldName,
                    PacketCodecs.BOOL, UpdatePedestalBooleanPayload::value,
                    UpdatePedestalBooleanPayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
