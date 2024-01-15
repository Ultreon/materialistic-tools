package io.github.ultreon.mods.materialistictools.init;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import io.github.ultreon.mods.materialistictools.util.item.ItemTierBuilder;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import static java.lang.Float.POSITIVE_INFINITY;
import static java.lang.Integer.MAX_VALUE;

public class ModTiers {
    public static final Tier INFINITY = new ItemTierBuilder().tier(7).maxUses(MAX_VALUE).efficiency(POSITIVE_INFINITY).attackDamage(POSITIVE_INFINITY).enchantability(MAX_VALUE)
            .repairMaterial(() -> Ingredient.of(ItemMaterial.INFINITY.getIngot().get())).build();
    public static final Tier CHUNK = new ItemTierBuilder().tier(6).maxUses(8280426).efficiency(930F).attackDamage(510f).enchantability(900)
            .repairMaterial(() -> Ingredient.of(ItemMaterial.CHUNK.getIngot().get())).build();
    public static final Tier ULTRINIUM = new ItemTierBuilder().tier(6).maxUses(65842).efficiency(309F).attackDamage(130f).enchantability(220)
            .repairMaterial(() -> Ingredient.of(ItemMaterial.ULTRINIUM.getIngot().get())).build();
    public static final Tier COBALT = new ItemTierBuilder().tier(5).maxUses(8140).efficiency(59.5F).attackDamage(35f).enchantability(96)
            .repairMaterial(() -> Ingredient.of(ItemMaterial.COBALT.getIngot().get())).build();

    @ExpectPlatform
    public static void postInit() {

    }
}
