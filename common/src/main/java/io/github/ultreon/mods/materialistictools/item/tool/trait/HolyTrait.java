package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import io.github.ultreon.mods.materialistictools.item.ItemType;

import java.util.Set;

public class HolyTrait extends AbstractTrait {
    public HolyTrait() {
        super(ItemType.SWORD, ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#B0D4ED");
    }

    @Override
    public float getSmiteValue(Set<ItemType> smpToolTypes, ItemStack stack, LivingEntity attacker) {
        return 8f;
    }
}
