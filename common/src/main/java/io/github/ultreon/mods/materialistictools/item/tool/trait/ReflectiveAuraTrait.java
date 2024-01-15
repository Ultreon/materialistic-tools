package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import io.github.ultreon.mods.materialistictools.item.ItemType;

/**
 * ReflectiveAuraTrait represents a trait that applies a reflective aura effect to certain item types.
 * When the equipped player is hit by a projectile, there is a chance that the damage is nullified.
 */
public class ReflectiveAuraTrait extends AbstractTrait {
    private static final float TRIGGER_CHANCE = 0.4f;

    public ReflectiveAuraTrait() {
        super(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
    }

    public TextColor getColor() {
        return TextColor.parseColor("#17a2b2");
    }

    @Override
    public Float onLivingDamage(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        if (source.is(DamageTypeTags.IS_PROJECTILE) && entity.getRandom().nextFloat() < TRIGGER_CHANCE) {
            return null;
        }

        super.onLivingDamage(stack, entity, source, amount);
        return amount;
    }
}
