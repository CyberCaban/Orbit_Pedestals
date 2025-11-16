package net.orbit.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.orbit.OrbitPedestals;

public record UpdatePedestalVec3dPayload(
        BlockPos pos,
        String fieldName,
        double x,
        double y,
        double z
) implements CustomPayload {

    public static final CustomPayload.Id<UpdatePedestalVec3dPayload> ID =
            new CustomPayload.Id<>(Identifier.of(OrbitPedestals.MOD_ID, "update_pedestal_vec3d"));

    public static final PacketCodec<RegistryByteBuf, UpdatePedestalVec3dPayload> CODEC =
            PacketCodec.tuple(
                    BlockPos.PACKET_CODEC, UpdatePedestalVec3dPayload::pos,
                    PacketCodecs.STRING, UpdatePedestalVec3dPayload::fieldName,
                    PacketCodecs.DOUBLE, UpdatePedestalVec3dPayload::x,
                    PacketCodecs.DOUBLE, UpdatePedestalVec3dPayload::y,
                    PacketCodecs.DOUBLE, UpdatePedestalVec3dPayload::z,
                    UpdatePedestalVec3dPayload::new
            );

    public UpdatePedestalVec3dPayload(BlockPos pos, String fieldName, Vec3d vec) {
        this(pos, fieldName, vec.x, vec.y, vec.z);
    }

    public Vec3d getVec3d() {
        return new Vec3d(x, y, z);
    }

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
