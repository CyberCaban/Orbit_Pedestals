package net.skebob.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.skebob.block.ModBlocks;
import net.skebob.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SKEBOB_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SKEBOB_SMART_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GOD_SKEBOB);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SKEBOB, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOD_SKEBOB_PICKAXE,Models.GENERATED);
        itemModelGenerator.register(ModItems.GOD_SKEBOB_SHOVEL,Models.GENERATED);
        itemModelGenerator.register(ModItems.GOD_SKEBOB_SWORD,Models.GENERATED);
        itemModelGenerator.register(ModItems.GOD_SKEBOB_AXE,Models.GENERATED);
        itemModelGenerator.register(ModItems.GOD_SKEBOB_HOE,Models.GENERATED);

    }
}
