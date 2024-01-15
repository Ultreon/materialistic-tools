package io.github.ultreon.mods.materialistictools.fabric;

import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import net.fabricmc.api.ModInitializer;

public class MaterialisticToolsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MaterialisticTools.init();
        MaterialisticTools.postInit();
    }
}