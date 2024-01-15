package io.github.ultreon.mods.materialistictools.util.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ItemTierBuilder {
    private int tier;
    private int maxUses;
    private float efficiency;
    private float attackDamage;
    private int enchantability;
    private Supplier<Ingredient> repairMaterial;

    public ItemTierBuilder tier(int tier) {
        this.tier = tier;
        return this;
    }

    public ItemTierBuilder maxUses(int maxUses) {
        this.maxUses = maxUses;
        return this;
    }

    public ItemTierBuilder efficiency(float efficiency) {
        this.efficiency = efficiency;
        return this;
    }

    public ItemTierBuilder attackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
        return this;
    }

    public ItemTierBuilder enchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    public ItemTierBuilder repairMaterial(Supplier<Ingredient> repairMaterial) {
        this.repairMaterial = repairMaterial;
        return this;
    }

    public Tier build() {
        return new Tier() {
            @Override
            public int getUses() {
                return ItemTierBuilder.this.maxUses;
            }

            @Override
            public float getSpeed() {
                return ItemTierBuilder.this.efficiency;
            }

            @Override
            public float getAttackDamageBonus() {
                return ItemTierBuilder.this.attackDamage;
            }

            @Override
            public int getLevel() {
                return ItemTierBuilder.this.tier;
            }

            @Override
            public int getEnchantmentValue() {
                return ItemTierBuilder.this.enchantability;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return ItemTierBuilder.this.repairMaterial.get();
            }
        };
    }
}