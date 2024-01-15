package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import io.github.ultreon.mods.materialistictools.item.ItemType;

public class ExplosiveTrait extends AbstractTrait {
    public ExplosiveTrait() {
        super(ItemType.CHESTPLATE, ItemType.SWORD, ItemType.AXE, ItemType.PICKAXE);
    }

    @Override
    public boolean onHitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, LivingEntity attacker) {
        if (attacker.level().isClientSide) {
            // Don't do anything on client.
            return true;
        }

        if (attacker.getRandom().nextInt(10) == 0) {
            victim.level().explode(victim, victim.getX(), victim.getY(), victim.getZ(), 6.0F, Level.ExplosionInteraction.NONE);
        }
        return true;
    }

    @Override
    public Float onLivingDamage(ItemStack stack, LivingEntity victim, DamageSource source, float amount) {
        Entity damaging = source.getEntity();
        if (damaging instanceof LivingEntity attacker) {
            if (attacker.getRandom().nextInt(10) == 0) {
                victim.level().explode(victim, victim.getX(), victim.getY(), victim.getZ(), 4.0F, Level.ExplosionInteraction.NONE);
            }
        }

        return super.onLivingDamage(stack, victim, source, amount);
    }

    @Override
    public boolean onBlockBroken(ItemStack stack, Level dimensionIn, BlockState state, BlockPos pos, LivingEntity user) {
        if (user.getRandom().nextInt(10) == 0) {
            user.level().explode(user, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, 1.5F, Level.ExplosionInteraction.NONE);
        }

        return super.onBlockBroken(stack, dimensionIn, state, pos, user);
    }

    @Override
    public TextColor getColor() {
        return TextColor.parseColor("#E50099");
    }
}
