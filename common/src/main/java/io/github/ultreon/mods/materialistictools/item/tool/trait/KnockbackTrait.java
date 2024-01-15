package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import io.github.ultreon.mods.materialistictools.item.ItemCategory;
import io.github.ultreon.mods.materialistictools.item.ItemType;

import java.util.Set;

public class KnockbackTrait extends AbstractTrait {
    public KnockbackTrait() {
        super(ItemCategory.WEAPON, ItemCategory.DIGGER);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#B0D4ED");
    }

    @Override
    public float getKnockback(Set<ItemType> itemTypes) {
        return 2f;
    }
}
