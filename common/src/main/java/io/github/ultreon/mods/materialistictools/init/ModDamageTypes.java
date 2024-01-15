package io.github.ultreon.mods.materialistictools.init;

import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public final class ModDamageTypes {
    public static final ResourceKey<DamageType> CURSE = create("curse");
    public static final ResourceKey<DamageType> RADIATION = create("radiation");
    public static final ResourceKey<DamageType> BLEEDING = create("bleeding");

    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, MaterialisticTools.res(name));
    }

    public static void nopInit() {

    }
}
