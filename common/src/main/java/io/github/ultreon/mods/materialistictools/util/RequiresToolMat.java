package io.github.ultreon.mods.materialistictools.util;

import io.github.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import org.jetbrains.annotations.Nullable;

public interface RequiresToolMat {
    @Nullable
    ToolRequirement getRequirement();
}
