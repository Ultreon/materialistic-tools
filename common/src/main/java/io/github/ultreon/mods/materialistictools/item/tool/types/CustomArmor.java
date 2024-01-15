package io.github.ultreon.mods.materialistictools.item.tool.types;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Streams;
import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import io.github.ultreon.mods.materialistictools.item.ItemType;
import io.github.ultreon.mods.materialistictools.item.tool.TraitsItem;
import io.github.ultreon.mods.materialistictools.item.tool.trait.AbstractTrait;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
public class CustomArmor extends ArmorItem implements TraitsItem {
    private final Supplier<List<AbstractTrait>> traits;
    private final Supplier<Multimap<Attribute, AttributeModifier>> modifiers;

    public CustomArmor(ArmorMaterial materialIn, Type slot, Properties builderIn, Supplier<List<AbstractTrait>> traits) {
        super(materialIn, slot, builderIn);
        this.traits = traits;
        this.modifiers = Suppliers.memoize(() -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(slot.getSlot()));
            for (AbstractTrait trait : this.getTraits()) {
                builder.putAll(trait.getArmorAttributeModifiers(slot.getSlot()));
            }

            return builder.build();
        });
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        super.inventoryTick(itemStack, level, entity, i, bl);
        
        if (!(entity instanceof Player player)) return;
        
        for (AbstractTrait trait : this.getTraits()) {
            trait.onArmorTick(itemStack, level, player);
        }
    }

    @Override
    public final List<AbstractTrait> getTraits() {
        return this.traits.get().stream().filter(abstractTrait -> abstractTrait.isApplicable(this.getItemTypes())).toList();
    }

    @Override
    public Set<ItemType> getItemTypes() {
        return switch (this.type) {
            case HELMET -> Set.of(ItemType.HELMET);
            case CHESTPLATE -> Set.of(ItemType.CHESTPLATE);
            case LEGGINGS -> Set.of(ItemType.LEGGINGS);
            case BOOTS -> Set.of(ItemType.BOOTS);
        };
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level dimension, List<Component> tooltip, TooltipFlag flag) {
        for (AbstractTrait trait : this.getTraits()) {
            tooltip.add(trait.getTranslation());
        }
    }

    static {
        EntityEvent.LIVING_HURT.register(CustomArmor::onLivingDamageEvent);
    }
    
    private static EventResult onLivingDamageEvent(LivingEntity livingEntity, DamageSource damageSource, float damage) {
        for (ItemStack stack : Streams.stream(livingEntity.getArmorSlots()).filter((itemStack) -> itemStack.getItem() instanceof CustomArmor).toList()) {
            CustomArmor item = (CustomArmor) stack.getItem();
            CompoundEventResult<Float> eventResult = item.livingDamage(stack, livingEntity, damageSource, damage);
            if (eventResult.interruptsFurtherEvaluation()) {
                Float object = eventResult.object();
                if (eventResult.object() != null) {
                    livingEntity.hurt(damageSource, object);
                }
                return EventResult.interruptFalse();
            }
        }
        return EventResult.interruptTrue();
    }

    private CompoundEventResult<Float> livingDamage(ItemStack stack, LivingEntity victim, DamageSource damageSource, final float damage) {
        float smite = 0f;
        float finalDamage = damage;
        for (AbstractTrait trait : this.getTraits()) {
            Float modified = trait.onLivingDamage(stack, victim, damageSource, finalDamage);
            if (modified == null)
                return CompoundEventResult.interruptFalse(null);

            modified = trait.livingHurt(stack, victim, damageSource, modified);
            if (modified == null)
                return CompoundEventResult.interruptFalse(null);

            finalDamage = modified;

            float smiteValue = trait.getSmiteValue(this.getItemTypes(), stack, victim);
            smite += smiteValue;
        }

        Entity directAttacker = damageSource.getDirectEntity();
        if (directAttacker instanceof LivingEntity mob && mob.getMobType() == MobType.UNDEAD && smite > 0.0F) {
            directAttacker.hurt(damageSource, smite);
        }
        return CompoundEventResult.interruptFalse(finalDamage);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == this.type.getSlot() ? this.modifiers.get() : ImmutableMultimap.of();
    }

    @Override
    public boolean canBeDepleted() {
        boolean val = super.canBeDepleted();
        for (AbstractTrait trait : this.getTraits()) {
            val &= trait.isDamageable();
        }
        return val;
    }
}
