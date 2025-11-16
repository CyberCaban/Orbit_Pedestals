package net.orbit_pedestals.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SKEBOB_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SKEBOB_SMART_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GOD_SKEBOB);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
//        itemModelGenerator.register(ModItems.SKEBOB, Models.GENERATED);
//        itemModelGenerator.register(ModItems.GOD_SKEBOB_PICKAXE, Models.HANDHELD);
//        itemModelGenerator.register(ModItems.GOD_SKEBOB_SHOVEL, Models.GENERATED);
//        itemModelGenerator.register(ModItems.GOD_SKEBOB_SWORD, Models.GENERATED);
//        itemModelGenerator.register(ModItems.GOD_SKEBOB_AXE, Models.GENERATED);
//        itemModelGenerator.register(ModItems.GOD_SKEBOB_HOE, Models.GENERATED);
//        itemModelGenerator.register(ModItems.MAGIC_PAPER,Models.GENERATED);
//
//
//
//
//        // Datagen for AoWItem
//        final Identifier MODEL_WIND_ASH = Models.GENERATED_TWO_LAYERS.upload(ModItems.WIND_ASH, TextureMap.layered(
//                Identifier.of("skebob:item/aow"),
//                Identifier.of("item/wind_charge")), itemModelGenerator.modelCollector);
//        itemModelGenerator.output.accept(ModItems.WIND_ASH, ItemModels.basic(MODEL_WIND_ASH));
//
//        final Identifier EMPTY_ASH = Models.GENERATED.upload(ModItems.EMPTY_ASH,
//                TextureMap.layer0(Identifier.of("skebob:item/aow")),
//                itemModelGenerator.modelCollector);
//        itemModelGenerator.output.accept(ModItems.EMPTY_ASH, ItemModels.basic(EMPTY_ASH));
//
//        final Identifier MODEL_ARROW_ASH  = Models.GENERATED_TWO_LAYERS.upload(ModItems.ARROW_ASH, TextureMap.layered(
//                Identifier.of("skebob:item/aow"),
//                Identifier.of("item/arrow")), itemModelGenerator.modelCollector);
//        itemModelGenerator.output.accept(ModItems.ARROW_ASH, ItemModels.basic(MODEL_ARROW_ASH));
//        final Identifier MODEL_ARROW_EX_ASH  = Models.GENERATED_TWO_LAYERS.upload(ModItems.ARROW_EX_ASH, TextureMap.layered(
//                Identifier.of("skebob:item/aowex"),
//                Identifier.of("item/arrow")), itemModelGenerator.modelCollector);
//        itemModelGenerator.output.accept(ModItems.ARROW_EX_ASH, ItemModels.basic(MODEL_ARROW_EX_ASH));
    }
}
