package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import io.github.ultreon.mods.materialistictools.item.ItemCategory;

public class ChunkyTrait extends AbstractTrait {
    public ChunkyTrait() {
        super(ItemCategory.ARMOR);
    }

    @Override
    public Float onLivingDamage(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        return amount / 500;
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#00CC87");
    }
}
