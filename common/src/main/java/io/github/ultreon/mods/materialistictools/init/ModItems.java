package io.github.ultreon.mods.materialistictools.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import io.github.ultreon.mods.materialistictools.item.tool.Toolset;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import static io.github.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

@SuppressWarnings("unused")
public class ModItems {
    @ApiStatus.Internal
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> FIRE_GEM = REGISTER.register("fire_gem", () -> new Item(new Item.Properties().fireResistant()));

    static {
        ItemMaterial.registerItems();
        Toolset.register();
    }

    public static void register() {
        REGISTER.register();
    }
}
