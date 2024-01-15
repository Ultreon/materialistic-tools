package io.github.ultreon.mods.materialistictools.item.material;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public interface BaseItemMaterial {
    String getName();

    RegistrySupplier<Block> getStoneOre();

    RegistrySupplier<Block> getDeepslateOre();

    RegistrySupplier<Block> getNetherOre();

    RegistrySupplier<Block> getStorageBlock();

    RegistrySupplier<Item> getRawMaterial();

    RegistrySupplier<Item> getIngot();

    RegistrySupplier<Item> getGem();

    RegistrySupplier<Item> getNugget();

    Optional<TagKey<Block>> getOreTag();

    Optional<TagKey<Block>> getStorageBlockTag();

    Optional<TagKey<Item>> getOreItemTag();

    Optional<TagKey<Item>> getStorageBlockItemTag();

    Optional<TagKey<Item>> getRawMaterialTag();

    Optional<TagKey<Item>> getIngotTag();

    Optional<TagKey<Item>> getGemTag();

    Optional<TagKey<Item>> getNuggetTag();
}
