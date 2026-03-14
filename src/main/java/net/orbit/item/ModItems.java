package net.orbit.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.orbit.OrbitPedestals;

import java.util.function.Function;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, new Identifier(OrbitPedestals.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings);

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void registerModItems() {
        OrbitPedestals.LOGGER.info("Registering Mod Items for " + OrbitPedestals.MOD_ID);
    }
}
