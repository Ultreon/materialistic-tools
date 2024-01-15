package io.github.ultreon.mods.materialistictools.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static io.github.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

public class ModItemTags {
    public static final TagKey<Item> RAW_MEAT = tag("food/raw_meat");
    public static final TagKey<Item> COOKED_MEAT = tag("food/cooked_meat");
    public static final TagKey<Item> MEAT = tag("food/meat");
    public static final TagKey<Item> ROD_URANIUM = tag("rod/uranium");
    public static final TagKey<Item> OBSIDIAN = tag("obsidian");

    private static TagKey<Item> forgeTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation("forge", name));
    }

    private static TagKey<Item> mcTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(ResourceLocation.DEFAULT_NAMESPACE, name));
    }

    private static TagKey<Item> tag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, name));
    }

    public static void init() {
        // Just initialize tags.
    }
}
