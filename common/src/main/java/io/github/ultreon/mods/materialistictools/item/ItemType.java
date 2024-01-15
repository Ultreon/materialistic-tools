package io.github.ultreon.mods.materialistictools.item;

import io.github.ultreon.mods.materialistictools.util.Translatable;

import java.util.List;

public enum ItemType implements Translatable {
    SWORD("sword", ItemCategory.WEAPON),
    AXE("axe", ItemCategory.DIGGER, ItemCategory.WEAPON),
    PICKAXE("pickaxe", ItemCategory.DIGGER),
    SHOVEL("shovel", ItemCategory.DIGGER),
    HOE("hoe", ItemCategory.BLOCK_UTIL),
    SHEARS("shears", ItemCategory.DIGGER, ItemCategory.TOOL),
    WRENCH("wrench", ItemCategory.TOOL),
    MULTI_TOOL("multi_tool", ItemCategory.DIGGER),
    COMPASS("compass", ItemCategory.INFO),
    CLOCK("clock", ItemCategory.INFO),
    FISHING_ROD("fishing_rod", ItemCategory.TOOL),
    IGNITER("igniter", ItemCategory.BLOCK_UTIL),
    BLOCK("block", ItemCategory.PLACEABLE),
    HAMMER("hammer", ItemCategory.WEAPON, ItemCategory.DIGGER),
    EXCAVATOR("excavator", ItemCategory.DIGGER),
    BATTLE_AXE("battle_axe", ItemCategory.WEAPON),
    LUMBER_AXE("lumber_axe", ItemCategory.DIGGER),
    SCYTHE("scythe", ItemCategory.WEAPON, ItemCategory.BLOCK_UTIL, ItemCategory.DIGGER),
    HELMET("helmet", ItemCategory.ARMOR),
    CHESTPLATE("chestplate", ItemCategory.ARMOR),
    LEGGINGS("leggings", ItemCategory.ARMOR),
    BOOTS("boots", ItemCategory.ARMOR),
    ;

    private final String name;
    private final List<ItemCategory> categories;

    ItemType(String name, ItemCategory... categories) {
        this.name = name;
        this.categories = List.of(categories);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "name='" + this.name + '\'' +
                '}';
    }

    @Override
    public String getTranslationId() {
        return "misc.materialistic_tools.item_type." + this.name;
    }

    public List<ItemCategory> getCategories() {
        return this.categories;
    }
}
