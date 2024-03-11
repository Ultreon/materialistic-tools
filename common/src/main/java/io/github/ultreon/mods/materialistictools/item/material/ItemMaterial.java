package io.github.ultreon.mods.materialistictools.item.material;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.block.BlockWithRequiredToolMat;
import io.github.ultreon.mods.materialistictools.block.OreBlock;
import io.github.ultreon.mods.materialistictools.init.ModBlocks;
import io.github.ultreon.mods.materialistictools.init.ModItems;
import io.github.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import io.github.ultreon.mods.materialistictools.item.tool.Toolset;
import io.github.ultreon.mods.materialistictools.world.gen.ores.ItemMaterialOre;
import io.github.ultreon.mods.materialistictools.world.gen.ores.ModOres;
import io.github.ultreon.mods.materialistictools.world.gen.ores.Ore;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static io.github.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

@SuppressWarnings("unused")
@MethodsReturnNonnullByDefault
public class ItemMaterial implements BaseItemMaterial, Predicate<Holder<Item>> {
    // Class preload.
    private static final List<ItemMaterial> all = new ArrayList<>();
    private static final Map<ResourceLocation, ItemMaterial> map = new HashMap<>();

    // Vanilla stuff
    public static final ItemMaterial GOLD = new ItemMaterial(builder("gold").vanillaMetal(true, true));
    public static final ItemMaterial IRON = new ItemMaterial(builder("iron").vanillaMetal(true, true));
    public static final ItemMaterial COPPER = new ItemMaterial(builder("copper").vanillaMetal(true, false));
    public static final ItemMaterial NETHERITE = new ItemMaterial(builder("netherite").vanillaMetal(false, false));
    public static final ItemMaterial DIAMOND = new ItemMaterial(builder("diamond").vanillaGem(true, true));
    public static final ItemMaterial EMERALD = new ItemMaterial(builder("emerald").vanillaGem(true, true));
    public static final ItemMaterial LAPIS = new ItemMaterial(builder("lapis").vanillaGem(false, false));
    public static final ItemMaterial QUARTZ = new ItemMaterial(builder("quartz").vanillaGem(false, false));
    public static final ItemMaterial AMETHYST = new ItemMaterial(builder("amethyst").vanillaGem(false, false));
    public static final ItemMaterial PRISMARINE = new ItemMaterial(builder("prismarine").vanillaGem(false, false));

    // Metals
    public static final ItemMaterial COMPRESSED_IRON = new ItemMaterial(builder("compressed_iron").ingot());
    public static final ItemMaterial SILVER = new ItemMaterial(overworldOre("silver", ModOres.SILVER));
    public static final ItemMaterial LEAD = new ItemMaterial(overworldOre("lead", ModOres.LEAD));
    public static final ItemMaterial PLATINUM = new ItemMaterial(overworldOre("platinum", ModOres.PLATINUM));
    public static final ItemMaterial ALUMINUM = new ItemMaterial(overworldOre("aluminum", ModOres.BAUXITE), MaterialisticTools.res("bauxite"));
    public static final ItemMaterial URANIUM = new ItemMaterial(overworldOre("uranium", ModOres.URANIUM));
    public static final ItemMaterial ELECTRUM = new ItemMaterial(builderAlloy("electrum", ToolRequirement.STONE));
    public static final ItemMaterial LUMIUM = new ItemMaterial(builderAlloy("lumium", ToolRequirement.STONE));
    public static final ItemMaterial ENDERIUM = new ItemMaterial(builderAlloy("enderium", ToolRequirement.DIAMOND));
    public static final ItemMaterial AURORA_STEEL = new ItemMaterial(builderAlloy("aurora_steel", ToolRequirement.DIAMOND));
    public static final ItemMaterial COBALT = new ItemMaterial(netherOre("cobalt", ModOres.COBALT));
    public static final ItemMaterial ULTRINIUM = new ItemMaterial(overworldOre("ultrinium", ModOres.ULTRINIUM));
    public static final ItemMaterial CHUNK = new ItemMaterial(overworldOre("chunk", ModOres.CHUNK));
    public static final ItemMaterial INFINITY = new ItemMaterial(overworldOre("infinity", ModOres.INFINITY));

    private final ResourceLocation oreRegistryName;
    private final ResourceLocation registryName;

    private final Supplier<Toolset> tools;

