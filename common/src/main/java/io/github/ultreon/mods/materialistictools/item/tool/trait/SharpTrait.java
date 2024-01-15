package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import io.github.ultreon.mods.materialistictools.init.ModEffects;
import io.github.ultreon.mods.materialistictools.item.ItemType;

public class SharpTrait extends AbstractMobEffectTrait {
    public SharpTrait() {
        super(ItemType.SWORD, ItemType.AXE, ItemType.PICKAXE, ItemType.SHOVEL, ItemType.HOE);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#df1300");
    }

    @Override
    public MobEffectInstance getEffectInstance() {
        return new MobEffectInstance(ModEffects.BLEEDING.get(), 240, 2);
    }
}
