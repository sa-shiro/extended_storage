package com.github.sa_shiro.extended_storage;

import com.github.sa_shiro.extended_storage.event.DeferredRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("extended_storage")
@Mod.EventBusSubscriber(modid = ExtendedStorage.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExtendedStorage {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "extended_storage";
    public static ExtendedStorage instance;
    final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public ExtendedStorage() {
        eventBus.addListener(this::client);
        DeferredRegistryEvent.BLOCKS.register(eventBus);
        DeferredRegistryEvent.ITEMS.register(eventBus);
        DeferredRegistryEvent.TILE_ENTITY_TYPE.register(eventBus);
        DeferredRegistryEvent.CONTAINER_TYPE.register(eventBus);

        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void client(final FMLClientSetupEvent event) {

    }
}