    private final Supplier<OreBlock> stoneOreSupplier;
    private final Supplier<OreBlock> deepslateOreSupplier;
    private final Supplier<OreBlock> netherOreSupplier;
    private final Supplier<Block> storageBlockSupplier;
    private final Supplier<Item> rawMaterialSupplier;
    private final Supplier<Item> ingotSupplier;
    private final Supplier<Item> nuggetSupplier;
    private final Supplier<Item> gemSupplier;
    private final TagKey<Block> storageBlockTag;
    private final TagKey<Block> oreTag;
    private final TagKey<Item> storageBlockItemTag;
    private final TagKey<Item> oreItemTag;
    private final TagKey<Item> rawMaterialTag;
    private final TagKey<Item> ingotTag;
    private final TagKey<Item> nuggetTag;
    @Nullable private final ToolRequirement harvestRequirement;
    private final TagKey<Item> gemTag;
    private RegistrySupplier<Block> stoneOre;
    private RegistrySupplier<Block> deepslateOre;
    private RegistrySupplier<Block> netherOre;
    private RegistrySupplier<Block> storageBlock;
    private RegistrySupplier<Item> rawMaterial;
    private RegistrySupplier<Item> dust;
    private RegistrySupplier<Item> ingot;
    private RegistrySupplier<Item> gem;
    private RegistrySupplier<Item> nugget;

    private final Collection<TagKey<Block>> miningTags = new ArrayList<>();

    public ItemMaterial(Builder builder) {
        this(builder, builder.registryName);
    }

    public ItemMaterial(Builder builder, ResourceLocation registryName) {
        this.oreRegistryName = registryName;
        this.registryName = builder.registryName;

        this.storageBlockSupplier = builder.storageBlock;
        this.stoneOreSupplier = builder.stoneOre;
        this.deepslateOreSupplier = builder.deepslateOre;
        this.netherOreSupplier = builder.netherOre;
        this.rawMaterialSupplier = builder.rawMaterial;
        this.ingotSupplier = builder.ingot;
        this.gemSupplier = builder.gem;
        this.nuggetSupplier = builder.nugget;
        this.oreTag = builder.oreTag;
        this.storageBlockTag = builder.storageBlockTag;
        this.oreItemTag = this.oreTag != null ? Builder.itemTag(this.oreTag.location()) : null;
        this.storageBlockItemTag = this.storageBlockTag != null ? Builder.itemTag(this.storageBlockTag.location()) : null;
        this.rawMaterialTag = builder.rawMaterialTag;
        this.ingotTag = builder.ingotTag;
        this.gemTag = builder.gemTag;
        this.nuggetTag = builder.nuggetTag;
        this.harvestRequirement = builder.toolRequirement;
        this.miningTags.addAll(builder.miningTags);

        this.tools = builder.tools;

        map.put(registryName, this);
        all.add(this);
    }

    public static void registerBlocks() {
        for (ItemMaterial metal : getValues()) {
            if (metal.stoneOreSupplier != null) {
                String name = metal.oreRegistryName.getPath() + "_ore";
                metal.stoneOre = ModBlocks.REGISTER.register(name, metal.stoneOreSupplier);
                ModItems.REGISTER.register(name, () ->
                        new BlockItem(metal.stoneOre.get(), new Item.Properties()));
            }
            if (metal.deepslateOreSupplier != null) {
                String name = "deepslate_" + metal.oreRegistryName.getPath() + "_ore";
                metal.deepslateOre = ModBlocks.REGISTER.register(name, metal.deepslateOreSupplier);
                ModItems.REGISTER.register(name, () ->
                        new BlockItem(metal.deepslateOre.get(), new Item.Properties()));
            }
            if (metal.netherOreSupplier != null) {
                String name = "nether_" + metal.oreRegistryName.getPath() + "_ore";
                metal.netherOre = ModBlocks.REGISTER.register(name, metal.netherOreSupplier);
                ModItems.REGISTER.register(name, () ->
                        new BlockItem(metal.netherOre.get(), new Item.Properties()));
            }
            if (metal.storageBlockSupplier != null) {
                String name = metal.registryName.getPath() + "_block";
                metal.storageBlock = ModBlocks.REGISTER.register(name, metal.storageBlockSupplier);
                ModItems.REGISTER.register(name, () ->
                        new BlockItem(metal.storageBlock.get(), new Item.Properties()));
            }
        }
    }

