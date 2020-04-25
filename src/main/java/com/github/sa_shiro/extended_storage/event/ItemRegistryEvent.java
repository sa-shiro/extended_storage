package com.github.sa_shiro.extended_storage.event;

import com.github.sa_shiro.extended_storage.ExtendedStorage;
import com.github.sa_shiro.extended_storage.block.Blocks;
import com.github.sa_shiro.extended_storage.item.Items;
import com.github.sa_shiro.extended_storage.util.ItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistryEvent {

    private static final Item.Properties properties = new Item.Properties().group(ItemGroup.itemGroup);

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> e) {
        ExtendedStorage.LOGGER.info("Registering BlockItems...");
        assert false;
        e.getRegistry().registerAll(
                Items.IRON_CHEST = new BlockItem(Blocks.IRON_CHEST, properties).setRegistryName(Blocks.IRON_CHEST.getRegistryName()),
                Items.GOLD_CHEST = new BlockItem(Blocks.GOLD_CHEST, properties).setRegistryName(Blocks.GOLD_CHEST.getRegistryName()),
                Items.DIAMOND_CHEST = new BlockItem(Blocks.DIAMOND_CHEST, properties).setRegistryName(Blocks.DIAMOND_CHEST.getRegistryName()),
                Items.OBSIDIAN_CHEST = new BlockItem(Blocks.OBSIDIAN_CHEST, properties).setRegistryName(Blocks.OBSIDIAN_CHEST.getRegistryName()),
                Items.EMERALD_CHEST = new BlockItem(Blocks.EMERALD_CHEST, properties).setRegistryName(Blocks.EMERALD_CHEST.getRegistryName())
        );
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> e) {
        ExtendedStorage.LOGGER.info("Registering Items...");
        assert false;
        e.getRegistry().registerAll(
                Items.IRON_BAG = new Item(properties).setRegistryName("iron_bag"),
                Items.GOLD_BAG = new Item(properties).setRegistryName("gold_bag"),
                Items.DIAMOND_BAG = new Item(properties).setRegistryName("diamond_bag"),
                Items.OBSIDIAN_BAG = new Item(properties).setRegistryName("obsidian_bag"),
                Items.EMERALD_BAG = new Item(properties).setRegistryName("emerald_bag")
        );
    }
}
