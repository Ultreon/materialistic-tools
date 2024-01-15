package io.github.ultreon.mods.materialistictools.init;

import dev.architectury.registry.registries.DeferredRegister;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

import static io.github.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

@SuppressWarnings("unused")
public class ModBlocks {
    @ApiStatus.Internal
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    static {
        ItemMaterial.registerBlocks();
    }

    public static void register() {
        REGISTER.register();
    }
}
