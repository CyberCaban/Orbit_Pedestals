package net.orbit.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.orbit.OrbitPedestals;

public record UpdatePedestalFloatPayload(
        BlockPos pos,
        String fieldName,
        float value
) {

    public static final Identifier ID = new Identifier(OrbitPedestals.MOD_ID, "update_pedestal_float");

    public UpdatePedestalFloatPayload(PacketByteBuf buf) {
        this(buf.readBlockPos(), buf.readString(), buf.readFloat());
    }

    public void write(PacketByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeString(fieldName);
        buf.writeFloat(value);
    }
}
