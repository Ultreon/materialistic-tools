package io.github.ultreon.mods.materialistictools.mixin;

import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public abstract class PlayerGlintMixin<T extends LivingEntity> {
    @Inject(method = "getRenderType", at = @At(
            value = "HEAD"
    ), cancellable = true)
    private void onGetRenderType(LivingEntity livingEntity, boolean bl, boolean bl2, boolean bl3, CallbackInfoReturnable<RenderType> cir) {
        if (livingEntity.getTags().contains(MaterialisticTools.MOD_ID + ":glint")) {
            cir.setReturnValue(RenderType.glint());
        }
    }
}
