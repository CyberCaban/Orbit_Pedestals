package net.skebob.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
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
                createShaped(RecipeCategory.TOOLS, ModItems.GOD_SKEBOB_PICKAXE)
                        .pattern("lll")
                        .pattern(" p ")
                        .pattern(" p ")
                        .input('l', ModBlocks.GOD_SKEBOB)
                        .input('p', Items.STICK)
                        .criterion(hasItem(ModItems.GOD_SKEBOB_PICKAXE), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.TOOLS, ModItems.GOD_SKEBOB_AXE)
                        .pattern(" ll")
                        .pattern(" pl")
                        .pattern(" p ")
                        .input('l', ModBlocks.GOD_SKEBOB)
                        .input('p', Items.STICK)
                        .criterion(hasItem(ModItems.GOD_SKEBOB_PICKAXE), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.TOOLS, ModItems.GOD_SKEBOB_SWORD)
                        .pattern(" l ")
                        .pattern(" l ")
                        .pattern(" p ")
                        .input('l', ModBlocks.GOD_SKEBOB)
                        .input('p', Items.STICK)
                        .criterion(hasItem(ModItems.GOD_SKEBOB_PICKAXE), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.TOOLS, ModItems.GOD_SKEBOB_HOE)
                        .pattern("ll ")
                        .pattern(" p ")
                        .pattern(" p ")
                        .input('l', ModBlocks.GOD_SKEBOB)
                        .input('p', Items.STICK)
                        .criterion(hasItem(ModItems.GOD_SKEBOB_PICKAXE), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.TOOLS, ModItems.GOD_SKEBOB_SHOVEL)
                        .pattern(" l ")
                        .pattern(" p ")
                        .pattern(" p ")
                        .input('l', ModBlocks.GOD_SKEBOB)
                        .input('p', Items.STICK)
                        .criterion(hasItem(ModItems.GOD_SKEBOB_PICKAXE), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, ModItems.MAGIC_PAPER)
                        .pattern("d l")
                        .pattern(" p ")
                        .pattern("l d")
                        .input('l', Items.LAPIS_LAZULI)
                        .input('p', Items.PAPER)
                        .input('d', ModItems.SKEBOB)
                        .criterion(hasItem(ModItems.SKEBOB), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, ModItems.EMPTY_ASH)
                        .pattern("lll")
                        .pattern("lpl")
                        .pattern("lll")
                        .input('l', ModItems.MAGIC_PAPER)
                        .input('p', Items.DIAMOND)
                        .criterion(hasItem(ModItems.SKEBOB), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, ModItems.ARROW_ASH)
                        .pattern("lll")
                        .pattern("dpd")
                        .pattern("lll")
                        .input('l', Items.ARROW)
                        .input('d', Items.LAPIS_LAZULI)
                        .input('p', ModItems.EMPTY_ASH)
                        .criterion(hasItem(ModItems.SKEBOB), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, ModItems.WIND_ASH)
                        .pattern("lll")
                        .pattern("dpd")
                        .pattern("lll")
                        .input('l', Items.WIND_CHARGE)
                        .input('d', Items.LAPIS_LAZULI)
                        .input('p', ModItems.EMPTY_ASH)
                        .criterion(hasItem(ModItems.SKEBOB), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, ModBlocks.SKEBOB_BLOCK)
                        .pattern("lll")
                        .pattern("lll")
                        .pattern("lll")
                        .input('l', ModItems.SKEBOB)
                        .criterion(hasItem(ModItems.SKEBOB), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, ModItems.ARROW_EX_ASH)
                        .pattern("lll")
                        .pattern("lpl")
                        .pattern("lll")
                        .input('l', ModItems.EMPTY_ASH)
                        .input('p', ModItems.ARROW_ASH)
                        .criterion(hasItem(ModItems.SKEBOB), conditionsFromItem(ModItems.SKEBOB))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.GOD_SKEBOB)
                        .pattern("lll")
                        .pattern("lpl")
                        .pattern("lll")
                        .input('l', ModBlocks.SKEBOB_SMART_BLOCK)
                        .input('p', Items.NETHER_STAR)
                        .criterion(hasItem(ModBlocks.SKEBOB_SMART_BLOCK), conditionsFromItem(ModBlocks.SKEBOB_SMART_BLOCK))
                        .offerTo(exporter);
                offerSmelting(List.of(ModBlocks.SKEBOB_BLOCK), RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SKEBOB_SMART_BLOCK,
                        0.1f,
                        300,
                        "skebob");

                createShaped(RecipeCategory.MISC, ModBlocks.PEDESTAL_BLOCK)
                        .pattern("lll")
                        .pattern(" s ")
                        .pattern("sss")
                        .input('s', Items.SMOOTH_STONE)
                        .input('l', Items.SMOOTH_STONE_SLAB)
                        .criterion(hasItem(Items.SMOOTH_STONE), conditionsFromItem(Items.SMOOTH_STONE))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "SkebobRecipeProvider";
    }
}
