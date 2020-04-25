package com.github.sa_shiro.extended_storage;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("extended_storage")
public class ExtendedStorage {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "extended_storage";

    public ExtendedStorage() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::client);
    }

    private void client(final FMLClientSetupEvent event) {
    }
}
