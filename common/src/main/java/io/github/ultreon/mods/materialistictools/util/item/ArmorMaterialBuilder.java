package io.github.ultreon.mods.materialistictools.util.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ArmorMaterialBuilder {
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

    private String name;
    private int maxDamageFactor;
    private int[] damageReduction;
    private int enchantability;
    private SoundEvent sound;
    private float toughness;
    private Supplier<Ingredient> repairMaterial;
    private float knockbackResistance;

    public ArmorMaterialBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ArmorMaterialBuilder maxDamageFactor(int maxDamageFactor) {
        this.maxDamageFactor = maxDamageFactor;
        return this;
    }

    public ArmorMaterialBuilder damageReduction(int[] damageReduction) {
        this.damageReduction = damageReduction;
        return this;
    }

    public ArmorMaterialBuilder enchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    public ArmorMaterialBuilder sound(SoundEvent sound) {
        this.sound = sound;
        return this;
    }

    public ArmorMaterialBuilder toughness(float toughness) {
        this.toughness = toughness;
        return this;
    }

    public ArmorMaterialBuilder repairMaterial(Supplier<Ingredient> repairMaterial) {
        this.repairMaterial = repairMaterial;
        return this;
    }

    public ArmorMaterialBuilder knockbackResistance(float knockbackResistance) {
        this.knockbackResistance = knockbackResistance;
        return this;
    }

    public ArmorMaterial build() {
        return new ArmorMaterial() {
            @Override
            public int getDurabilityForType(@NotNull ArmorItem.Type slotIn) {
                return MAX_DAMAGE_ARRAY[slotIn.getSlot().getIndex()] * ArmorMaterialBuilder.this.maxDamageFactor;
            }

            @Override
            public int getDefenseForType(@NotNull ArmorItem.Type slotIn) {
                return ArmorMaterialBuilder.this.damageReduction[slotIn.getSlot().getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return ArmorMaterialBuilder.this.enchantability;
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return ArmorMaterialBuilder.this.sound;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return ArmorMaterialBuilder.this.repairMaterial.get();
            }

            @Override
            public @NotNull String getName() {
                return ArmorMaterialBuilder.this.name;
            }

            @Override
            public float getToughness() {
                return ArmorMaterialBuilder.this.toughness;
            }

            @Override
            public float getKnockbackResistance() {
                return ArmorMaterialBuilder.this.knockbackResistance;
            }
        };
    }
}