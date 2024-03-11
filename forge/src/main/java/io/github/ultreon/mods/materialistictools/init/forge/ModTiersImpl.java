package io.github.ultreon.mods.materialistictools.init.forge;

import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.init.ModTiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ModTiersImpl {
    public static void postInit() {
        TierSortingRegistry.registerTier(ModTiers.COBALT, MaterialisticTools.res("cobalt"), List.of(Tiers.NETHERITE), Collections.emptyList());
        TierSortingRegistry.registerTier(ModTiers.CHUNK, MaterialisticTools.res("chunk"), List.of(ModTiers.COBALT), Collections.emptyList());
        TierSortingRegistry.registerTier(ModTiers.ULTRINIUM, MaterialisticTools.res("ultrinium"), List.of(ModTiers.CHUNK), Collections.emptyList());
        TierSortingRegistry.registerTier(ModTiers.INFINITY, MaterialisticTools.res("infinity"), List.of(ModTiers.ULTRINIUM), Collections.emptyList());
    }
}
