package net.orbit.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.orbit.OrbitPedestals;

public class ModEntities {
    public static <T extends Entity>EntityType<T> registerEntity(String id, EntityType.Builder<T> type) {
        RegistryKey<EntityType<?>> registryKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(OrbitPedestals.MOD_ID, id));
        return Registry.register(Registries.ENTITY_TYPE, registryKey, type.build(id));
    }

    public static void registerEntities() {
    }
}
