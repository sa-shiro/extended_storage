package com.github.sa_shiro.extended_storage.event;

import com.github.sa_shiro.extended_storage.ExtendedStorage;
import com.github.sa_shiro.extended_storage.client.gui.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ExtendedStorage.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        ScreenManager.registerFactory(DeferredRegistryEvent.IRON_CHEST_CONTAINER.get(), IronChestScreen::new);
        ScreenManager.registerFactory(DeferredRegistryEvent.GOLD_CHEST_CONTAINER.get(), GoldChestScreen::new);
        ScreenManager.registerFactory(DeferredRegistryEvent.DIAMOND_CHEST_CONTAINER.get(), DiamondChestScreen::new);
        ScreenManager.registerFactory(DeferredRegistryEvent.OBSIDIAN_CHEST_CONTAINER.get(), ObsidianChestScreen::new);
        ScreenManager.registerFactory(DeferredRegistryEvent.EMERALD_CHEST_CONTAINER.get(), EmeraldChestScreen::new);
        ScreenManager.registerFactory(DeferredRegistryEvent.DRAGON_CHEST_CONTAINER.get(), DragonChestScreen::new);
    }
}
