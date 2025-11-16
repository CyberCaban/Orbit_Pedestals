package net.skebob.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.skebob.Skebob;

import java.util.function.Function;

public class ModBlocks {
    private static Block register(String name,
                                  Function<AbstractBlock.Settings, Block> blockFactory,
                                  AbstractBlock.Settings settings,
                                  boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Skebob.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Skebob.MOD_ID, name));
    }
    public static final Block SKEBOB_BLOCK = register("skebob_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(20000f)
                    .hardness(0.9999f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
            ,
            true
            );
    public static final Block GOD_SKEBOB = register("god_skebob_block",
            GodSkebobBlock::new,
            AbstractBlock.Settings.create()
                    .strength(1f)
                    .hardness(1f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.ANVIL)
            ,
                true
    );

    public static final Block SKEBOB_SMART_BLOCK = register("skebob_smart_block",
            SmartSkebobBlock::new,
            AbstractBlock.Settings.create()
                    .strength(1f)
                    .hardness(1f)
                    .requiresTool()
                    .luminance(value -> value.get(SmartSkebobBlock.CLICKED) ? 15 : 0)
                    .sounds(BlockSoundGroup.HONEY)
            ,
            true
    );

    public static final Block PEDESTAL_BLOCK = register("pedestal_block", PedestalBlock::new,
            AbstractBlock.Settings.create()
                    .nonOpaque()
                    .strength(1f)
                    .hardness(1f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
            ,
            true);

    public static void registerModBlocks() {
        Skebob.LOGGER.info("Registering Mod Blocks for " + Skebob.MOD_ID);
    }
}
