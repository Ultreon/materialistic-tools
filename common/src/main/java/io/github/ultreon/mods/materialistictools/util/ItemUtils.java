package io.github.ultreon.mods.materialistictools.util;

import net.minecraft.world.InteractionResult;

public class ItemUtils {

    public static InteractionResult maxActionResult(InteractionResult result, InteractionResult actionResultType) {
        return result == InteractionResult.SUCCESS ? actionResultType : result;
    }
}
