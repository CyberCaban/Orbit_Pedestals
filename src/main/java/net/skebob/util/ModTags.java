package net.skebob.util;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.skebob.OrbitPedestals;


public class ModTags {
    public static class Items {
        public static final TagKey<Item> ORBIT_PEDESTALS_ITEMS = createTag("orbit_pedestals_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(OrbitPedestals.MOD_ID, name));
        }
    }
}
