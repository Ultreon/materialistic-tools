package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import io.github.ultreon.mods.materialistictools.item.ItemType;

public class ParalyzeTrait extends AbstractMobEffectTrait {
    public ParalyzeTrait() {
        super(ItemType.AXE, ItemType.BATTLE_AXE, ItemType.HAMMER);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#fff000");
    }

    @Override
    public MobEffectInstance getEffectInstance() {
        return new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 29);
    }
}
