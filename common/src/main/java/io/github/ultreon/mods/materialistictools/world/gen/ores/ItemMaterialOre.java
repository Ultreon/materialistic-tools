package io.github.ultreon.mods.materialistictools.world.gen.ores;

import dev.architectury.hooks.level.biome.GenerationProperties;
import dev.architectury.registry.level.biome.BiomeModifications;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.util.InvalidNameException;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceBlockConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import io.github.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import io.github.ultreon.mods.materialistictools.world.gen.ores.config.DefaultOreConfig;
import io.github.ultreon.mods.materialistictools.world.gen.ores.config.OreConfig;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static net.minecraft.tags.BlockTags.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.tags.BlockTags.STONE_ORE_REPLACEABLES;

/**
 * Handles ore blocks and default ore configs
 */
@MethodsReturnNonnullByDefault
public class ItemMaterialOre implements Ore {
    protected final Supplier<ItemMaterial> material;
    protected final DefaultOreConfig config;
    private final int hardness;
    private final int resistance;
    protected ResourceKey<PlacedFeature> placedFeature;

    private final String name;
    private final Map<Block, Block> oreGroundTypeMap = new HashMap<>();
    private final ToolRequirement toolRequirement;
    private final Predicate<BiomeModifications.BiomeContext> biomePredicate;
    protected ResourceKey<ConfiguredFeature<?, ?>> cfKey;
    protected ResourceKey<PlacedFeature> pfKey;

    public ItemMaterialOre(String name, Supplier<ItemMaterial> material, int hardness, ToolRequirement toolRequirement, DefaultOreConfig config) {
        this(name, material, hardness, hardness, toolRequirement, config, BiomeTags.IS_OVERWORLD);
    }

    public ItemMaterialOre(String name, Supplier<ItemMaterial> material, int hardness, int resistance, ToolRequirement toolRequirement, DefaultOreConfig config) {
        this(name, material, hardness, resistance, toolRequirement, config, BiomeTags.IS_OVERWORLD);
    }

    public ItemMaterialOre(String name, Supplier<ItemMaterial> material, int hardness, ToolRequirement toolRequirement, DefaultOreConfig config, TagKey<Biome> biomeTagKey) {
        this(name, material, hardness, hardness, toolRequirement, config, getter -> getter.hasTag(biomeTagKey));
    }

    public ItemMaterialOre(String name, Supplier<ItemMaterial> material, int hardness, int resistance, ToolRequirement toolRequirement, DefaultOreConfig config, TagKey<Biome> biomeTagKey) {
        this(name, material, hardness, resistance, toolRequirement, config, getter -> getter.hasTag(biomeTagKey));
    }

    public ItemMaterialOre(String name, Supplier<ItemMaterial> material, int hardness, ToolRequirement toolRequirement, DefaultOreConfig config, Predicate<BiomeModifications.BiomeContext> biomePredicate) {
        this(name, material, hardness, hardness, toolRequirement, config, biomePredicate);
    }

    public ItemMaterialOre(String name, Supplier<ItemMaterial> material, int hardness, int resistance, ToolRequirement toolRequirement, DefaultOreConfig config, Predicate<BiomeModifications.BiomeContext> biomePredicate) {
        this.material = material;
        this.config = config;
        this.hardness = hardness;
        this.resistance = resistance;
        this.toolRequirement = toolRequirement;
        this.biomePredicate = biomePredicate;

        // Enum backports.
        VALUES.add(this);
        this.name = name;

        if (!Pattern.compile("[a-z0-9_]").matcher(name).find()) {
            throw new InvalidNameException("Ore name is invalid.");
        }
    }

    @Deprecated
    public String name() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    @Override
    public ConfiguredFeature<?, ?> createConfiguredFeature() {
        if (config.getVeinSize() < 2) {
            return new ConfiguredFeature<>(Feature.REPLACE_SINGLE_BLOCK, new ReplaceBlockConfiguration(
                    List.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), this.material.get().getStoneOre().get().defaultBlockState()),
                            OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), this.material.get().getDeepslateOre().get().defaultBlockState())
                    )
            ));
        }
        return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), this.material.get().getStoneOre().get().defaultBlockState()),
                        OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), this.material.get().getDeepslateOre().get().defaultBlockState())
                ),
                config.getVeinSize(),
                config.getDiscardChanceOnExposure()
        ));
    }

    @Override
    public PlacedFeature createPlaceFeature(HolderGetter<ConfiguredFeature<?, ?>> holderGetter) {
        return new PlacedFeature(holderGetter.getOrThrow(this.cfKey),
                List.of(CountPlacement.of(ConstantInt.of(config.getVeinCount())),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(config.getMinHeight()), VerticalAnchor.absolute(config.getMaxHeight()))),
                        RarityFilter.onAverageOnceEvery(config.getSpawnChance())));
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public void register() {
        this.cfKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, MaterialisticTools.res(getName() + "_ore"));
        this.pfKey = ResourceKey.create(Registries.PLACED_FEATURE, MaterialisticTools.res("placed_" + getName() + "_ore"));

        BiomeModifications.addProperties((biomeContext, mutable) -> {
            GenerationProperties.Mutable generationProperties = mutable.getGenerationProperties();
            if (this.biomePredicate.test(biomeContext)) {
                generationProperties.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, pfKey);
            }
        });
    }

    @Override
    public float getHardness() {
        return this.hardness;
    }

    @Override
    public float getResistance() {
        return this.resistance;
    }

    @Override
    public OreConfig getOreConfig() {
        return this.config;
    }

    @Override
    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature() {
        return this.cfKey;
    }

    @Override
    public ResourceKey<PlacedFeature> getPlacedFeature() {
        return this.pfKey;
    }

    @Override
    public Block getStoneOre() {
        return this.material.get().getStoneOre().get();
    }

    @Override
    public Block getDeepslateOre() {
        return this.material.get().getDeepslateOre().get();
    }

    @Override
    public BlockState getStoneFeatureState() {
        return this.getStoneOre().defaultBlockState();
    }

    @Override
    public BlockState getDeepslateFeatureState() {
        return this.getDeepslateOre().defaultBlockState();
    }

    @Override
    public Collection<Block> getGroundTypes() {
        return this.oreGroundTypeMap.values();
    }

    @Override
    public Predicate<BiomeModifications.BiomeContext> getTagKey() {
        return this.biomePredicate;
    }

    @Override
    public ToolRequirement getToolRequirement() {
        return this.toolRequirement;
    }

    @Override
    public ItemLike getDrop() {
        return this.material.get().getRawMaterial().get();
    }

    public void dataGen() {
        this.cfKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, MaterialisticTools.res("ore_" + getName()));
        this.pfKey = ResourceKey.create(Registries.PLACED_FEATURE, MaterialisticTools.res("ore_" + getName()));
    }
}
