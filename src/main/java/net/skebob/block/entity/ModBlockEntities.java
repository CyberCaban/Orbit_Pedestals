package net.skebob.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.skebob.OrbitPedestals;
import net.skebob.block.ModBlocks;
import net.skebob.block.entity.custom.PedestalBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(OrbitPedestals.MOD_ID, "pedestal_be"),
                    FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL_BLOCK).build());

    public static void registerBlockEntities() {
        OrbitPedestals.LOGGER.info("Registering block entities");
    }
}
