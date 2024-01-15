package io.github.ultreon.mods.materialistictools.item.tool;

import com.google.common.base.Suppliers;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ItemTier implements Tier {
    protected int tier;
    protected int maxUses;
    protected float efficiency;
    protected float attackDamage;
    protected int enchantability;
    protected Supplier<Ingredient> repairMaterial;

    public ItemTier(Properties properties) {
        this.tier = properties.tier;
        this.maxUses = properties.maxUses;
        this.efficiency = properties.efficiency;
        this.attackDamage = properties.attackDamage;
        this.enchantability = properties.enchantability;
        this.repairMaterial = properties.repairMaterial;
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.tier;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    public static class Properties {
        private static final Supplier<Ingredient> EMPTY_INGREDIENT = Suppliers.memoize(() -> Ingredient.EMPTY);
        private final int tier;
        private int maxUses = 0;
        private float efficiency = 1.0F;
        private float attackDamage = 1.0F;
        private int enchantability = 0;
        private Supplier<Ingredient> repairMaterial = EMPTY_INGREDIENT;

        public Properties(int tier) {
            this.tier = tier;
        }

        public Properties maxUses(int maxUses) {
            this.maxUses = maxUses;
            return this;
        }

        public Properties efficiency(float efficiency) {
            this.efficiency = efficiency;
            return this;
        }

        public Properties attackDamage(float attackDamage) {
            this.attackDamage = attackDamage;
            return this;
        }

        public Properties enchantability(int enchantability) {
            this.enchantability = enchantability;
            return this;
        }

        public Properties repairMaterial(Supplier<Ingredient> repairMaterial) {
            this.repairMaterial = repairMaterial;
            return this;
        }
    }
}
