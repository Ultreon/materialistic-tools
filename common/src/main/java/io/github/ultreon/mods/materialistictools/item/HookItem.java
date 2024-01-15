package io.github.ultreon.mods.materialistictools.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import io.github.ultreon.mods.materialistictools.item.tool.types.CustomSword;

import java.util.Collections;

public class HookItem extends CustomSword {
    public HookItem(Tier tier, int attackDamage, float speed, Item.Properties properties) {
        super(tier, attackDamage, speed, properties, Collections::emptyList);
    }

    @Override
    public float getKnockback() {
        return 1F;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        boolean b = super.onEntitySwing(stack, entity);
//        if (!b) {
//            LazyOptional<SMPLivingEntityData> capability = entity.getCapability(ModCapabilities.LIVING_ENTITY_DATA);
//            Optional<SMPLivingEntityData> resolve = capability.resolve();
//            if (resolve.isPresent()) {
//                SMPLivingEntityData data = resolve.get();
//                InteractionHand lastHookSwing = data.getLastHookSwing();
//                InteractionHand hand;
//                if (lastHookSwing == InteractionHand.MAIN_HAND) {
//                    hand = InteractionHand.OFF_HAND;
//                } else {
//                    hand = InteractionHand.MAIN_HAND;
//                }
//                if (entity.getItemInHand(hand).is(ModItems.SILVER_HOOK)) {
//
//                }
//                data.setLastHookSwing(hand);
//                entity.swing(hand);
//            }
//        }
        return b;
    }
}
