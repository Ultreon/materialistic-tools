package io.github.ultreon.mods.materialistictools.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static io.github.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

public class ModBlockTags {
    public static final TagKey<Block> NEEDS_COBALT_TOOL = tag("needs_cobalt_tool");
    public static final TagKey<Block> NEEDS_ULTRINIUM_TOOL = tag("needs_ultrinium_tool");
    public static final TagKey<Block> NEEDS_CHUNK_TOOL = tag("needs_chunk_tool");
    public static final TagKey<Block> NEEDS_INFINITY_TOOL = tag("needs_infinity_tool");
    public static final TagKey<Block> MINEABLE_WITH_SWORD = tag("mineable_with_sword");
    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = tag("needs_netherite_tool");
    public static final TagKey<Block> NEEDS_WOOD_TOOL = tag("needs_netherite_tool");
    public static final TagKey<Block> NEEDS_GOLD_TOOL = tag("needs_golden_tool");

    private static TagKey<Block> forgeTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation("forge", name));
    }

    private static TagKey<Block> mcTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, name));
    }

    private static TagKey<Block> tag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, name));
    }

    public static void init() {
        // Just initialize tags.
    }
}
