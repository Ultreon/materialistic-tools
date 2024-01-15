package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import io.github.ultreon.mods.materialistictools.item.ItemCategory;

public class MagicResistantTrait extends AbstractTrait {
    public MagicResistantTrait() {
        super(ItemCategory.ARMOR);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#8603BE");
    }

    @Override
    public Float onLivingDamage(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        super.onLivingDamage(stack, entity, source, amount);

        if (source.is(DamageTypeTags.WITCH_RESISTANT_TO) && !source.is(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
            return null;
        }
        return amount;
    }
}
