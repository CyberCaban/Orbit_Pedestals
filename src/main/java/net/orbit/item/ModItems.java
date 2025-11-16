package net.orbit.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.orbit.OrbitPedestals;
import net.orbit.block.ModBlocks;

import java.util.function.Function;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(OrbitPedestals.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

//    public static final Item SKEBOB = register("skebob", SkebobItem::new, new Item.Settings());
//
//    public static final Item GOD_SKEBOB_PICKAXE = register("god_skebob_pickaxe", settings -> new Item(settings.pickaxe(ModToolMaterials.GOD_SKEBOB_MATERIAL, 5f, 10f)), new Item.Settings());
//    public static final Item GOD_SKEBOB_AXE = register("god_skebob_axe", settings -> new AxeItem(ModToolMaterials.GOD_SKEBOB_MATERIAL, 10f, 1f,settings), new Item.Settings());
//    public static final Item GOD_SKEBOB_SWORD = register("god_skebob_sword", settings -> new Item(settings.sword(ModToolMaterials.GOD_SKEBOB_MATERIAL, 8f, 4f)), new Item.Settings());
//    public static final Item GOD_SKEBOB_HOE = register("god_skebob_hoe", settings -> new HoeItem(ModToolMaterials.GOD_SKEBOB_MATERIAL, 3f, 3f,settings), new Item.Settings());
//    public static final Item GOD_SKEBOB_SHOVEL = register("god_skebob_shovel", settings -> new ShovelItem(ModToolMaterials.GOD_SKEBOB_MATERIAL, 3f, 3f,settings), new Item.Settings());
//
//
//
//    public static final Item MAGIC_PAPER=register("magic_paper",Item::new,new Item.Settings());
//    public static final Item EMPTY_ASH = register("empty_ash",Item::new,new Item.Settings());
//    public static final Item WIND_ASH = register("wind_aow", settings -> new AoWItem(settings, AoWAbilities.WIND_ASH_ABILITY), new Item.Settings());
//    public static final Item ARROW_ASH = register("arrow_aow", settings -> new AoWItem(settings, AoWAbilities.ARROW_ASH_ABILITY), new Item.Settings());
//    public static final Item ARROW_EX_ASH = register("arrow_ex_aow",settings -> new AoWItem(settings, AoWAbilities.ARROW_EX_ASH_ABILITY), new Item.Settings());
//    public static final Item GLINTSTONE_ASH = register("glintstone_aow", settings -> new AoWItem(settings, AoWAbilities.GLINTSTONE_ASH_ABILITY), new Item.Settings());
//    public static final Map<AshAbility, Item> AOW_ABILITIES = Map.of(
//            AoWAbilities.WIND_ASH_ABILITY, WIND_ASH,
//            AoWAbilities.ARROW_ASH_ABILITY, ARROW_ASH,
//            AoWAbilities.ARROW_EX_ASH_ABILITY, ARROW_EX_ASH,
//            AoWAbilities.GLINTSTONE_ASH_ABILITY, GLINTSTONE_ASH
//    );

    public static void registerModItems() {
        OrbitPedestals.LOGGER.info("Registering Mod Items for " + OrbitPedestals.MOD_ID);
    }
}
