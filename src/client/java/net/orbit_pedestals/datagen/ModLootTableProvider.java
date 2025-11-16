package net.orbit_pedestals.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import net.orbit_pedestals.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
//        addDrop(ModBlocks.SKEBOB_BLOCK);
//        addDrop(ModBlocks.GOD_SKEBOB);
//        addDrop(ModBlocks.SKEBOB_SMART_BLOCK,
//                LootTable.builder()
//                        .pool(
//                            LootPool.builder()
//                                .rolls(new UniformLootNumberProvider(
//                                        new ConstantLootNumberProvider(0),
//                                        new ConstantLootNumberProvider(1)))
//                                .with(ItemEntry.builder(ModBlocks.SKEBOB_BLOCK))
//                        )
//                        .pool(
//                        LootPool.builder()
//                                .rolls(new UniformLootNumberProvider(
//                                        new ConstantLootNumberProvider(0),
//                                        new ConstantLootNumberProvider(1)))
//                                .with(ItemEntry.builder(ModBlocks.SKEBOB_BLOCK))
//                        )
//        );
        addDrop(ModBlocks.PEDESTAL_BLOCK);
    }
}
