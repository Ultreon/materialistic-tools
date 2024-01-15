//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.ultreon.mods.materialistictools.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.RenderType;

@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface RenderTypeHolder {
    @Environment(EnvType.CLIENT)
    RenderType getRenderType();
}
