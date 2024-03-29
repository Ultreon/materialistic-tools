package io.github.ultreon.mods.materialistictools.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.item.IngredientItems;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import io.github.ultreon.mods.materialistictools.item.tool.Toolset;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class ModCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(MaterialisticTools.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> MATERIALISTIC_TOOLS = register("materialistic_tools", () -> new ItemStack(ItemMaterial.AURORA_STEEL.getIngot().get()), ModCreativeTabs::register_combat);

    // Register functions
    private static void register_material_tools(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        for (Toolset toolset : Toolset.values()) {
            Optional.ofNullable(toolset.getSword()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getAxe()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getPickaxe()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getShovel()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getHoe()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getHelmet()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getChestplate()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getLeggings()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getBoots()).ifPresent(obj -> output.accept(obj.get()));
        }
    }
    private static void register_metal_ingredients(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        for (ItemMaterial material : ItemMaterial.getValues()) {
            Optional.ofNullable(material.getRawMaterial()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(material.getIngot()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(material.getNugget()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(material.getStorageBlock()).ifPresent(obj -> output.accept(obj.get()));
        }
    }

    private static void register_ingredients(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        for (ItemMaterial material : ItemMaterial.getValues())
            Optional.ofNullable(material.getGem()).ifPresent(obj -> output.accept(obj.get()));
        output.accept(ModItems.FIRE_GEM.get());

        for (IngredientItems material : IngredientItems.values())
            output.accept(material.asItem());
    }

    private static void register_ores(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        for (ItemMaterial material : ItemMaterial.getValues()) {
            Optional.ofNullable(material.getStoneOre()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(material.getDeepslateOre()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(material.getNetherOre()).ifPresent(obj -> output.accept(obj.get()));
        }
    }

    private static void register_misc(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {

    }

    private static void register_combat(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        for (Toolset toolset : Toolset.values()) {
            Optional.ofNullable(toolset.getSword()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getAxe()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getPickaxe()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getShovel()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getHoe()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getHelmet()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getChestplate()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getLeggings()).ifPresent(obj -> output.accept(obj.get()));
            Optional.ofNullable(toolset.getBoots()).ifPresent(obj -> output.accept(obj.get()));
        }

        for (ItemMaterial material : ItemMaterial.getValues()) {
            Optional.ofNullable(material.getIngot()).ifPresent(sup -> sup.ifPresent(output::accept));
            Optional.ofNullable(material.getNugget()).ifPresent(sup -> sup.ifPresent(output::accept));
            Optional.ofNullable(material.getStorageBlock()).ifPresent(sup -> sup.ifPresent(output::accept));
            Optional.ofNullable(material.getGem()).ifPresent(sup -> sup.ifPresent(output::accept));
            Optional.ofNullable(material.getStoneOre()).ifPresent(sup -> sup.ifPresent(output::accept));
            Optional.ofNullable(material.getDeepslateOre()).ifPresent(sup -> sup.ifPresent(output::accept));
            Optional.ofNullable(material.getNetherOre()).ifPresent(sup -> sup.ifPresent(output::accept));
            Optional.ofNullable(material.getRawMaterial()).ifPresent(sup -> sup.ifPresent(output::accept));
        }
//        output.accept(ModItems.SILVER_HOOK.get());
//        output.accept(ModItems.DYNAMITE.get());
    }

    private static RegistrySupplier<CreativeModeTab> register(String name, Supplier<@NotNull ItemStack> icon) {
        return register(name, icon, false);
    }

    private static RegistrySupplier<CreativeModeTab> register(String name, Supplier<@NotNull ItemStack> icon, boolean hasSearchBar) {
        return REGISTER.register(name, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).icon(icon).build());
    }

    private static RegistrySupplier<CreativeModeTab> register(String name, Supplier<@NotNull ItemStack> icon, CreativeModeTab.DisplayItemsGenerator items) {
        return register(name, icon, false, items);
    }

    private static RegistrySupplier<CreativeModeTab> register(String name, Supplier<@NotNull ItemStack> icon, boolean hasSearchBar, CreativeModeTab.DisplayItemsGenerator items) {
        return REGISTER.register(name, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).icon(icon).title(Component.translatable("itemGroup.materialistic_tools." + name)).displayItems(items).build());
    }

    public static void register() {
        REGISTER.register();
    }
}
