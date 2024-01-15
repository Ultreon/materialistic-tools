package io.github.ultreon.mods.materialistictools.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MaterialisticTools.MOD_ID)
public class MaterialisticToolsForge {
    public MaterialisticToolsForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(MaterialisticTools.MOD_ID, modEventBus);
        MaterialisticTools.init();

        modEventBus.register(this);
    }

    @SubscribeEvent
    public void onLoadComplete() {
        MaterialisticTools.postInit();
    }
}