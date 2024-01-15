package io.github.ultreon.mods.materialistictools.item.tool;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.item.tool.trait.*;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

import static io.github.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

@MethodsReturnNonnullByDefault
public final class ModTraits {
    public static final Registrar<AbstractTrait> REGISTER = RegistrarManager.get(MOD_ID).<AbstractTrait>builder(MaterialisticTools.res("trait")).syncToClients().saveToDisc().build();

    public static final RegistrySupplier<RadioactiveTrait> RADIOACTIVE = register("radioactive", RadioactiveTrait::new);
    public static final RegistrySupplier<InfinityTrait> INFINITY = register("infinity", InfinityTrait::new);
    public static final RegistrySupplier<WitherTrait> WITHER = register("wither", WitherTrait::new);
    public static final RegistrySupplier<PoisonTrait> POISON = register("poison", PoisonTrait::new);
    public static final RegistrySupplier<EnderTrait> ENDER = register("ender", EnderTrait::new);
    public static final RegistrySupplier<ReflectiveAuraTrait> REFLECTIVE_AURA = register("reflective_aura", ReflectiveAuraTrait::new);
    public static final RegistrySupplier<AuroraShieldTrait> AURORA_SHIELD = register("aurora_shield", AuroraShieldTrait::new);
    public static final RegistrySupplier<BlazeTrait> BLAZE = register("blaze", BlazeTrait::new);
    public static final RegistrySupplier<HolyTrait> HOLY = register("holy", HolyTrait::new);
    public static final RegistrySupplier<SharpTrait> SHARP = register("sharp", SharpTrait::new);
    public static final RegistrySupplier<BlastResistantTrait> BLAST_RESISTANT = register("blast_resistant", BlastResistantTrait::new);
    public static final RegistrySupplier<FireResistantTrait> FIRE_RESISTANT = register("fire_resistant", FireResistantTrait::new);
    public static final RegistrySupplier<MagicResistantTrait> MAGIC_RESISTANT = register("magic_resistant", MagicResistantTrait::new);
    public static final RegistrySupplier<ProjectileResistantTrait> PROJECTILE_RESISTANT = register("projectile_resistant", ProjectileResistantTrait::new);
    public static final RegistrySupplier<KnockbackTrait> KNOCKBACK = register("knockback", KnockbackTrait::new);
    public static final RegistrySupplier<SlimeyTrait> SLIMY = register("slimey", SlimeyTrait::new);
    public static final RegistrySupplier<ExplosiveTrait> EXPLOSIVE = register("explosive", ExplosiveTrait::new);
    public static final RegistrySupplier<EmpoweredTrait> EMPOWERED = register("empowered", EmpoweredTrait::new);
    public static final RegistrySupplier<ChunkyTrait> CHUNKY = register("chunky", ChunkyTrait::new);

    public static <T extends AbstractTrait> RegistrySupplier<T> register(String name, Supplier<T> supplier) {
        return REGISTER.register(new ResourceLocation(MOD_ID, name), supplier);
    }

    public static void register() {

    }
}
