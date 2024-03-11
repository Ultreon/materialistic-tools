package io.github.ultreon.mods.materialistictools.forge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import io.github.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ModBlockTagsProvider extends BlockTagsProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    public ModBlockTagsProvider(DataGenerator generatorIn, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn.getPackOutput(), lookupProvider, modId, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.@NotNull Provider p_256380_) {
        for (ItemMaterial metal : ItemMaterial.getValues()) {
            LOGGER.info("Adding tags for item material: " + metal.getRegistryName());
            metal.getOreTag().ifPresent(tag -> {
                RegistrySupplier<Block> stoneOre = metal.getStoneOre();
                IntrinsicTagAppender<Block> tagged = tag(tag);
                if (stoneOre != null) tagged.add(stoneOre.get());
                RegistrySupplier<Block> deepslateOre = metal.getDeepslateOre();
                if (deepslateOre != null) tagged.add(deepslateOre.get());
                RegistrySupplier<Block> netherOre = metal.getNetherOre();
                if (netherOre != null) tagged.add(netherOre.get());
            });
            metal.getStorageBlockTag().ifPresent(tag -> {
                RegistrySupplier<Block> storageBlock = metal.getStorageBlock();
                IntrinsicTagAppender<Block> tagged = tag(tag);
                if (storageBlock != null) tagged.add(storageBlock.get());
            });
            ToolRequirement harvestRequirement = metal.getHarvestRequirement();
            if (harvestRequirement != null) {
                TagKey<Block> tag = harvestRequirement.getTag();
                if (tag != null)
                    tag(tag);
                Optional.ofNullable(metal.getStoneOre()).ifPresent(it -> it.ifPresent(block -> {
                    if (tag != null) tag(tag).add(block);
                    tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                }));
                Optional.ofNullable(metal.getDeepslateOre()).ifPresent(it -> it.ifPresent(block -> {
                    if (tag != null) tag(tag).add(block);
                    tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                }));
                Optional.ofNullable(metal.getNetherOre()).ifPresent(it -> it.ifPresent(block -> {
                    if (tag != null) tag(tag).add(block);
                    tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                }));
                Optional.ofNullable(metal.getStorageBlock()).ifPresent(it -> it.ifPresent(block -> {
                    if (tag != null) tag(tag).add(block);
                    tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                }));
            }
        }

        groupBuilder(Tags.Blocks.ORES, ItemMaterial::getOreTag);
        groupBuilder(Tags.Blocks.STORAGE_BLOCKS, ItemMaterial::getStorageBlockTag);
    }

    private void groupBuilder(TagKey<Block> tag, Function<ItemMaterial, Optional<TagKey<Block>>> tagGetter) {
        TagAppender<Block> builder = tag(tag);
        for (ItemMaterial metal : ItemMaterial.getValues()) tagGetter.apply(metal).ifPresent(builder::addTag);
    }

    @NotNull
    @Override
    public String getName() {
        return "Machinizing - Block Tags";
    }
}
