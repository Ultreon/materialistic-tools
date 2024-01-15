package io.github.ultreon.mods.materialistictools.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import io.github.ultreon.mods.materialistictools.init.ModDamageTypes;
import io.github.ultreon.mods.materialistictools.item.tool.Toolset;

/**
 * Radiation potion effect, does one heart of damage very slowly. Can kill all living entities.
 *
 * @see Toolset#ALUMINUM
 * @see Toolset#OBSIDIAN
 */
public class BleedingEffect extends MobEffect {
    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 0xbfbfbf);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.hurt(entityLivingBaseIn.damageSources().source(ModDamageTypes.BLEEDING), 1f);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int j = 35 >> amplifier;
        if (j > 0)
            return duration % j == 0;
        else
            return true;
    }
}
