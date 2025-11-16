package net.orbit.item;

import net.orbit.OrbitPedestals;

public class ModItemGroups {
//    public static final ItemGroup SKEBOB_ITEMS = Registry.register(Registries.ITEM_GROUP,
//            Identifier.of(Skebob.MOD_ID, "skebob_items"),
//            FabricItemGroup.builder()
//                    .icon(()->new ItemStack(ModItems.SKEBOB))
//                    .displayName(Text.translatable(ModItems.SKEBOB.getTranslationKey()))
//                    .entries((displayContext, entries) -> {
                        // ITEMS
//                        entries.add(ModItems.SKEBOB);
//                        entries.add(ModItems.GOD_SKEBOB_PICKAXE);
//                        entries.add(ModItems.GOD_SKEBOB_SHOVEL);
//                        entries.add(ModItems.GOD_SKEBOB_SWORD);
//                        entries.add(ModItems.GOD_SKEBOB_AXE);
//                        entries.add(ModItems.GOD_SKEBOB_HOE);

                        // AOW ITEMS
//                        entries.add(ModItems.MAGIC_PAPER);
//                        entries.add(ModItems.EMPTY_ASH);
//                        entries.add(ModItems.WIND_ASH);
//                        entries.add(ModItems.ARROW_ASH);
//                        entries.add(ModItems.ARROW_EX_ASH);
//                        entries.add(ModItems.GLINTSTONE_ASH);

                        // BLOCKS
//                        entries.add(ModBlocks.SKEBOB_BLOCK);
//                        entries.add(ModBlocks.SKEBOB_SMART_BLOCK);
//                        entries.add(ModBlocks.GOD_SKEBOB);
//                        entries.add(ModBlocks.PEDESTAL_BLOCK);
//                    })
//                    .build());
    public static void registerItemGroups() {
        OrbitPedestals.LOGGER.info("Registering Mod Item Groups for: " + OrbitPedestals.MOD_ID);
    }
}
