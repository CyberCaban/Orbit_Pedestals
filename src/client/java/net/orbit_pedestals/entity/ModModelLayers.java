package net.orbit_pedestals.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.orbit_pedestals.OrbitPedestals;

public class ModModelLayers {
    public static final EntityModelLayer GLINTSTONE_PROJECTILE = new EntityModelLayer(
            Identifier.of(OrbitPedestals.MOD_ID, "glintstone_entity"), "main");
    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(GLINTSTONE_PROJECTILE, GlintstoneProjectileModel::getTexturedModelData);
    }
}
