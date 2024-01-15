package io.github.ultreon.mods.materialistictools;

import dev.architectury.registry.level.biome.BiomeModifications;
import io.github.ultreon.mods.materialistictools.init.*;
import io.github.ultreon.mods.materialistictools.item.tool.ModTraits;
import io.github.ultreon.mods.materialistictools.world.gen.ores.ModOres;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaterialisticTools
{
	public static final String MOD_ID = "materialistic_tools";
	public static final Logger LOGGER = LoggerFactory.getLogger("MaterialisticTools");

	public static void init() {
		ModBlocks.register();
		ModItems.register();
		ModEffects.register();
		ModOres.register();
		ModTraits.register();
		ModCreativeTabs.register();
	}

	public static void postInit() {
		ModTiers.postInit();
	}

	public static ResourceLocation res(String name) {
		return new ResourceLocation(MOD_ID, name);
	}
}
