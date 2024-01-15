package io.github.ultreon.mods.materialistictools.world.gen.ores;

import com.google.common.annotations.Beta;
import dev.architectury.registry.level.biome.BiomeModifications;
import io.github.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import io.github.ultreon.mods.materialistictools.world.gen.ores.config.OreConfig;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Ore {
    List<Ore> VALUES = new ArrayList<>();

    void register();

    // Getters for values.
    float getHardness();

    // Config / features.
    OreConfig getOreConfig();
    ResourceKey<ConfiguredFeature<?,?>> getConfiguredFeature();

    ResourceKey<PlacedFeature> getPlacedFeature();

    ConfiguredFeature<?, ?> createConfiguredFeature();

    PlacedFeature createPlaceFeature(HolderGetter<ConfiguredFeature<?, ?>> holderGetter);

    // Misc getter for values.
    Block getStoneOre();

    Block getDeepslateOre();

    BlockState getStoneFeatureState();

    BlockState getDeepslateFeatureState();

    @Beta
    Collection<Block> getGroundTypes();

    Predicate<BiomeModifications.BiomeContext> getTagKey();

    float getResistance();

    ToolRequirement getToolRequirement();

    ItemLike getDrop();

    String getName();
}
