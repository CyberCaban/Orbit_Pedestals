package net.orbit.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

//        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.GOD_SKEBOB_SWORD);
//        valueLookupBuilder(ItemTags.AXES).add(ModItems.GOD_SKEBOB_AXE);
//        valueLookupBuilder(ItemTags.SHOVELS).add(ModItems.GOD_SKEBOB_SHOVEL);
//        valueLookupBuilder(ItemTags.PICKAXES).add(ModItems.GOD_SKEBOB_PICKAXE);
//        valueLookupBuilder(ItemTags.HOES).add(ModItems.GOD_SKEBOB_HOE);

    }
}
