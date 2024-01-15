package io.github.ultreon.mods.materialistictools.item.forge;

import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

import static io.github.ultreon.mods.materialistictools.init.ModTiers.*;

public class ModTiersImpl {
    public static void postInit() {
        TierSortingRegistry.registerTier(COBALT, MaterialisticTools.res("cobalt"), List.of(Tiers.NETHERITE), List.of());
        TierSortingRegistry.registerTier(ULTRINIUM, MaterialisticTools.res("ultrinium"), List.of(COBALT), List.of());
        TierSortingRegistry.registerTier(CHUNK, MaterialisticTools.res("chunk"), List.of(ULTRINIUM), List.of());
        TierSortingRegistry.registerTier(INFINITY, MaterialisticTools.res("infinity"), List.of(CHUNK), List.of());
    }
}
