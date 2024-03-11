package io.github.ultreon.mods.materialistictools.item.tool.trait;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.ultreon.mods.materialistictools.stats.ModStats;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.PlayerDataStorage;
import org.jetbrains.annotations.NotNull;
import io.github.ultreon.mods.materialistictools.item.ItemType;
import io.github.ultreon.mods.materialistictools.item.material.ItemMaterial;
import io.github.ultreon.mods.materialistictools.item.tool.Toolset;

import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("unused")
public class InfinityTrait extends AbstractTrait {
    private static final float RAINBOW_LENGTH = 359;
    private static final UUID ATTACK_REACH_UUID = UUID.fromString("bea021b8-3105-488a-99af-7253074c191a");
    private static final UUID SWIM_SPEED_UUID = UUID.fromString("c8b6f101-b296-479d-9fbc-db7a35492c17");

    public InfinityTrait() {

    }

    @Override
    public boolean isEnchantable(Set<ItemType> type, ItemStack stack) {
        return false;
    }

    @Override
    public boolean onHitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, LivingEntity attacker) {
        if (attacker.level().isClientSide) {
            // Don't do anything on client.
            return true;
        }
        // Get victim
        DamageSource damageSource = victim.damageSources().genericKill();
        if (isInfinite(victim)) {
            victim.hurt(damageSource, 1f);
            return true;
        }
        //noinspection ConstantConditions
        if (victim.getItemInHand(InteractionHand.MAIN_HAND) != null && victim.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Toolset.INFINITY.getSword().get() && victim.isUsingItem()) {
            return true;
        }

        // Combat tracking.
        victim.getCombatTracker().recordDamage(damageSource, victim.getHealth());
        victim.setHealth(0);

        // Death event.
        victim.die(damageSource);

        return true;
    }

    @SuppressWarnings("RedundantIfStatement")
    public static boolean isInfinite(LivingEntity livingEntity) {
        // Get armor list.
        List<ItemStack> armor = (List<ItemStack>) livingEntity.getArmorSlots();

        // Check Armor
        if (!armor.isEmpty()) {
            // Check boots.
            if (armor.stream().allMatch(stack -> stack.is(Toolset.INFINITY))) {
                return true;
            }
        }
        // None or partial infinity armor.
        return false;
    }

    @Override
    public void onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (!entity.level().isClientSide && entity instanceof Player victim) {
            if (victim.isCreative() && !(victim.getHealth() <= 0) && victim.getHealth() > 0 && !isInfinite(victim)) {
                victim.getCombatTracker().recordDamage(player.damageSources().genericKill(), victim.getHealth());
                victim.setHealth(0);
                victim.die(player.damageSources().genericKill());
//                player.awardStat(ModStats.INFINITY_KILL.get(), 1); // TODO: Implement
            }
        } else if (!entity.level().isClientSide) {
            if (entity.tickCount > 100) {
                if (entity instanceof ItemEntity itemEntity) {
                    if (itemEntity.getItem().is(Toolset.INFINITY)) return;
                    if (itemEntity.getItem().is(ItemMaterial.INFINITY)) return;
                }
                entity.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
        player.level().destroyBlock(pos, true, player);
        return true;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }

    @Override
    public Float onLivingDamage(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        return null;
    }

    @Override
    public Float livingHurt(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        return null;
    }

    @Override
    public Component getTranslation() {
        return createRainbowTextComponent(super.getTranslation().getString());
    }

    public static MutableComponent createRainbowTextComponent(String text) {
        MutableComponent component = Component.literal("");

        // Loop over the characters in the text
        for (int i = 0; i < text.length(); i++) {
            int color = getColorForIndex(i, text.length());

            // Append a formatted text component with the appropriate color
            MutableComponent subcomponent = Component.literal(text.substring(i, i + 1));
            subcomponent.withStyle(style -> style.withColor(TextColor.fromRgb(color)));
            component.append(subcomponent);
        }

        return component;
    }

    private static int getColorForIndex(int index, int length) {
        float hue = (float) index / length * RAINBOW_LENGTH;
        return Color.HSBtoRGB(hue, 0.7F, 1F);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level dimension, Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 255, false, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 300, 255, false, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 19, false, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 1, false, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 300, 255, false, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 2, false, false, false));

        if (player instanceof ServerPlayer && InfinityTrait.isInfinite(player) && !player.getAbilities().mayfly) {
            player.addTag("materialistictools:fly");
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getArmorAttributeModifiers(EquipmentSlot slot) {
        return ImmutableMultimap.<Attribute, AttributeModifier>builder()
                .build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getToolAttributeModifiers(EquipmentSlot slot) {
        return ImmutableMultimap.<Attribute, AttributeModifier>builder()
                .build();
    }

    @Override
    public boolean shouldApplyGlint(Player player, ItemStack mainHandItem) {
        return true;
    }
}
