package io.github.ultreon.mods.materialistictools.item.tool;

import com.mojang.datafixers.util.Either;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.debug.Debugger;
import io.github.ultreon.mods.materialistictools.init.ModItems;
import io.github.ultreon.mods.materialistictools.init.ModTiers;
import io.github.ultreon.mods.materialistictools.init.tags.ModItemTags;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import io.github.ultreon.mods.materialistictools.item.tool.types.*;
import io.github.ultreon.mods.materialistictools.util.FeatureStatus;
import io.github.ultreon.mods.materialistictools.util.item.ArmorMaterialBuilder;
import io.github.ultreon.mods.materialistictools.util.item.ItemTierBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static io.github.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "Convert2MethodRef"})
public enum Toolset implements Predicate<Holder<Item>> {
    COPPER(builder("copper")
            .material(() -> Either.right(ItemMaterial.COPPER.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":copper")
                    .maxDamageFactor(13)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(10)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(() -> Ingredient.of(Items.COPPER_INGOT))
                    .build())
            .tools(() -> new ItemTierBuilder()
                    .tier(2).maxUses(220).efficiency(5F).attackDamage(1.4F).enchantability(11)
                    .repairMaterial(() -> Ingredient.of(Items.COPPER_INGOT)).build())),
    SILVER(builder("silver")
            .material(() -> Either.right(ItemMaterial.SILVER.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().all(ModTraits.HOLY.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":silver")
                    .maxDamageFactor(15)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(48)
                    .knockbackResistance(1F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.SILVER.getIngotTag().get()))
                    .build())
            .tools(() -> TraitPack.create().all(ModTraits.HOLY.get()).build(), () -> new ItemTierBuilder()
                    .tier(2).maxUses(580).efficiency(5.5F).attackDamage(1.7F).enchantability(48)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.SILVER.getIngotTag().orElseThrow(() -> new NullPointerException("Silver ingot not found in OreMaterial class.")))).build())),
    LEAD(builder("lead")
            .material(() -> Either.right(ItemMaterial.LEAD.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().all(ModTraits.POISON.get(), ModTraits.SHARP.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":lead")
                    .maxDamageFactor(14)
                    .damageReduction(new int[]{4, 9, 12, 7})
                    .enchantability(4)
                    .knockbackResistance(1F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(4F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.LEAD.getIngotTag().get()))
                    .build())
            .tools(() -> TraitPack.create().all(ModTraits.POISON.get(), ModTraits.SHARP.get()).build(), () -> new ItemTierBuilder()
                    .tier(3).maxUses(450).efficiency(8F).attackDamage(3F).enchantability(7)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.LEAD.getIngotTag().orElseThrow(() -> new NullPointerException("Lead ingot not found in OreMaterial class.")))).build())),
    PLATINUM(builder("platinum")
            .material(() -> Either.right(ItemMaterial.PLATINUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":platinum")
                    .maxDamageFactor(36)
                    .damageReduction(new int[]{3, 6, 8, 3})
                    .enchantability(14)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.PLATINUM.getIngotTag().get()))
                    .build())
            .tools(() -> new ItemTierBuilder()
                    .tier(4).maxUses(1240).efficiency(7F).attackDamage(5F).enchantability(14)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.PLATINUM.getIngotTag().orElseThrow(() -> new NullPointerException("Platinum ingot not found in OreMaterial class.")))).build())),
    ALUMINUM(builder("aluminum")
            .material(() -> Either.right(ItemMaterial.ALUMINUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":aluminum")
                    .maxDamageFactor(13)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(10)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.ALUMINUM.getIngotTag().get()))
                    .build())
            .tools(() -> TraitPack.create().all(ModTraits.SHARP.get()).build(), () -> new ItemTierBuilder()
                    .tier(2).maxUses(180).efficiency(9F).attackDamage(3F).enchantability(9)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.ALUMINUM.getIngotTag().orElseThrow(() -> new NullPointerException("Aluminum ingot not found in OreMaterial class.")))).build())),
    URANIUM(builder("uranium")
            .material(() -> Either.right(ItemMaterial.URANIUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().all(ModTraits.RADIOACTIVE.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":uranium")
                    .maxDamageFactor(14)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(10)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.URANIUM.getIngotTag().get()))
                    .build())
            .tools(() -> TraitPack.create().all(ModTraits.RADIOACTIVE.get()).build(), () -> new ItemTierBuilder()
                    .tier(3).maxUses(220).efficiency(5.3F).attackDamage(1.4F).enchantability(11)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.URANIUM.getIngotTag().orElseThrow(() -> new NullPointerException("Uranium ingot not found in OreMaterial class.")))).build())),
    ELECTRUM(builder("electrum", FeatureStatus.WIP)
            .material(() -> Either.right(ItemMaterial.ELECTRUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":electrum")
                    .maxDamageFactor(14)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(11)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.ELECTRUM.getIngotTag().get()))
                    .build())
            .tools(() -> new ItemTierBuilder()
                    .tier(1).maxUses(283).efficiency(4.5F).attackDamage(2F).enchantability(11)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.ELECTRUM.getIngotTag().orElseThrow(() -> new NullPointerException("Electrum ingot not found in OreMaterial class.")))).build())),
    LUMIUM(builder("lumium", FeatureStatus.WIP)
            .material(() -> Either.right(ItemMaterial.LUMIUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":lumium")
                    .maxDamageFactor(9)
                    .damageReduction(new int[]{2, 5, 6, 2})
                    .enchantability(36)
                    .knockbackResistance(0F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(0F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.LUMIUM.getIngotTag().get()))
                    .build())
            .tools(() -> new ItemTierBuilder()
                    .tier(2).maxUses(200).efficiency(5F).attackDamage(2.7F).enchantability(36)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.LUMIUM.getIngotTag().orElseThrow(() -> new NullPointerException("Lumium ingot not found in OreMaterial class.")))).build())),
    ENDERIUM(builder("enderium")
            .material(() -> Either.right(ItemMaterial.ENDERIUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.ENDER.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":enderium")
                    .maxDamageFactor(42)
                    .damageReduction(new int[]{4, 10, 12, 3})
                    .enchantability(56)
                    .knockbackResistance(.2F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(4F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.ENDERIUM.getIngotTag().get()))
                    .build())
            .tools(() -> TraitPack.create().tools(ModTraits.ENDER.get()).build(), () -> new ItemTierBuilder()
                    .tier(2).maxUses(2340).efficiency(16F).attackDamage(7F).enchantability(56)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.ENDERIUM.getIngotTag().orElseThrow(() -> new NullPointerException("Enderium ingot not found in OreMaterial class.")))).build())),
    AURORA_STEEL(builder("aurora_steel")
            .material(() -> Either.right(ItemMaterial.AURORA_STEEL.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.REFLECTIVE_AURA.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":aurora_steel")
                    .maxDamageFactor(42)
                    .damageReduction(new int[]{4, 10, 12, 3})
                    .enchantability(56)
                    .knockbackResistance(.2F)
                    .sound(SoundEvents.ARMOR_EQUIP_IRON)
                    .toughness(4F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.ENDERIUM.getIngotTag().get()))
                    .build())
            .tools(() -> TraitPack.create().tools(ModTraits.AURORA_SHIELD.get()).armor(ModTraits.REFLECTIVE_AURA.get()).build(), () -> new ItemTierBuilder()
                    .tier(2).maxUses(2184).efficiency(17F).attackDamage(8F).enchantability(65)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.AURORA_STEEL.getIngotTag().orElseThrow(() -> new NullPointerException("Enderium ingot not found in OreMaterial class.")))).build())),
    OBSIDIAN(builder("obsidian")
            .material(() -> Either.right(ModItemTags.OBSIDIAN), () -> Items.STICK, () -> Either.left(ModItems.FIRE_GEM.get()))
            .armor(() -> TraitPack.create().armor(ModTraits.BLAST_RESISTANT.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":obsidian")
                    .maxDamageFactor(119)
                    .damageReduction(new int[]{4, 8, 10, 6})
                    .enchantability(17)
                    .knockbackResistance(.2F)
                    .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                    .toughness(4F)
                    .repairMaterial(() -> Ingredient.of(Items.OBSIDIAN))
                    .build())
            .tools(() -> TraitPack.create().all(ModTraits.BLAZE.get()).build(), () -> new ItemTierBuilder()
                    .tier(4).maxUses(3658).efficiency(18F).attackDamage(3F).enchantability(17)
                    .repairMaterial(() -> Ingredient.of(Items.OBSIDIAN)).build())),
    COBALT(builder("cobalt", FeatureStatus.WIP)
            .material(() -> Either.right(ItemMaterial.COBALT.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.MAGIC_RESISTANT.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":cobalt")
                    .maxDamageFactor(64)
                    .damageReduction(new int[]{28, 48, 56, 35})
                    .enchantability(24)
                    .knockbackResistance(.2F)
                    .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                    .toughness(6F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.COBALT.getIngotTag().get()))
                    .build())
            .tools(() -> ModTiers.COBALT)),
    ULTRINIUM(builder("ultrinium")
            .material(() -> Either.right(ItemMaterial.ULTRINIUM.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.BLAST_RESISTANT.get(), ModTraits.MAGIC_RESISTANT.get(), ModTraits.FIRE_RESISTANT.get(), ModTraits.PROJECTILE_RESISTANT.get(), ModTraits.HOLY.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":ultrinium")
                    .maxDamageFactor(1014)
                    .damageReduction(new int[]{125, 200, 275, 165})
                    .enchantability(224)
                    .knockbackResistance(1F)
                    .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                    .toughness(64F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.ULTRINIUM.getIngotTag().get()))
                    .build())
            .tools(() -> TraitPack.create().tools(ModTraits.SHARP.get(), ModTraits.WITHER.get(), ModTraits.POISON.get()).build(), () -> ModTiers.ULTRINIUM)),
    CHUNK(builder("chunk")
            .material(() -> Either.right(ItemMaterial.CHUNK.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.BLAST_RESISTANT.get(), ModTraits.MAGIC_RESISTANT.get(), ModTraits.FIRE_RESISTANT.get(), ModTraits.PROJECTILE_RESISTANT.get(), ModTraits.CHUNKY.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":chunk")
                    .maxDamageFactor(14985)
                    .damageReduction(new int[]{500, 800, 700, 400})
                    .enchantability(907)
                    .knockbackResistance(2F)
                    .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                    .toughness(256F)
                    .repairMaterial(() -> Ingredient.of(ItemMaterial.CHUNK.getIngotTag().get()))
                    .build())
            .tools(() -> TraitPack.create().tools(ModTraits.EXPLOSIVE.get(), ModTraits.EMPOWERED.get()).build(), () -> ModTiers.CHUNK)),
    INFINITY(builder("infinity")
            .material(() -> Either.right(ItemMaterial.INFINITY.getIngotTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(
                    ModTraits.BLAST_RESISTANT.get(), ModTraits.MAGIC_RESISTANT.get(),
                    ModTraits.FIRE_RESISTANT.get(), ModTraits.PROJECTILE_RESISTANT.get(),
                    ModTraits.HOLY.get(), ModTraits.INFINITY.get(), ModTraits.POISON.get(),
                    ModTraits.WITHER.get(), ModTraits.SHARP.get(), ModTraits.ENDER.get(),
                    ModTraits.RADIOACTIVE.get()).build(),
                    () -> new ArmorMaterialBuilder()
                            .name(MOD_ID + ":infinity")
                            .maxDamageFactor((int) Float.POSITIVE_INFINITY)
                            .damageReduction(new int[]{(int) Float.POSITIVE_INFINITY, (int) Float.POSITIVE_INFINITY, (int) Float.POSITIVE_INFINITY, (int) Float.POSITIVE_INFINITY})
                            .enchantability((int) Float.POSITIVE_INFINITY)
                            .knockbackResistance(Float.POSITIVE_INFINITY)
                            .sound(SoundEvents.ARMOR_EQUIP_NETHERITE)
                            .toughness(Float.POSITIVE_INFINITY)
                            .repairMaterial(() -> Ingredient.of(ItemMaterial.INFINITY.getIngotTag().get()))
                            .build())
            .tools(() -> TraitPack.create().all(
                    ModTraits.INFINITY.get(), ModTraits.POISON.get(),
                    ModTraits.WITHER.get(), ModTraits.SHARP.get(),
                    ModTraits.ENDER.get(), ModTraits.BLAZE.get(),
                    ModTraits.RADIOACTIVE.get()).build(), () -> ModTiers.INFINITY)),
    AMETHYST(builder("amethyst")
            .material(() -> Either.right(ItemMaterial.AMETHYST.getGemTag().orElseThrow()), () -> Items.STICK)
            .armor(() -> TraitPack.create().armor(ModTraits.MAGIC_RESISTANT.get()).build(), () -> new ArmorMaterialBuilder()
                    .name(MOD_ID + ":amethyst")
                    .maxDamageFactor(21)
                    .damageReduction(new int[]{4, 9, 11, 5})
                    .enchantability(38)
                    .knockbackResistance(0)
                    .sound(SoundEvents.ARMOR_EQUIP_DIAMOND)
                    .toughness(0)
                    .repairMaterial(() -> Ingredient.of(Items.AMETHYST_SHARD))
                    .build())
            .tools(() -> new ItemTierBuilder()
                    .tier(7).maxUses(226).efficiency(6.5F).attackDamage(3.8F).enchantability(30)
                    .repairMaterial(() -> Ingredient.of(Items.AMETHYST_SHARD)).build())),
    ;
    
    private final String toolName;

    private final Supplier<Either<Item, TagKey<Item>>> baseMaterial;
    private final Supplier<Item> handleMaterial;
    @Nullable
    private final Supplier<Either<Item, TagKey<Item>>> element;

    private final Supplier<ArmorMaterial> armorMaterial;
    private final Supplier<Tier> itemTier;

    private final Supplier<ArmorItem> helmetSupplier;
    private final Supplier<ArmorItem> chestplateSupplier;
    private final Supplier<ArmorItem> leggingsSupplier;
    private final Supplier<ArmorItem> bootsSupplier;
    private final Supplier<SwordItem> swordSupplier;
    private final Supplier<AxeItem> axeSupplier;
    private final Supplier<PickaxeItem> pickaxeSupplier;
    private final Supplier<ShovelItem> shovelSupplier;
    private final Supplier<HoeItem> hoeSupplier;

    private final FeatureStatus status;

    private RegistrySupplier<ArmorItem> helmet;
    private RegistrySupplier<ArmorItem> chestplate;
    private RegistrySupplier<ArmorItem> leggings;
    private RegistrySupplier<ArmorItem> boots;
    private RegistrySupplier<SwordItem> sword;
    private RegistrySupplier<AxeItem> axe;
    private RegistrySupplier<PickaxeItem> pickaxe;
    private RegistrySupplier<ShovelItem> shovel;
    private RegistrySupplier<HoeItem> hoe;

    Toolset(Builder builder) {
        this(builder, builder.name);
    }

    Toolset(Builder builder, String toolName) {
        if (!builder.name.equals(this.getName())) {
            throw new IllegalArgumentException("Builder name is incorrect, should be " + this.getName());
        }
        this.status = builder.status;
        this.toolName = toolName;
        Debugger.log(toolName + "{<class>:<init>[1].0}: " + builder.baseMaterial);
        this.baseMaterial = builder.baseMaterial;
        this.handleMaterial = builder.handleMaterial;
        this.element = builder.element;

        this.armorMaterial = builder.armorMaterial;
        this.itemTier = builder.itemTier;

        this.helmetSupplier = builder.helmet;
        this.chestplateSupplier = builder.chestplate;
        this.leggingsSupplier = builder.leggings;
        this.bootsSupplier = builder.boots;
        this.swordSupplier = builder.sword;
        this.axeSupplier = builder.axe;
        this.pickaxeSupplier = builder.pickaxe;
        this.shovelSupplier = builder.shovel;
        this.hoeSupplier = builder.hoe;
        Debugger.log(toolName + "{<class>:<init>[1].1}: " + this.baseMaterial);
    }

    public static void register() {
        for (Toolset metal : values()) {
            if (metal.helmetSupplier != null) {
                metal.helmet = ModItems.REGISTER.register(
                        metal.toolName + "_helmet", metal.helmetSupplier);
            }
            if (metal.chestplateSupplier != null) {
                metal.chestplate = ModItems.REGISTER.register(
                        metal.toolName + "_chestplate", metal.chestplateSupplier);
            }
            if (metal.leggingsSupplier != null) {
                metal.leggings = ModItems.REGISTER.register(
                        metal.toolName + "_leggings", metal.leggingsSupplier);
            }
            if (metal.bootsSupplier != null) {
                metal.boots = ModItems.REGISTER.register(
                        metal.toolName + "_boots", metal.bootsSupplier);
            }
            if (metal.swordSupplier != null) {
                metal.sword = ModItems.REGISTER.register(
                        metal.toolName + "_sword", metal.swordSupplier);
            }
            if (metal.axeSupplier != null) {
                metal.axe = ModItems.REGISTER.register(
                        metal.toolName + "_axe", metal.axeSupplier);
            }
            if (metal.pickaxeSupplier != null) {
                metal.pickaxe = ModItems.REGISTER.register(
                        metal.toolName + "_pickaxe", metal.pickaxeSupplier);
            }
            if (metal.shovelSupplier != null) {
                metal.shovel = ModItems.REGISTER.register(
                        metal.toolName + "_shovel", metal.shovelSupplier);
            }
            if (metal.hoeSupplier != null) {
                metal.hoe = ModItems.REGISTER.register(
                        metal.toolName + "_hoe", metal.hoeSupplier);
            }
        }
    }

    public Supplier<Either<Item, TagKey<Item>>> getBaseMaterial() {
        return this.baseMaterial;
    }

    public Supplier<Item> getHandleMaterial() {
        return this.handleMaterial;
    }

    public @Nullable Supplier<Either<Item, TagKey<Item>>> getElement() {
        return this.element;
    }

    public Supplier<ArmorMaterial> getArmorTier() {
        return this.armorMaterial;
    }

    public Supplier<Tier> getItemTier() {
        return this.itemTier;
    }

    public String getToolName() {
        return this.toolName;
    }

    public RegistrySupplier<ArmorItem> getHelmet() {
        return this.helmet;
    }

    public RegistrySupplier<ArmorItem> getChestplate() {
        return this.chestplate;
    }

    public RegistrySupplier<ArmorItem> getLeggings() {
        return this.leggings;
    }

    public RegistrySupplier<ArmorItem> getBoots() {
        return this.boots;
    }

    public RegistrySupplier<SwordItem> getSword() {
        return this.sword;
    }

    public RegistrySupplier<AxeItem> getAxe() {
        return this.axe;
    }

    public RegistrySupplier<PickaxeItem> getPickaxe() {
        return this.pickaxe;
    }

    public RegistrySupplier<ShovelItem> getShovel() {
        return this.shovel;
    }

    public RegistrySupplier<HoeItem> getHoe() {
        return this.hoe;
    }

    public FeatureStatus getStatus() {
        return this.status;
    }

    private static Builder builder(String name) {
        return new Builder(name);
    }

    private static Builder builder(String name, FeatureStatus status) {
        return new Builder(name, status);
    }

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    @SuppressWarnings({"SameParameterValue"})
    private static final class Builder {
        final String name;
        private Supplier<Either<Item, TagKey<Item>>> baseMaterial;
        private Supplier<Item> handleMaterial;
        private Supplier<Either<Item, TagKey<Item>>> element;
        private Supplier<ArmorMaterial> armorMaterial;
        private Supplier<Tier> itemTier;
        private Supplier<ArmorItem> helmet;
        private Supplier<ArmorItem> chestplate;
        private Supplier<ArmorItem> leggings;
        private Supplier<ArmorItem> boots;
        private Supplier<SwordItem> sword;
        private Supplier<AxeItem> axe;
        private Supplier<PickaxeItem> pickaxe;
        private Supplier<ShovelItem> shovel;
        private Supplier<HoeItem> hoe;
        private FeatureStatus status = FeatureStatus.NORMAL;

        Builder(String name) {
            this.name = name;
        }

        Builder(String name, FeatureStatus status) {
            this.name = name;
            this.status = status;
        }

        Builder material(Supplier<Either<Item, TagKey<Item>>> material, Supplier<Item> handleMaterial) {
            MaterialisticTools.LOGGER.debug(this.name + "{BUILDER:MATERIAL[0]}: " + material);
            return this.material(material, handleMaterial, null);
        }

        Builder material(Supplier<Either<Item, TagKey<Item>>> material, Supplier<Item> handleMaterial, @Nullable Supplier<Either<Item, TagKey<Item>>> element) {
            MaterialisticTools.LOGGER.debug(this.name + "{BUILDER:MATERIAL[1]}: " + material);
            this.baseMaterial = material;
            this.handleMaterial = handleMaterial;
            this.element = element;
            return this;
        }

        Builder armor(Supplier<ArmorMaterial> armorMaterial) {
            return this.armor(() -> TraitPack.EMPTY, armorMaterial);
        }

        Builder armor(Supplier<TraitPack> pack, Supplier<ArmorMaterial> armorMaterial) {
            this.armorMaterial = armorMaterial;

            if (this.status == FeatureStatus.WIP) {
                this.helmet = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.HELMET, new Item.Properties(), () -> pack.get().helmet) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.chestplate = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.CHESTPLATE, new Item.Properties(), () -> pack.get().chestplate) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.leggings = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.LEGGINGS, new Item.Properties(), () -> pack.get().leggings) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.boots = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.BOOTS, new Item.Properties(), () -> pack.get().boots) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
            } else if (this.status == FeatureStatus.NORMAL) {
                this.helmet = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.HELMET, new Item.Properties(), () -> pack.get().helmet) { };
                this.chestplate = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.CHESTPLATE, new Item.Properties(), () -> pack.get().chestplate) { };
                this.leggings = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.LEGGINGS, new Item.Properties(), () -> pack.get().leggings) { };
                this.boots = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.BOOTS, new Item.Properties(), () -> pack.get().boots) {
                };
            } else if (this.status == FeatureStatus.DEPRECATED) {
                this.helmet = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.HELMET, new Item.Properties(), () -> pack.get().helmet) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.chestplate = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.CHESTPLATE, new Item.Properties(), () -> pack.get().chestplate) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.leggings = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.LEGGINGS, new Item.Properties(), () -> pack.get().leggings) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.boots = () -> new CustomArmor(armorMaterial.get(), ArmorItem.Type.BOOTS, new Item.Properties(), () -> pack.get().boots) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
            }
            return this;
        }

        Builder tools(Supplier<Tier> itemTier) {
            return this.tools(() -> TraitPack.EMPTY, itemTier);
        }

        Builder tools(Supplier<TraitPack> pack, Supplier<Tier> itemTier) {
            this.itemTier = itemTier;

            if (this.status == FeatureStatus.WIP) {
                this.sword = () -> new CustomSword(itemTier.get(), 3, -2.4F, new Item.Properties(), () -> pack.get().sword) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.axe = () -> new CustomAxe(itemTier.get(), 5F, -3F, new Item.Properties(), () -> pack.get().axe) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.pickaxe = () -> new CustomPickaxe(itemTier.get(), 1, -2.8F, new Item.Properties(), () -> pack.get().pickaxe) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.shovel = () -> new CustomShovel(itemTier.get(), 1.5F, -3F, new Item.Properties(), () -> pack.get().shovel) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
                this.hoe = () -> new CustomHoe(itemTier.get(), (int) -(itemTier.get().getAttackDamageBonus() - 1), -1F, new Item.Properties(), () -> pack.get().hoe) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.wip"));
                    }
                };
            } else if (this.status == FeatureStatus.NORMAL) {
                this.sword = () -> new CustomSword(itemTier.get(), 3, -2.4F, new Item.Properties(), () -> pack.get().sword);
                this.axe = () -> new CustomAxe(itemTier.get(), 5F, -3F, new Item.Properties(), () -> pack.get().axe);
                this.pickaxe = () -> new CustomPickaxe(itemTier.get(), 1, -2.8F, new Item.Properties(), () -> pack.get().pickaxe);
                this.shovel = () -> new CustomShovel(itemTier.get(), 1.5F, -3F, new Item.Properties(), () -> pack.get().shovel);
                this.hoe = () -> new CustomHoe(itemTier.get(), (int) -(itemTier.get().getAttackDamageBonus() - 1), -1F, new Item.Properties(), () -> pack.get().hoe);
            } else if (this.status == FeatureStatus.DEPRECATED) {
                this.sword = () -> new CustomSword(itemTier.get(), 3, -2.4F, new Item.Properties(), () -> pack.get().sword) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.axe = () -> new CustomAxe(itemTier.get(), 5F, -3F, new Item.Properties(), () -> pack.get().axe) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.pickaxe = () -> new CustomPickaxe(itemTier.get(), 1, -2.8F, new Item.Properties(), () -> pack.get().pickaxe) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.shovel = () -> new CustomShovel(itemTier.get(), 1.5F, -3F, new Item.Properties(), () -> pack.get().shovel) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
                this.hoe = () -> new CustomHoe(itemTier.get(), (int) -(itemTier.get().getAttackDamageBonus() - 1), -1F, new Item.Properties(), () -> pack.get().hoe) {
                    @Override
                    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level dimension, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                        super.appendHoverText(stack, dimension, tooltip, flag);
                        tooltip.add(Component.translatable("misc.materialistic_tools.deprecated"));
                    }
                };
            }
            return this;
        }
    }

    @Override
    public String toString() {
        return "Toolset{" +
                "toolName='" + this.toolName + '\'' +
                ", baseMaterial=" + this.baseMaterial +
                ", handleMaterial=" + this.handleMaterial +
                ", element=" + this.element +
                ", status=" + this.status +
                ", helmet=" + this.helmet +
                ", chestplate=" + this.chestplate +
                ", leggings=" + this.leggings +
                ", boots=" + this.boots +
                ", sword=" + this.sword +
                ", axe=" + this.axe +
                ", pickaxe=" + this.pickaxe +
                ", shovel=" + this.shovel +
                ", hoe=" + this.hoe +
                '}';
    }

    @Override
    public boolean test(Holder<Item> itemHolder) {
        return itemHolder.value() == this.sword.orElse(null)
                || itemHolder.value() == this.axe.orElse(null)
                || itemHolder.value() == this.pickaxe.orElse(null)
                || itemHolder.value() == this.shovel.orElse(null)
                || itemHolder.value() == this.hoe.orElse(null)
                || itemHolder.value() == this.helmet.orElse(null)
                || itemHolder.value() == this.chestplate.orElse(null)
                || itemHolder.value() == this.leggings.orElse(null)
                || itemHolder.value() == this.boots.orElse(null)
                ;
    }
}
