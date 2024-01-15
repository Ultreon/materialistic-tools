package io.github.ultreon.mods.materialistictools.item.tool.trait;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import io.github.ultreon.mods.materialistictools.item.ItemCategory;
import io.github.ultreon.mods.materialistictools.item.ItemType;

import java.util.function.Predicate;

public abstract class AbstractMobEffectTrait extends AbstractTrait {
    public AbstractMobEffectTrait() {
        super();
    }

    public AbstractMobEffectTrait(ItemType... types) {
        super(types);
    }

    public AbstractMobEffectTrait(ItemCategory... categories) {
        super(categories);
    }

    public AbstractMobEffectTrait(Predicate<ItemType> predicate) {
        super(predicate);
    }

    @Override
    public boolean onHitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, LivingEntity attacker) {
        victim.addEffect(this.getEffectInstance());
        return super.onHitEntity(stack, victim, attacker);
    }

    @Override
    public void onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(this.getEffectInstance());
        }
    }

    public abstract MobEffectInstance getEffectInstance();
}
