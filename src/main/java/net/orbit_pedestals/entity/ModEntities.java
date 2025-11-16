package net.orbit_pedestals.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.orbit_pedestals.OrbitPedestals;

public class ModEntities {
    public static final EntityType<GlintstoneProjectileEntity> GLINTSTONE_PROJECTILE_ENTITY =
            registerEntity("glintstone_entity",
            EntityType.Builder.<GlintstoneProjectileEntity>create(GlintstoneProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).maxTrackingRange(4).trackingTickInterval(20));

    public static <T extends Entity>EntityType<T> registerEntity(String id, EntityType.Builder<T> type) {
        RegistryKey<EntityType<?>> registryKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(OrbitPedestals.MOD_ID, id));
        return Registry.register(Registries.ENTITY_TYPE, registryKey, type.build(registryKey));
    }

    public static void registerEntities() {}
}
