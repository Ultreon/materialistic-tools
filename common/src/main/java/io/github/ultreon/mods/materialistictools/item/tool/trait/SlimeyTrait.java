package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import io.github.ultreon.mods.materialistictools.item.ItemCategory;
import io.github.ultreon.mods.materialistictools.item.ItemType;

import java.util.Set;

public class SlimeyTrait extends AbstractTrait {
    public SlimeyTrait() {
        super(ItemCategory.WEAPON, ItemCategory.DIGGER);
    }

    @Override
    public boolean onHitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, LivingEntity attacker) {
        if (attacker.level().isClientSide) {
            // Don't do anything on client.
            return true;
        }

        victim.getCommandSenderWorld().playLocalSound(victim.getX(), victim.getY(), victim.getZ(), SoundEvents.SLIME_BLOCK_PLACE, SoundSource.BLOCKS, 1f, 1f, false);
        return true;
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#76BE6D");
    }

    @Override
    public float getKnockback(Set<ItemType> itemTypes) {
        return 4f;
    }
}
