package io.github.ultreon.mods.materialistictools.item.tool.trait;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.ultreon.mods.materialistictools.inject.data.ModEntityData;
import io.github.ultreon.mods.materialistictools.inject.EntityTraitDataInjection;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import io.github.ultreon.mods.materialistictools.item.ItemCategory;
import io.github.ultreon.mods.materialistictools.item.ItemType;
import io.github.ultreon.mods.materialistictools.item.tool.ModTraits;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public abstract class AbstractTrait {
    public static final AbstractTrait DEFAULT = new AbstractTrait() {
    };

    protected static final Marker MARKER = MarkerFactory.getMarker("Traits");

    private final List<ItemType> types;

    public AbstractTrait() {
        this.types = null;
    }

    public AbstractTrait(ItemType... types) {
        this.types = List.of(types);
    }

    public AbstractTrait(ItemCategory... categories) {
        List<ItemCategory> categoryList = List.of(categories);
        this.types = Arrays.stream(ItemType.values()).filter(type -> {
            for (ItemCategory category : type.getCategories()) {
                if (categoryList.contains(category)) {
                    return true;
                }
            }
            return false;
        }).toList();
    }

    public AbstractTrait(Predicate<ItemType> predicate) {
        this.types = Arrays.stream(ItemType.values()).filter(predicate).toList();
    }

    /**
     * Return false if you want item unable to be enchanted .
     *
     * @param type a set of item types of what the tool is
     * @param stack the item stack of the tool.
     * @return true to be enchantable, false otherwise.
     */
    public boolean isEnchantable(Set<ItemType> type, ItemStack stack) {
        return true;
    }

    /**
     * Called when a living entity was hit using an item that has this trait.
     *
     * @param stack    the item stack that has this trait.
     * @param victim   the victim (the living being that has been attacked).
     * @param attacker the attacker (the living being that attacked another living being).
     * @return ?
     */
    public boolean onHitEntity(@NotNull ItemStack stack, @NotNull LivingEntity victim, LivingEntity attacker) {
        return true;
    }

    /**
     * Called when a player has right-clicked with an item that has this trait.
     *
     * @param item      the item that has this trait.
     * @param dimension the dimension of the clicker.
     * @param clicker   the player that clicked.
     * @param hand      the hand where the item was held.
     * @return ?
     */
    public boolean onRightClick(Item item, Level dimension, Player clicker, InteractionHand hand) {
        return false;
    }

    /**
     * Called every tick when in inventory of an item that has this trait.
     *
     * @param stack       the item stack that is ticking.
     * @param dimensionIn the dimension where the tick happened.
     * @param entityIn    the owner of the item.
     * @param itemSlot    the slot index of the item in the inventory.
     * @param isSelected  true if the item is selected in the hotbar.
     */
    public void onInventoryTick(ItemStack stack, Level dimensionIn, Entity entityIn, int itemSlot, boolean isSelected) {

    }

    /**
     * Called every tick while equipped on an item slot for armor with an item that has this trait.
     *
     * @param stack     the item stack that is ticking.
     * @param dimension the dimension where the tick happened.
     * @param player    the owner of the item that has this trait.
     */
    public void onArmorTick(ItemStack stack, Level dimension, Player player) {

    }

    /**
     * Called when a block was broken by an item the item that has this trait.
     *
     * @param stack        the item that has this trait.
     * @param dimensionIn  the dimension where the block was broken.
     * @param state        the block state of the broken block.
     * @param pos          the position of the broken block.
     * @param entityLiving the entity that broke the block.
     * @return ?
     */
    public boolean onBlockBroken(ItemStack stack, Level dimensionIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        return true;
    }

    /**
     * Called when an item that has this trait was dropped.
     *
     * @param stack  the item that has this trait.
     * @param player the player that dropped the stack.
     * @return ?
     */
    public boolean onDroppedByPlayer(ItemStack stack, Player player) {
        return true;
    }

    /**
     * @return the translated name of this trait.
     */
    public Component getTranslation() {
        if (this.getRegistryName() == null) {
            return Component.translatable("misc.unknown");
        }
        MutableComponent translation = Component.translatable("smp_trait." + this.getRegistryName().getNamespace() + "." + this.getRegistryName().getPath().replaceAll("/", "."));
        translation.setStyle(translation.getStyle().withColor(this.getColor()));
        return translation;
    }

    /**
     * The color of the name.
     *
     * @return the color
     */
    public TextColor getColor() {
        return null;
    }

    /**
     * Get the registry name of this trait.
     *
     * @return a registry name.
     */
    @Nullable
    public ResourceLocation getRegistryName() {
        return ModTraits.REGISTER.getId(this);
    }

    /**
     * Return the registry type of the trait, the class {@linkplain AbstractTrait}.
     *
     * @return {@linkplain AbstractTrait}.
     */
    public Class<AbstractTrait> getRegistryType() {
        return AbstractTrait.class;
    }

    /**
     * Will be called when a player left-clicked an entity.
     *
     * @param stack  the item that has this trait.
     * @param player the clicker.
     * @param entity the entity that has been left-clicked by the player.
     */
    public void onLeftClickEntity(ItemStack stack, Player player, Entity entity) {

    }

    /**
     * Will be called when the player has started breaking a block with the item that has this trait.
     *
     * @param stack  the item that has this trait.
     * @param pos    the block's position.
     * @param player the player that has started breaking a block.
     * @return ?
     */
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
        return false;
    }

    /**
     * @return false if the item will be unbreakable, true is it is damageable.
     */
    public boolean isDamageable() {
        return true;
    }

    /**
     * @return will return true if the item bound to this trait will be immune to fire, false otherwise.
     */
    public boolean isImmuneToFire() {
        return false;
    }

    /**
     * Multiply the base destroy speed of the tool.
     *
     * @param type  the type of tool the item stack is.
     * @param stack the item that has this trait.
     * @param state the state of the block that is currently being mined.
     * @return the multiplication in block destroy speed. This will multiply before {@linkplain #getDestroyModifier(Set, ItemStack, BlockState)}.
     */
    public float getDestroyMultiplier(Set<ItemType> type, ItemStack stack, BlockState state) {
        return 1f;
    }

    /**
     * Modify the destroy speed using addition.
     *
     * @param type  the type of tool the item stack is.
     * @param stack the item that has this trait.
     * @param state the state of the block that is currently being mined.
     * @return the addition in block destroy speed.
     */
    public float getDestroyModifier(Set<ItemType> type, ItemStack stack, BlockState state) {
        return 0f;
    }

    /**
     * Multiply the total destroy speed of the tool.
     *
     * @param type  the type of tool the item stack is.
     * @param stack the item that has this trait.
     * @param state he state of the block that is currently being mined.
     * @return the multiplication of the total block destroy speed. This will multiply after {@linkplain #getDestroyModifier(Set, ItemStack, BlockState)}.
     */
    public float getDestroyTotalMultiplier(Set<ItemType> type, ItemStack stack, BlockState state) {
        return 1f;
    }

    /**
     * Return the smite value of the tool.
     * Smite is the attack damage against undead.
     * It's recommend to not use negative values as it might break
     */
    @Range(from = 0, to = Long.MAX_VALUE)
    public float getSmiteValue(Set<ItemType> smpItemTypes, ItemStack stack, LivingEntity attacker) {
        return 0f;
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    public @Nullable Float onLivingDamage(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        return amount;
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    public @Nullable Float livingHurt(ItemStack stack, LivingEntity entity, DamageSource source, float amount) {
        return amount;
    }

    /**
     * Called when this item is used when targeting a Block
     */
    public @NotNull InteractionResult onUseItem(UseOnContext context) {
        return InteractionResult.FAIL;
    }

    /**
     * Applies knockback to the item with this trait when attacking an entity with it.
     *
     * @param itemTypes the types that the item has.
     * @return the amount of knockback to apply.
     */
    public float getKnockback(Set<ItemType> itemTypes) {
        return 0f;
    }

    /**
     * Modifies the drops when breaking a block.
     *
     * @param stacks the current drops.
     * @param tool the tool used to break the block.
     * @param entity the entity that broke the block.
     * @param level the dimension where it happened.
     * @param state the state of the block that broke.
     * @return the modified drops.
     */
    public List<ItemStack> getDrops(List<ItemStack> stacks, ItemStack tool, Entity entity, Level level, BlockState state) {
        return stacks;
    }

    /**
     * Returns the attribute modifiers for armor.
     *
     * @param slot the slot to apply the modifiers for.
     * @return the attribute modifiers.
     */
    @ApiStatus.OverrideOnly
    public Multimap<? extends Attribute, ? extends AttributeModifier> getArmorAttributeModifiers(EquipmentSlot slot) {
        return ImmutableMultimap.of();
    }

    /**
     * Returns the attribute modifiers for tools (non-armor).
     *
     * @param slot the slot to apply the modifiers for.
     * @return the attribute modifiers.
     */
    @ApiStatus.OverrideOnly
    public Multimap<? extends Attribute, ? extends AttributeModifier> getToolAttributeModifiers(EquipmentSlot slot) {
        return ImmutableMultimap.of();
    }

    /**
     * Get whether the trait is applicable for the given item type.
     *
     * @param type the item type to check it for.
     * @return whether it's applicable.
     */
    public boolean isApplicable(ItemType type) {
        return this.types == null || this.types.contains(type);
    }

    /**
     * Get whether the trait is applicable for the given item types.
     *
     * @param types the item types to check it for.
     * @return whether it's applicable.
     */
    public boolean isApplicable(Set<ItemType> types) {
        boolean flag = false;
        if (types == null) return true;
        for (ItemType type : types) {
            flag |= this.isApplicable(type);
        }
        return flag;
    }

    public ModEntityData getTraitData(Entity entity) {
        return ((EntityTraitDataInjection)entity).getTraitData();
    }

    public boolean shouldApplyGlint(Player player, ItemStack mainHandItem) {
        return false;
    }
}