    public static void registerItems() {
        for (ItemMaterial metal : getValues()) {
            if (metal.rawMaterialSupplier != null) {
                String[] split = metal.oreRegistryName.getPath().split("/");
                split[split.length - 1] = "raw_" + split[split.length - 1];
                metal.rawMaterial = ModItems.REGISTER.register(
                        String.join("", split), metal.rawMaterialSupplier);
            }
            if (metal.ingotSupplier != null) {
                metal.ingot = ModItems.REGISTER.register(
                        metal.registryName.getPath() + "_ingot", metal.ingotSupplier);
            }
            if (metal.gemSupplier != null) {
                metal.gem = ModItems.REGISTER.register(
                        metal.registryName.getPath(), metal.gemSupplier);
            }
            if (metal.nuggetSupplier != null) {
                metal.nugget = ModItems.REGISTER.register(
                        metal.registryName.getPath() + "_nugget", metal.nuggetSupplier);
            }
        }
    }

    private static Builder builder(String name) {
        return new Builder(name);
    }

    /**
     * Creates base builder with ore.
     *
     * @param name name of the material.
     * @param ore ore object.
     * @return the item material builder.
     */
    public static Builder overworldOre(String name, Ore ore) {
        return builder(name).toolRequirement(ore.getToolRequirement()).rawMaterial(ore).storageBlock(ore.getToolRequirement()).overworldOre(ore).ingot().nugvalue();
    }

    /**
     * Creates base builder with ore.
     *
     * @param name name of the material.
     * @param ore ore object.
     * @return the item material builder.
     */
    public static Builder netherOre(String name, Ore ore) {
        return builder(name).toolRequirement(ore.getToolRequirement()).storageBlock(ore.getToolRequirement()).netherOre(ore).rawMaterial(ore).ingot().nugvalue();
    }

    /**
     * Creates alloy material builder.
     *
     * @param name name of the material.
     * @param toolRequirement the material's harvest level.
     * @return the item material builder.
     */
    public static Builder builderAlloy(String name, ToolRequirement toolRequirement) {
        return builder(name).storageBlock(toolRequirement).ingot().nugvalue();
    }

    /**
     * Creates gem material builder.
     *
     * @deprecated replaced by {@link #builderGem(String, ItemMaterialOre)}.
     * @param name name of the material.
     * @param ore the ore object for the gem.
     * @param harvestLevel the material's harvest level.
     * @return the item material builder.
     */
    @SuppressWarnings("unused")
    @Deprecated
    public static Builder builderGem(String name, ItemMaterialOre ore, @Deprecated int harvestLevel) {
        return builderGem(name, ore);
    }

    /**
     * Creates gem material builder.
     *
     * @param name name of the material.
     * @param ore the ore object for the gem.
     * @return the item material builder.
     */
    public static Builder builderGem(String name, ItemMaterialOre ore) {
        return builder(name).storageBlock(ore.getToolRequirement()).overworldOre(ore).gem();
    }

    /**
     * Get the toolset bound to this material.
     *
     * @return toolset instance.
     */
    @Nullable
    public Toolset getToolset() {
        return this.tools.get();
    }

    /**
     * Get the material's name.
     *
     * @return resource path name for the item material.
     */
    @Deprecated(forRemoval = true)
    @Override
    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    /**
     * Get the stone ore block.
     *
     * @return optional value for the stone ore block.
     */
    @Override
    public @Nullable RegistrySupplier<Block> getStoneOre() {
        return this.stoneOre;
    }

    /**
     * Get the deepslate ore block.
     *
     * @return optional value for the deepslate ore block.
     */
    @Override
    public @Nullable RegistrySupplier<Block> getDeepslateOre() {
        return this.deepslateOre;
    }

    /**
     * Get the nether ore block.
     *
     * @return optional value for the nether ore block.
     */
    @Override
    public @Nullable RegistrySupplier<Block> getNetherOre() {
        return this.netherOre;
    }

    /**
     * Get the storage block.
     *
     * @return optional value for storage block.
     */
    @Override
    public @Nullable RegistrySupplier<Block> getStorageBlock() {
        return this.storageBlock;
    }

    /**
     * Get the ore chunks item.
     *
     * @return optional value for the ore chunks item.
     */
    @Override
    public @Nullable RegistrySupplier<Item> getRawMaterial() {
        return this.rawMaterial;
    }

    /**
     * Get the ingot item.
     *
     * @return optional value for the ingot item.
     */
    @Override
    public @Nullable RegistrySupplier<Item> getIngot() {
        return this.ingot;
    }

    /**
     * Get the gem item.
     *
     * @return optional value for the gem item.
     */
    @Override
    public @Nullable RegistrySupplier<Item> getGem() {
        return this.gem;
    }

    /**
     * Get the nugget item.
     *
     * @return optional value for the nugget item.
     */
    @Override
    public @Nullable RegistrySupplier<Item> getNugget() {
        return this.nugget;
    }

