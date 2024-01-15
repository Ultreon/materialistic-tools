package io.github.ultreon.mods.materialistictools.item.tool.types;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import io.github.ultreon.mods.materialistictools.MaterialisticTools;
import io.github.ultreon.mods.materialistictools.item.ItemType;
import io.github.ultreon.mods.materialistictools.item.tool.TraitsItem;
import io.github.ultreon.mods.materialistictools.item.tool.trait.AbstractTrait;
import io.github.ultreon.mods.materialistictools.util.ItemUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
public class CustomHoe extends HoeItem implements TraitsItem {
    private final Supplier<List<AbstractTrait>> traits;
    private final double attackDamage;
    private final Supplier<ImmutableMultimap<Attribute, AttributeModifier>> toolAttributes;

    protected static final UUID ATTACK_KNOCKBACK_MODIFIER = UUID.nameUUIDFromBytes("Attack Knockback".getBytes());

    public CustomHoe(Tier tier, double attackDamageIn, double attackSpeedIn, Properties builderIn, Supplier<List<AbstractTrait>> traits) {
        super(tier, (int) attackDamageIn, (float) attackSpeedIn, builderIn);
        this.traits = traits;
        this.attackDamage = attackDamageIn + tier.getAttackDamageBonus();

        this.toolAttributes = Suppliers.memoize(() -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeedIn, AttributeModifier.Operation.ADDITION));
            for (AbstractTrait trait : this.getTraits()) {
                builder.putAll(trait.getToolAttributeModifiers(EquipmentSlot.MAINHAND));
            }

            return builder.build();
        });
    }

    @Override
    public Set<ItemType> getItemTypes() {
        return Set.of(ItemType.HOE);
    }

    @Override
    public List<AbstractTrait> getTraits() {
        return this.traits.get().stream().filter(abstractTrait -> abstractTrait.isApplicable(this.getItemTypes())).toList();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = super.useOn(context);
        for (AbstractTrait trait : this.getTraits()) {
            InteractionResult actionResultType = trait.onUseItem(context);
            result = ItemUtils.maxActionResult(result, actionResultType);
        }
        return result;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        if (this.isEnchantable(stack)) {
            return super.isBookEnchantable(stack, book);
        }
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        boolean val = super.isEnchantable(stack);
        for (AbstractTrait trait : this.getTraits()) {
            val |= trait.isEnchantable(this.getItemTypes(), stack);
        }
        return val;
    }

    @Override
    public boolean canBeDepleted() {
        boolean val = super.canBeDepleted();
        for (AbstractTrait trait : this.getTraits()) {
            val &= trait.isDamageable();
        }
        return val;
    }

    @Override
    public boolean isFireResistant() {
        boolean val = super.isFireResistant();
        for (AbstractTrait trait : this.getTraits()) {
            val |= trait.isImmuneToFire();
        }
        return val;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        if (this.canBeDepleted()) {
            super.setDamage(stack, damage);
            return;
        }
        if (this.isDamaged(stack)) {
            super.setDamage(stack, 0);
        }
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (this.canBeDepleted()) {
            return super.damageItem(stack, amount, entity, onBroken);
        }
        return 0;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level dimensionIn, Player playerIn, InteractionHand handIn) {
        boolean val = false;
        for (AbstractTrait trait : this.getTraits()) {
            val |= trait.onRightClick(this, dimensionIn, playerIn, handIn);
        }
        if (val) {
            return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
        }
        return InteractionResultHolder.fail(playerIn.getItemInHand(handIn));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity clicked) {
        float smite = 0f;
        for (AbstractTrait trait : this.getTraits()) {
            float smiteValue = trait.getSmiteValue(this.getItemTypes(), stack, player);
            if (smiteValue < 0f) {
                MaterialisticTools.LOGGER.warn("Smite value is less that zero, this can cause weird behavior");
            }

            smite += smiteValue;
            trait.onLeftClickEntity(stack, player, clicked);
        }

        if (smite > 0f) {
            if (clicked instanceof LivingEntity living) {
                if (living.getMobType() == MobType.UNDEAD) {
                    clicked.hurt(living.damageSources().playerAttack(player), smite);
                }
            }
        }

        return false;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity victim, LivingEntity attacker) {
        float smite = 0f;
        boolean val = super.hurtEnemy(stack, victim, attacker);
        for (AbstractTrait trait : this.getTraits()) {
            float smiteValue = trait.getSmiteValue(this.getItemTypes(), stack, attacker);
            if (smiteValue < 0f) {
                MaterialisticTools.LOGGER.warn("Smite value is less that zero, this can cause weird behavior");
            }

            smite += smiteValue;
            val &= trait.onHitEntity(stack, victim, attacker);
        }

        if (smite > 0f) {
            if (victim.getMobType() == MobType.UNDEAD) {
                if (attacker instanceof Player) {
                    victim.hurt(attacker.damageSources().playerAttack((Player) attacker), smite);
                } else {
                    victim.hurt(attacker.damageSources().mobAttack(attacker), smite);
                }
            }
        }

        if (val) {
            super.hurtEnemy(stack, victim, attacker);
        }

        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level dimension, Entity entity, int slot, boolean selected) {
        if (!this.canBeDepleted()) {
            this.setDamage(stack, 0);
        }

        for (AbstractTrait trait : this.getTraits()) {
            trait.onInventoryTick(stack, dimension, entity, slot, selected);
        }
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level dimension, BlockState state, BlockPos pos, LivingEntity living) {
        boolean op = super.mineBlock(stack, dimension, state, pos, living);
        for (AbstractTrait trait : this.getTraits()) {
            op |= trait.onBlockBroken(stack, dimension, state, pos, living);
        }
        return op;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, Player player) {
        boolean op = super.onDroppedByPlayer(stack, player);
        for (AbstractTrait trait : this.getTraits()) {
            op &= trait.onDroppedByPlayer(stack, player);
        }
        return op;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level dimension, List<Component> tooltip, TooltipFlag flag) {
        for (AbstractTrait trait : this.getTraits()) {
            tooltip.add(trait.getTranslation());
        }
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
        boolean val = super.onBlockStartBreak(stack, pos, player);
        for (AbstractTrait trait : this.getTraits()) {
            val |= trait.onBlockStartBreak(stack, pos, player);
        }
        return val;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        float val = super.getDestroySpeed(stack, state);
        for (AbstractTrait trait : this.getTraits()) {
            val *= trait.getDestroyMultiplier(this.getItemTypes(), stack, state);
        }
        for (AbstractTrait trait : this.getTraits()) {
            val += trait.getDestroyModifier(this.getItemTypes(), stack, state);
        }
        for (AbstractTrait trait : this.getTraits()) {
            val *= trait.getDestroyTotalMultiplier(this.getItemTypes(), stack, state);
        }
        return val;
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        Multimap<Attribute, AttributeModifier> attributes;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(this.toolAttributes.get());

        int knockback = 0;
        for (AbstractTrait trait : this.getTraits()) {
            knockback += trait.getKnockback(this.getItemTypes());
        }

        builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(ATTACK_KNOCKBACK_MODIFIER, "Tool modifier", knockback, AttributeModifier.Operation.ADDITION));
        attributes = builder.build();
        return equipmentSlot == EquipmentSlot.MAINHAND ? attributes : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    public float getAttackDamage() {
        return (float) this.attackDamage;
    }
}