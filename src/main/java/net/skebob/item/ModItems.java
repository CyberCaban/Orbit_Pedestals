package net.skebob.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.skebob.Skebob;
import net.skebob.item.custom.AoWItem;
import net.skebob.item.custom.SkebobItem;
import net.skebob.item.custom.ability.WindAshAbility;

import java.util.function.Function;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Skebob.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static final Item SKEBOB = register("skebob", SkebobItem::new, new Item.Settings());

    public static final Item GOD_SKEBOB_PICKAXE = register("god_skebob_pickaxe", settings -> new Item(settings.pickaxe(ModToolMaterials.GOD_SKEBOB_MATERIAL, 5f, 10f)), new Item.Settings());
    public static final Item GOD_SKEBOB_AXE = register("god_skebob_axe", settings -> new AxeItem(ModToolMaterials.GOD_SKEBOB_MATERIAL, 10f, 1f,settings), new Item.Settings());
    public static final Item GOD_SKEBOB_SWORD = register("god_skebob_sword", settings -> new Item(settings.sword(ModToolMaterials.GOD_SKEBOB_MATERIAL, 8f, 4f)), new Item.Settings());
    public static final Item GOD_SKEBOB_HOE = register("god_skebob_hoe", settings -> new HoeItem(ModToolMaterials.GOD_SKEBOB_MATERIAL, 3f, 3f,settings), new Item.Settings());
    public static final Item GOD_SKEBOB_SHOVEL = register("god_skebob_shovel", settings -> new ShovelItem(ModToolMaterials.GOD_SKEBOB_MATERIAL, 3f, 3f,settings), new Item.Settings());

    public static final Item WIND_ASH = register("wind_aow", settings -> new AoWItem(settings, new WindAshAbility()), new Item.Settings());

    public static void registerModItems() {
        Skebob.LOGGER.info("Registering Mod Items for " + Skebob.MOD_ID);
    }
}