    /**
     * Get the ore tag.
     *
     * @return optional value for the ore block tag.
     */
    @Override
    public Optional<TagKey<Block>> getOreTag() {
        return Optional.ofNullable(this.oreTag);
    }

    /**
     * Get the storage block tag.
     *
     * @return optional value for the storage block tag.
     */
    @Override
    public Optional<TagKey<Block>> getStorageBlockTag() {
        return Optional.ofNullable(this.storageBlockTag);
    }

    /**
     * Get the ore item tag.
     *
     * @return optional value for the ore item tag.
     */
    @Override
    public Optional<TagKey<Item>> getOreItemTag() {
        return Optional.ofNullable(this.oreItemTag);
    }

    /**
     * Get the storage block item tag.
     *
     * @return optional value for the storage block item tag.
     */
    @Override
    public Optional<TagKey<Item>> getStorageBlockItemTag() {
        return Optional.ofNullable(this.storageBlockItemTag);
    }

    /**
     * Get the chunks tag.
     *
     * @return optional value for the ore chunks item tag.
     */
    @Override
    public Optional<TagKey<Item>> getRawMaterialTag() {
        return Optional.ofNullable(this.rawMaterialTag);
    }

    /**
     * Get the ingot tag.
     *
     * @return optional value for the ingot item tag.
     */
    @Override
    public Optional<TagKey<Item>> getIngotTag() {
        return Optional.ofNullable(this.ingotTag);
    }

    /**
     * Get the gem tag.
     *
     * @return optional value for the gem item tag.
     */
    @Override
    public Optional<TagKey<Item>> getGemTag() {
        return Optional.ofNullable(this.gemTag);
    }

    /**
     * Get the nugget tag.
     *
     * @return optional value for the nugget item tag.
     */
    @Override
    public Optional<TagKey<Item>> getNuggetTag() {
        return Optional.ofNullable(this.nuggetTag);
    }

    public @Nullable ToolRequirement getHarvestRequirement() {
        return this.harvestRequirement;
    }

    @Deprecated(forRemoval = true)
    public String name() {
        return this.registryName.toString();
    }

    public ResourceLocation getRegistryName() {
        return this.registryName;
    }

    @Deprecated(forRemoval = true)
    public static ItemMaterial[] values() {
        return all.toArray(new ItemMaterial[0]);
    }

    public static Collection<ItemMaterial> getValues() {
        return map.values();
    }

    @Deprecated(forRemoval = true)
    public static ItemMaterial fromIndex(int index) {
        return all.get(index);
    }

    @Deprecated(forRemoval = true)
    public static ItemMaterial fromName(String name) {
        return map.get(new ResourceLocation(name));
    }

    public static ItemMaterial fromName(ResourceLocation name) {
        return map.get(name);
    }

    public Collection<TagKey<Block>> getMiningTags() {
        return Collections.unmodifiableCollection(this.miningTags);
    }

    @Override
    public boolean test(Holder<Item> itemHolder) {
        return (this.ingot != null && itemHolder.value() == this.ingot.orElse(null))
                || (this.nugget != null && itemHolder.value() == this.nugget.orElse(null))
                || (this.rawMaterial != null && itemHolder.value() == this.rawMaterial.orElse(null))
                || (this.gem != null && itemHolder.value() == this.gem.orElse(null))
                || (this.storageBlock != null && itemHolder.value() == this.storageBlock.toOptional().map(Block::asItem).orElse(null))
                || (this.stoneOre != null && itemHolder.value() == this.stoneOre.toOptional().map(Block::asItem).orElse(null))
                || (this.netherOre != null && itemHolder.value() == this.netherOre.toOptional().map(Block::asItem).orElse(null))
                || (this.deepslateOre != null && itemHolder.value() == this.deepslateOre.toOptional().map(Block::asItem).orElse(null))
                ;
    }

    /**
     * @author XyperCode
     */
    public static class Builder {
        private final ResourceLocation registryName;
        private Supplier<OreBlock> stoneOre;
        private Supplier<OreBlock> deepslateOre;
        private Supplier<OreBlock> netherOre;
        private Supplier<Block> storageBlock;
        private Supplier<Item> rawMaterial;
        private Supplier<Item> ingot;
        private Supplier<Item> gem;
        private Supplier<Item> nugget;
        private TagKey<Block> oreTag;
        private TagKey<Block> storageBlockTag;
        private TagKey<Item> rawMaterialTag;
        private TagKey<Item> ingotTag;
        private TagKey<Item> gemTag;
        private TagKey<Item> nuggetTag;
        private ToolRequirement toolRequirement;

