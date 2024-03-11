package io.github.ultreon.mods.materialistictools.forge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@SuppressWarnings("deprecation")
public class ModItemTagsProvider extends ItemTagsProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    public ModItemTagsProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, modId, existingFileHelper);
    }

    private static TagKey<Item> itemTag(ResourceLocation id) {
        return ItemTags.create(id);
    }

    private static ResourceLocation forgeId(String path) {
        return new ResourceLocation("forge", path);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    protected void addTags(HolderLookup.@NotNull Provider p_256380_) {
        // Empties
        builder(forgeId("nuggets/coal"));
        builder(forgeId("storage_blocks/charcoal"));
        TagAppender<Item> ingots = tag(itemTag(new ResourceLocation(modId, "metal_craftables/ingots")));
        TagAppender<Item> nuggets = tag(itemTag(new ResourceLocation(modId, "metal_craftables/nuggets")));
        TagAppender<Item> rawMaterials = tag(itemTag(new ResourceLocation(modId, "metal_craftables/raw_materials")));

        for (ItemMaterial metal : ItemMaterial.getValues()) {
            metal.getOreTag().ifPresent(tag ->
                    copy(tag, metal.getOreItemTag().get()));
            metal.getStorageBlockTag().ifPresent(tag ->
                    copy(tag, metal.getStorageBlockItemTag().get()));
            metal.getIngotTag().ifPresent(tag -> {
                IntrinsicTagAppender<Item> tagged = tag(tag);
                if (metal.getIngot() != null) {
                    metal.getIngot().ifPresent(item -> {
                        ingots.add(metal.getIngot().get().builtInRegistryHolder().key());
                        tagged.add(item);
                    });
                }
            });
            metal.getNuggetTag().ifPresent(tag -> {
                IntrinsicTagAppender<Item> tagged = tag(tag);
                if (metal.getNugget() != null) {
                    metal.getNugget().ifPresent(item -> {
                        nuggets.add(metal.getNugget().get().builtInRegistryHolder().key());
                        tagged.add(item);
                    });
                }
            });
            metal.getRawMaterialTag().ifPresent(tag -> {
                IntrinsicTagAppender<Item> tagged = tag(tag);
                if (metal.getRawMaterial() != null) {
                    metal.getRawMaterial().ifPresent(item -> {
                        rawMaterials.add(metal.getRawMaterial().get().builtInRegistryHolder().key());
                        tagged.add(item);
                    });
                }
            });
        }

        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        groupBuilder(Tags.Items.INGOTS, ItemMaterial::getIngotTag);
        groupBuilder(Tags.Items.NUGGETS, ItemMaterial::getNuggetTag);
        groupBuilder(Tags.Items.RAW_MATERIALS, ItemMaterial::getRawMaterialTag);

        TagAppender<Item> cookedMeat = tag(itemTag(forgeId("food/cooked_meat")))
                .add(Items.COOKED_BEEF)
                .add(Items.COOKED_PORKCHOP)
                .add(Items.COOKED_CHICKEN)
                .add(Items.COOKED_MUTTON)
                .add(Items.COOKED_RABBIT);

        TagAppender<Item> rawMeat = tag(itemTag(forgeId("food/raw_meat")))
                .add(Items.BEEF)
                .add(Items.PORKCHOP)
                .add(Items.CHICKEN)
                .add(Items.MUTTON)
                .add(Items.RABBIT);
    }

    @SafeVarargs

    private final void groupBuilder(TagKey<Item> tag, Function<ItemMaterial, Optional<TagKey<Item>>> tagGetter, TagKey<Item>... extras) {
        TagBuilder builder = getOrCreateRawBuilder(tag);
        for (ItemMaterial metal : ItemMaterial.getValues()) {
            tagGetter.apply(metal).ifPresent(namedTag -> builder.add(TagEntry.tag(namedTag.location())));
        }
        for (TagKey<Item> extraTag : extras) {
            builder.addTag(extraTag.location());
        }
    }

    private void builder(ResourceLocation id, ItemLike... items) {
        for (Item item : Arrays.stream(items).map(ItemLike::asItem).toArray(Item[]::new)) {
            getOrCreateRawBuilder(itemTag(id)).add(TagEntry.element(Objects.requireNonNull(item.builtInRegistryHolder().key().location(), () -> "Item with not found in registry with class: " + item.getClass().getName())));
        }
    }

    @Override
    public @NotNull
    String getName() {
        return "MaterialisticTools - Item Tags";
    }



//    @SuppressWarnings("ConstantConditions")
//    @Override
//    public void run(@NotNull HashCache cache) {
//        // Temp fix that removes the broken safety check
//        this.builders.clear();
//        this.addTags();
//        this.builders.forEach((p_240524_4_, p_240524_5_) -> {
//            JsonObject jsonobject = p_240524_5_.serializeToJson();
//            Path path = this.getPath(p_240524_4_);
//            if (path == null)
//                return; //Forge: Allow running this data provider without writing it. Recipe provider needs valid tags.
//
//            try {
//                String s = GSON.toJson(jsonobject);
//                String s1 = SHA1.hashUnencodedChars(s).toString();
//                if (!Objects.equals(cache.getHash(path), s1) || !Files.exists(path)) {
//                    Files.createDirectories(path.getParent());
//
//                    try (BufferedWriter bufferedwriter = Files.newBufferedWriter(path)) {
//                        bufferedwriter.write(s);
//                    }
//                }
//
//                cache.putNew(path, s1);
//            } catch (IOException ioexception) {
//                LOGGER.error("Couldn't write tags to {}", path, ioexception);
//            }
//
//        });
//    }

    private static TagKey<Item> itemTag(String path) {
        return ItemTags.create(new ResourceLocation(MaterialisticTools.MOD_ID, path));
    }
}
