package io.github.ultreon.mods.materialistictools.effect;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import io.github.ultreon.mods.materialistictools.init.ModDamageTypes;

import java.util.UUID;

/**
 * @author XyperCode
 */
@MethodsReturnNonnullByDefault
public class CurseEffect extends MobEffect {
    public CurseEffect() {
        super(MobEffectCategory.HARMFUL, 0xff00ff);
        this.addAttributeModifier(Attributes.LUCK, UUID.nameUUIDFromBytes("CURSED!!!".getBytes()).toString()/*""CC5AF142-2BD2-4215-B636-2605AED11727"*/, -4.0D, AttributeModifier.Operation.ADDITION);
    }

    /**
     * This effect is not an instant effect.
     *
     * @return always {@code false}.
     */
    @Override
    public final boolean isInstantenous() {
        return false;
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int j = 20 >> amplifier;
        if (j > 0) {
            return duration % j == 0;
        } else {
            return true;
        }
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        RandomSource rng = entity.getRandom();
        switch (rng.nextInt(13)) {
            case 0 -> entity.hurt(entity.damageSources().source(ModDamageTypes.CURSE), 1f);
            case 1 -> entity.setDeltaMovement(((rng.nextDouble() - 0.5d) * 2d) * 10d, ((rng.nextDouble() - 0.5d) * 2d) * 10d, ((rng.nextDouble() - 0.5d) * 2d) * 10d);
            case 2 -> entity.setJumping(rng.nextBoolean());
            case 3 -> entity.setShiftKeyDown(rng.nextBoolean());
            case 4 -> entity.lerpMotion(((rng.nextDouble() - 0.5d) * 2d) * 10d, ((rng.nextDouble() - 0.5d) * 2d) * 10d, ((rng.nextDouble() - 0.5d) * 2d) * 10d);
            case 5 -> entity.teleportTo(entity.getX(), entity.getY() + rng.nextInt(20), entity.getZ());
            case 6 -> entity.addEffect(new MobEffectInstance(MobEffects.POISON, 12000, 5));
            case 7 -> entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 12000, 5));
            case 8 -> entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 12000, 5));
            case 9 -> entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 12000, 5));
            case 10 -> entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 12000, 5));
            case 11 -> entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 12000, 5));
            case 12 -> entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 12000, 3));
            case 13 -> entity.setSecondsOnFire(2);
            default -> {
            }
        }
    }
}
