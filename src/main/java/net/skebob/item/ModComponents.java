package net.skebob.item;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.skebob.Skebob;

public class ModComponents {
    public static final ComponentType<?> AOW_INFUSABLE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Skebob.MOD_ID, "aow_infusable"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static void registerModComponents() {
        Skebob.LOGGER.info("Registering ModComponents for: " + Skebob.MOD_ID);
    }
}
