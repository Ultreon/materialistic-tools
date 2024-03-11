package io.github.ultreon.mods.materialistictools.stats;

import com.ibm.icu.impl.ICUResourceBundle;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModStats {
    private static final DeferredRegister<ResourceLocation> REGISTER = DeferredRegister.create(MaterialisticTools.MOD_ID, Registries.CUSTOM_STAT);
    private static final Map<String, StatFormatter> FORMATTERS = new HashMap<>();

//    public static final RegistrySupplier<ResourceLocation> INFINITY_KILL = register("infinity_kill", Integer::toString, () -> MaterialisticTools.res("stats/infinity_kill"));

    private static RegistrySupplier<ResourceLocation> register(String name, StatFormatter formatter, Supplier<ResourceLocation> toRegister) {
        ModStats.FORMATTERS.put(name, formatter);
        return REGISTER.register(name, toRegister);
    }

    public static void register() {
        REGISTER.register();
    }
}
