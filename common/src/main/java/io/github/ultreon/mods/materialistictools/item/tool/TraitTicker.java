package io.github.ultreon.mods.materialistictools.item.tool;

import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.item.tool.trait.AbstractTrait;
import io.github.ultreon.mods.materialistictools.item.tool.trait.InfinityTrait;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class TraitTicker {
    public static void tick(Player player) {
        TraitTicker.tickFlightAbility(player);
    }

    private static void tickFlightAbility(Player player) {
        if (player instanceof ServerPlayer && player.getAbilities().mayfly && player.getTags().contains("materialistictools:fly")) {
            if (InfinityTrait.isInfinite(player)) return;

            player.getAbilities().mayfly = false;
            player.onUpdateAbilities();
            player.removeTag("materialistictools:fly");
        }

        if (!player.getTags().contains("materialistictools:glint")) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (mainHandItem.getItem() instanceof TraitsItem traitsItem) {
                List<AbstractTrait> traits = traitsItem.getTraits();
                traits.stream()
                        .filter(trait -> trait.shouldApplyGlint(player, mainHandItem))
                        .findAny()
                        .ifPresentOrElse(
                                trait -> player.addTag(MaterialisticTools.MOD_ID + ":glint"),
                                () -> player.removeTag(MaterialisticTools.MOD_ID + ":glint")
                        );
            } else {
                player.removeTag(MaterialisticTools.MOD_ID + ":glint");
            }
        }
    }
}
