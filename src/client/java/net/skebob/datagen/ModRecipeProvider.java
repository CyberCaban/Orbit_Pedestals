package net.skebob.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.skebob.block.ModBlocks;
import net.skebob.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, ModItems.SKEBOB, 9)
                        .input(ModBlocks.SKEBOB_BLOCK)
                        .criterion(hasItem(ModItems.SKEBOB), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.SKEBOB_BLOCK)
                        .pattern("lll")
                        .pattern("lll")
                        .pattern("lll")
                        .input('l', ModItems.SKEBOB)
                        .criterion(hasItem(ModItems.SKEBOB), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);

                offerSmelting(List.of(ModBlocks.SKEBOB_BLOCK), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SKEBOB_SMART_BLOCK,
                        0.1f,
                        300,
                        "skebob");
            }
        };
    }

    @Override
    public String getName() {
        return "SkebobRecipeProvider";
    }
}
