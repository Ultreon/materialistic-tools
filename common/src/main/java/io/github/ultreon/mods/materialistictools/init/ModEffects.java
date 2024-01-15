package io.github.ultreon.mods.materialistictools.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.effect.BleedingEffect;
import io.github.ultreon.mods.materialistictools.effect.CurseEffect;
import io.github.ultreon.mods.materialistictools.effect.RadiationEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class ModEffects {
    private static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(MaterialisticTools.MOD_ID, Registries.MOB_EFFECT);

    // Racks
    public static final RegistrySupplier<CurseEffect> CURSE = register("curse", CurseEffect::new);
    public static final RegistrySupplier<RadiationEffect> RADIATION = register("radiation", RadiationEffect::new);
    public static final RegistrySupplier<BleedingEffect> BLEEDING = register("bleeding", BleedingEffect::new);

    // Utility methods
    private static <T extends MobEffect> RegistrySupplier<T> register(String name, Supplier<T> supplier) {
        return REGISTER.register(name, supplier);
    }

    public static void register() {
        REGISTER.register();
    }
}
