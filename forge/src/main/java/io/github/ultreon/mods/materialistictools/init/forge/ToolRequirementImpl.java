package io.github.ultreon.mods.materialistictools.init.forge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ToolRequirementImpl {
    private static Tier registerTierWithSorting(Tier tier, ResourceLocation name, List<Object> after, List<Object> before) {
        return TierSortingRegistry.registerTier(tier, name, after, before);
    }
}
