package io.github.ultreon.mods.materialistictools.world.gen.ores;

import dev.architectury.registry.level.biome.BiomeModifications;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import io.github.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import io.github.ultreon.mods.materialistictools.world.gen.ores.config.DefaultOreConfig;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceBlockConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.minecraft.tags.BlockTags.NETHER_CARVER_REPLACEABLES;

@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public class ItemMaterialNetherOre extends ItemMaterialOre {
    public ItemMaterialNetherOre(String name, Supplier<ItemMaterial> material, int hardness, ToolRequirement toolRequirement, DefaultOreConfig config) {
        this(name, material, hardness, hardness, toolRequirement, config, BiomeTags.IS_NETHER);
    }

    public ItemMaterialNetherOre(String name, Supplier<ItemMaterial> material, int hardness, int resistance, ToolRequirement toolRequirement, DefaultOreConfig config) {
        this(name, material, hardness, resistance, toolRequirement, config, BiomeTags.IS_NETHER);
    }

    public ItemMaterialNetherOre(String name, Supplier<ItemMaterial> material, int hardness, ToolRequirement toolRequirement, DefaultOreConfig config, TagKey<Biome> biomeTagKey) {
        super(name, material, hardness, toolRequirement, config, biomeTagKey);
    }

    public ItemMaterialNetherOre(String name, Supplier<ItemMaterial> material, int hardness, int resistance, ToolRequirement toolRequirement, DefaultOreConfig config, TagKey<Biome> biomeTagKey) {
        super(name, material, hardness, resistance, toolRequirement, config, biomeTagKey);
    }

    public ItemMaterialNetherOre(String name, Supplier<ItemMaterial> material, int hardness, ToolRequirement toolRequirement, DefaultOreConfig config, Predicate<BiomeModifications.BiomeContext> biomePredicate) {
        super(name, material, hardness, toolRequirement, config, biomePredicate);
    }

    public ItemMaterialNetherOre(String name, Supplier<ItemMaterial> material, int hardness, int resistance, ToolRequirement toolRequirement, DefaultOreConfig config, Predicate<BiomeModifications.BiomeContext> biomePredicate) {
        super(name, material, hardness, resistance, toolRequirement, config, biomePredicate);
    }

    @Override
    public ConfiguredFeature<?, ?> createConfiguredFeature() {
        if (config.getVeinSize() < 2) {
            return new ConfiguredFeature<>(Feature.REPLACE_SINGLE_BLOCK, new ReplaceBlockConfiguration(
                    List.of(OreConfiguration.target(new TagMatchTest(NETHER_CARVER_REPLACEABLES), this.material.get().getNetherOre().get().defaultBlockState()))
            ));
        } else {
            return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
                    List.of(OreConfiguration.target(new TagMatchTest(NETHER_CARVER_REPLACEABLES), this.material.get().getNetherOre().get().defaultBlockState())),
                    config.getVeinSize(),
                    config.getDiscardChanceOnExposure()
            ));
        }
    }
}
