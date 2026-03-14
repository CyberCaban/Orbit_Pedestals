package net.orbit.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.orbit.OrbitPedestals;

public record UpdatePedestalBooleanPayload(
        BlockPos pos,
        String fieldName,
        boolean value
) {

    public static final Identifier ID = new Identifier(OrbitPedestals.MOD_ID, "update_pedestal_boolean");

    public UpdatePedestalBooleanPayload(PacketByteBuf buf) {
        this(buf.readBlockPos(), buf.readString(), buf.readBoolean());
    }

    public void write(PacketByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeString(fieldName);
        buf.writeBoolean(value);
    }
}
