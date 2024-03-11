package io.github.ultreon.mods.materialistictools.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import io.github.ultreon.mods.materialistictools.item.tool.types.CustomSword;

import java.util.Collections;

public class HookItem extends CustomSword {
    public HookItem(Tier tier, int attackDamage, float speed, Item.Properties properties) {
        super(tier, attackDamage, speed, properties, Collections::emptyList);
    }

    @Override
    public float getKnockback() {
        return 1F;
    }
}
