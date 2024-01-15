package io.github.ultreon.mods.materialistictools.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import io.github.ultreon.mods.materialistictools.init.ModDamageTypes;
import io.github.ultreon.mods.materialistictools.item.tool.Toolset;

/**
 * Radiation potion effect, does one heart of damage very slowly. Can kill all living entities.
 *
 * @see Toolset#URANIUM
 */
public class RadiationEffect extends MobEffect {
    public RadiationEffect() {
        super(MobEffectCategory.HARMFUL, 0x408040);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.hurt(entityLivingBaseIn.damageSources().source(ModDamageTypes.RADIATION), 2f);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int i;

        if (duration == 0) i = 0;
        else i = (1800 * amplifier) / duration;

        if (i > 0) return duration % i == 0;
        else return true;
    }
}
