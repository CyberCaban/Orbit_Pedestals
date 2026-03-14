package net.orbit.network;

import net.minecraft.network.PacketByteBuf;
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
    ) {

        public static final Identifier ID = new Identifier(OrbitPedestals.MOD_ID, "update_pedestal_vec3d");

        public UpdatePedestalVec3dPayload(PacketByteBuf buf) {
        this(buf.readBlockPos(), buf.readString(), buf.readDouble(), buf.readDouble(), buf.readDouble());
        }

    public UpdatePedestalVec3dPayload(BlockPos pos, String fieldName, Vec3d vec) {
        this(pos, fieldName, vec.x, vec.y, vec.z);
    }

    public Vec3d getVec3d() {
        return new Vec3d(x, y, z);
    }

    public void write(PacketByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeString(fieldName);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }
}
