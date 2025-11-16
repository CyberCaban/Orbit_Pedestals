package net.orbit_pedestals.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.orbit_pedestals.OrbitPedestals;

public record UpdatePedestalFloatPayload(
        BlockPos pos,
        String fieldName,
        float value
) implements CustomPayload {

    public static final CustomPayload.Id<UpdatePedestalFloatPayload> ID =
            new CustomPayload.Id<>(Identifier.of(OrbitPedestals.MOD_ID, "update_pedestal_float"));

    public static final PacketCodec<RegistryByteBuf, UpdatePedestalFloatPayload> CODEC =
            PacketCodec.tuple(
                    BlockPos.PACKET_CODEC, UpdatePedestalFloatPayload::pos,
                    PacketCodecs.STRING, UpdatePedestalFloatPayload::fieldName,
                    PacketCodecs.FLOAT, UpdatePedestalFloatPayload::value,
                    UpdatePedestalFloatPayload::new
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
