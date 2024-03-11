package io.github.ultreon.mods.materialistictools.item.tool;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.init.ModTiers;
import io.github.ultreon.mods.materialistictools.init.tags.ModBlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;

import java.util.List;

public enum ToolRequirement {
    WOOD(ModBlockTags.NEEDS_WOOD_TOOL, Tiers.WOOD),
    GOLD(ModBlockTags.NEEDS_GOLD_TOOL, Tiers.GOLD),
    STONE(BlockTags.NEEDS_STONE_TOOL, Tiers.STONE),
    IRON(BlockTags.NEEDS_IRON_TOOL, Tiers.IRON),
    DIAMOND(BlockTags.NEEDS_DIAMOND_TOOL, Tiers.DIAMOND),
    NETHERITE(ModBlockTags.NEEDS_NETHERITE_TOOL, Tiers.NETHERITE),
    COBALT(ModBlockTags.NEEDS_COBALT_TOOL, ModTiers.COBALT),
    ULTRINIUM(ModBlockTags.NEEDS_ULTRINIUM_TOOL, ModTiers.ULTRINIUM),
    CHUNK(ModBlockTags.NEEDS_CHUNK_TOOL, ModTiers.CHUNK),
    INFINITY(ModBlockTags.NEEDS_INFINITY_TOOL, ModTiers.INFINITY);

    private final TagKey<Block> tag;
    private final Tier tier;

    ToolRequirement(TagKey<Block> tag, Tier tier) {
        this.tag = tag;
        this.tier = tier;
    }

    public TagKey<Block> getTag() {
        return this.tag;
    }

    public Tier getTier() {
        return this.tier;
    }

    private Tier register(ResourceLocation name, List<Object> after, List<Object> before) {
        return registerTierWithSorting(this.tier, name, after, before);
    }

    @ExpectPlatform
    private static Tier registerTierWithSorting(Tier tier, ResourceLocation name, List<Object> after, List<Object> before) {
        return tier;
    }

    public static void registerAll() {
        COBALT.register(MaterialisticTools.res("cobalt"), List.of("netherite"), List.of(ULTRINIUM.tier, INFINITY.tier));
        ULTRINIUM.register(MaterialisticTools.res("ultrinium"), List.of(COBALT.tier), List.of(INFINITY.tier));
        INFINITY.register(MaterialisticTools.res("infinity"), List.of(ULTRINIUM), List.of());
    }
}
