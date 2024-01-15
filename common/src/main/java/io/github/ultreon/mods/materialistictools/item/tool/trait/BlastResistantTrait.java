package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import io.github.ultreon.mods.materialistictools.item.ItemCategory;

public class BlastResistantTrait extends AbstractTrait {
    public BlastResistantTrait() {
        super(ItemCategory.ARMOR);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#BE031D");
    }

    @Override
    public Float onLivingDamage(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        super.onLivingDamage(stack, entity, source, amount);

        if (source.is(DamageTypeTags.IS_EXPLOSION) && !source.is(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
            return null;
        }
        return amount;
    }
}
