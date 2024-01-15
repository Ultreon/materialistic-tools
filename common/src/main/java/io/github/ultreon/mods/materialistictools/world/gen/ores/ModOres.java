package io.github.ultreon.mods.materialistictools.world.gen.ores;

import net.minecraft.tags.BiomeTags;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import io.github.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import io.github.ultreon.mods.materialistictools.world.gen.ores.config.DefaultOreConfig;

import java.util.ArrayList;
import java.util.List;

public final class ModOres {
    private static final List<Runnable> LOADERS = new ArrayList<>();

    // Overworld ores
    public static final ItemMaterialOre SILVER = register(new ItemMaterialOre("silver", () -> ItemMaterial.SILVER, 4, ToolRequirement.STONE, new DefaultOreConfig(4, 8, -64, 40)));
    public static final ItemMaterialOre LEAD = register(new ItemMaterialOre("lead", () -> ItemMaterial.LEAD, 4, ToolRequirement.IRON, new DefaultOreConfig(4, 8, -64, 30)));
    public static final ItemMaterialOre PLATINUM = register(new ItemMaterialOre("platinum", () -> ItemMaterial.PLATINUM, 5, ToolRequirement.IRON, new DefaultOreConfig(2, 8, -25, 20)));
    public static final ItemMaterialOre BAUXITE = register(new ItemMaterialOre("bauxite", () -> ItemMaterial.ALUMINUM, 4, ToolRequirement.STONE, new DefaultOreConfig(6, 8, -37, 44)));
    public static final ItemMaterialOre URANIUM = register(new ItemMaterialOre("uranium", () -> ItemMaterial.URANIUM, 6, ToolRequirement.IRON, new DefaultOreConfig(1, 4, -64, -44)));

    // Nether ores
    public static final ItemMaterialNetherOre COBALT = register(new ItemMaterialNetherOre("cobalt", () -> ItemMaterial.COBALT, 180, ToolRequirement.NETHERITE, new DefaultOreConfig(48, 4, -64, 48)));

    // Rare ores
    public static final ItemMaterialOre ULTRINIUM = register(new ItemMaterialOre("ultrinium", () -> ItemMaterial.ULTRINIUM, 560, Integer.MAX_VALUE / 3, ToolRequirement.COBALT, new DefaultOreConfig(1, 1, -64, 64, 128), BiomeTags.IS_NETHER));

    public static final ItemMaterialOre CHUNK = register(new ItemMaterialOre("chunk", () -> ItemMaterial.CHUNK, 1900, Integer.MAX_VALUE / 2, ToolRequirement.ULTRINIUM, new DefaultOreConfig(1, 1, -64, 64, 1024), Tags.Biomes.IS_RARE));
    public static final ItemMaterialOre INFINITY = register(new ItemMaterialOre("infinity", () -> ItemMaterial.INFINITY, 14280, Integer.MAX_VALUE, ToolRequirement.CHUNK, new DefaultOreConfig(1, 1, -64, 64, 8192), Tags.Biomes.IS_RARE));

    private static <T extends Ore> T register(T ore) {
        LOADERS.add(ore::register);
        return ore;
    }

    public static void register() {
        LOADERS.forEach(Runnable::run);
    }
}
