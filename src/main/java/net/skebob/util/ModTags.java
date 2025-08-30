package net.skebob.util;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.skebob.Skebob;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> SKEBOB_ITEMS = createTag("skebob_items");
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Skebob.MOD_ID, name));
        }
    }
}
