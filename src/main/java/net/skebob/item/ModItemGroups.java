package net.skebob.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.skebob.Skebob;
import net.skebob.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup SKEBOB_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Skebob.MOD_ID, "skebob_items"),
            FabricItemGroup.builder()
                    .icon(()->new ItemStack(ModItems.SKEBOB))
                    .displayName(Text.translatable(ModItems.SKEBOB.getTranslationKey()))
                    .entries((displayContext, entries) -> {
                        // ITEMS
                        entries.add(ModItems.SKEBOB);

                        // BLOCKS
                        entries.add(ModBlocks.SKEBOB_BLOCK);
                        entries.add(ModBlocks.SKEBOB_SMART_BLOCK);
                    })
                    .build());
    public static void registerItemGroups() {
        Skebob.LOGGER.info("Registering Mod Item Groups for: " + Skebob.MOD_ID);
    }
}
