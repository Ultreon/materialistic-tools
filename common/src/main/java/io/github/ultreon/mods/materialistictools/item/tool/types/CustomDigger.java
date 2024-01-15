package io.github.ultreon.mods.materialistictools.item.tool.types;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import io.github.ultreon.mods.materialistictools.item.tool.TraitsItem;
import io.github.ultreon.mods.materialistictools.item.tool.trait.AbstractTrait;

import java.util.List;

public interface CustomDigger extends TraitsItem {
    default List<ItemStack> getDrops(List<ItemStack> drops, ItemStack tool, Entity entity, Level level, BlockState state) {
        List<AbstractTrait> traits = this.getTraits();
        for (AbstractTrait trait : traits) {
            drops = trait.getDrops(drops, tool, entity, level, state);
        }
        return drops;
    }
}
