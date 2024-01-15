package io.github.ultreon.mods.materialistictools.init.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static io.github.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

public class ModBlockTags {
    public static final TagKey<Block> NEEDS_COBALT_TOOL = tag("needs_cobalt_tool");
    public static final TagKey<Block> NEEDS_ULTRINIUM_TOOL = tag("needs_ultrinium_tool");
    public static final TagKey<Block> NEEDS_CHUNK_TOOL = tag("needs_chunk_tool");
    public static final TagKey<Block> NEEDS_INFINITY_TOOL = tag("needs_infinity_tool");
    public static final TagKey<Block> MINEABLE_WITH_SWORD = forgeTag("mineable_with_sword");
    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = forgeTag("needs_netherite_tool");

    private static TagKey<Block> forgeTag(String name) {
        return BlockTags.create(new ResourceLocation("forge", name));
    }

    private static TagKey<Block> mcTag(String name) {
        return BlockTags.create(new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, name));
    }

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(new ResourceLocation(MOD_ID, name));
    }

    public static void init() {
        // Just initialize tags.
    }
}
