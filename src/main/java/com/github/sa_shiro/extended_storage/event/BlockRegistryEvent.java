package com.github.sa_shiro.extended_storage.event;

import com.github.sa_shiro.extended_storage.ExtendedStorage;
import com.github.sa_shiro.extended_storage.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistryEvent {

    @SubscribeEvent
    public static void registerBlocks (final RegistryEvent.Register<Block> e) {
        ExtendedStorage.LOGGER.info("Registering Blocks...");
        e.getRegistry().registerAll(
                Blocks.IRON_CHEST = new Block(Block.Properties.create(Material.IRON)).setRegistryName("iron_chest"),
                Blocks.GOLD_CHEST = new Block(Block.Properties.create(Material.IRON)).setRegistryName("gold_chest"),
                Blocks.DIAMOND_CHEST = new Block(Block.Properties.create(Material.IRON)).setRegistryName("diamond_chest"),
                Blocks.OBSIDIAN_CHEST = new Block(Block.Properties.create(Material.IRON)).setRegistryName("obsidian_chest"),
                Blocks.EMERALD_CHEST = new Block(Block.Properties.create(Material.IRON)).setRegistryName("emerald_chest")
        );
    }
}