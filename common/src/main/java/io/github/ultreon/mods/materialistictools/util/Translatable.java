package io.github.ultreon.mods.materialistictools.util;

import net.minecraft.network.chat.Component;

public interface Translatable {
    String getTranslationId();

    default Component getTranslation() {
        return Component.translatable(getTranslationId());
    }

    default String getTranslatedName() {
        return this.getTranslation().getString();
    }
}