        private Supplier<Toolset> tools;
        private final List<TagKey<Block>> miningTags = new ArrayList<>();

        /**
         * @param name name of the item material.
         */
        private Builder(String name) {
            this.registryName = new ResourceLocation(MOD_ID, name);
        }

        public static TagKey<Block> blockTag(String path) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation("forge", path));
        }

        public static TagKey<Item> itemTag(String path) {
            return TagKey.create(Registries.ITEM, new ResourceLocation("forge", path));
        }

        public static TagKey<Item> itemTag(ResourceLocation tag) {
            return TagKey.create(Registries.ITEM, tag);
        }

        public Builder overworldOre(Ore ore) {
            this.stoneOre = () -> new OreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(ore.getHardness(), ore.getResistance())
                    .sound(SoundType.STONE), ore);
            this.deepslateOre = () -> new OreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(ore.getHardness(), ore.getResistance())
                    .sound(SoundType.DEEPSLATE), ore);
            this.miningTags.add(BlockTags.MINEABLE_WITH_PICKAXE);
            this.miningTags.add(ore.getToolRequirement().getTag());
            this.toolRequirement = ore.getToolRequirement();
            if (this.oreTag == null) {
                this.oreTag = blockTag("ores/" + ore.getName());
            }
            return this;
        }

        public Builder netherOre(Ore ore) {
            this.netherOre = () -> new OreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .requiresCorrectToolForDrops()
                    .strength(ore.getHardness(), ore.getResistance())
                    .sound(SoundType.NETHERRACK), ore);
            this.miningTags.add(BlockTags.MINEABLE_WITH_PICKAXE);
            this.miningTags.add(ore.getToolRequirement().getTag());
            this.toolRequirement = ore.getToolRequirement();
            if (this.oreTag == null) {
                this.oreTag = blockTag("ores/" + this.registryName.getPath());
            }
            return this;
        }

        public Builder tools(Supplier<Toolset> tools) {
            this.tools = tools;
            return this;
        }

        public Builder storageBlock(ToolRequirement tool) {
            this.storageBlock = () -> new BlockWithRequiredToolMat(tool);
            this.storageBlockTag = blockTag("storage_blocks/" + this.registryName.getPath());
            this.toolRequirement = tool;
            return this;
        }

        public Builder vanillaGem(boolean storageBlock, boolean ore) {
            if (ore) this.oreTag = blockTag("ores/" + this.registryName.getPath());
            if (storageBlock) this.storageBlockTag = blockTag("storage_block/" + this.registryName.getPath());
            this.gemTag = itemTag("gems/" + this.registryName.getPath());
            return this;
        }

        public Builder vanillaGemWithoutBlocks() {
            this.gemTag = itemTag("gems/" + this.registryName.getPath());
            return this;
        }

        public Builder vanillaMetal(boolean rawMaterial, boolean nugget) {
            if (rawMaterial) this.oreTag = blockTag("ores/" + this.registryName.getPath());
            this.storageBlockTag = blockTag("storage_block/" + this.registryName.getPath());
            this.ingotTag = itemTag("ingots/" + this.registryName.getPath());
            if (nugget) this.nuggetTag = itemTag("nuggets/" + this.registryName.getPath());
            if (rawMaterial) this.rawMaterialTag = itemTag("raw_material/" + this.registryName.getPath());
            return this;
        }

        public Builder vanillaMetalWithoutNugvalue() {
            this.oreTag = blockTag("ores/" + this.registryName.getPath());
            this.storageBlockTag = blockTag("storage_block/" + this.registryName.getPath());
            this.ingotTag = itemTag("ingots/" + this.registryName.getPath());
            return this;
        }

        public Builder rawMaterial(Ore ore) {
            this.rawMaterial = () -> new Item(new Item.Properties());
            this.rawMaterialTag = itemTag("raw_material/" + ore.getName());
            return this;
        }

        public Builder ingot() {
            this.ingot = () -> new Item(new Item.Properties());
            this.ingotTag = itemTag("ingots/" + this.registryName.getPath());
            return this;
        }

        public Builder nugvalue() {
            this.nugget = () -> new Item(new Item.Properties());
            this.nuggetTag = itemTag("nuggets/" + this.registryName.getPath());
            return this;
        }

        public Builder gem() {
            this.gem = () -> new Item(new Item.Properties());
            this.gemTag = itemTag("gems/" + this.registryName.getPath());
            return this;
        }

        public Builder toolRequirement(ToolRequirement toolRequirement) {
            this.toolRequirement = toolRequirement;
            return this;
        }
    }
}
