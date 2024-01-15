package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import io.github.ultreon.mods.materialistictools.item.ItemCategory;

public class BlazeTrait extends AbstractTrait {
    public BlazeTrait() {
        super(ItemCategory.WEAPON);
    }

    @Override
    public boolean onHitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, LivingEntity attacker) {
        victim.setSecondsOnFire(10);
        return super.onHitEntity(stack, victim, attacker);
    }

    public TextColor getColor() {
        return TextColor.parseColor("#ff8000");
    }
}
