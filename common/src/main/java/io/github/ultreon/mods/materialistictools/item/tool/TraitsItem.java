package io.github.ultreon.mods.materialistictools.item.tool;

import net.minecraft.world.level.ItemLike;
import io.github.ultreon.mods.materialistictools.item.ItemType;
import io.github.ultreon.mods.materialistictools.item.tool.trait.AbstractTrait;

import java.util.List;
import java.util.Set;

public interface TraitsItem extends ItemLike {
    List<AbstractTrait> getTraits();

    Set<ItemType> getItemTypes();
}
