package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import io.github.ultreon.mods.materialistictools.item.ItemCategory;

public class PoisonTrait extends AbstractMobEffectTrait {
    public PoisonTrait() {
        super(ItemCategory.WEAPON);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#007F3F");
    }

    @Override
    public MobEffectInstance getEffectInstance() {
        return new MobEffectInstance(MobEffects.POISON, 50, 1);
    }
}
