package io.github.ultreon.mods.materialistictools.util;

import org.jetbrains.annotations.Nullable;
import io.github.ultreon.mods.materialistictools.item.tool.ToolType;

public interface RequiresToolType {
    @Nullable
    ToolType getToolType();
}
