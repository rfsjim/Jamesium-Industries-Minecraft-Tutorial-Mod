package net.jimmynet.jamesindustries.entity.ai.interaction;

import net.jimmynet.jamesindustries.entity.passive.PetRabbitEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public final class SetRabbitVariantInteraction {
    private SetRabbitVariantInteraction() {}

    public static InteractionResult interactionChangeVariant(Player player, ItemStack itemStack, PetRabbitEntity petRabbit) {
        Rabbit.Variant targetVariant = resolveTargetVariant(itemStack, petRabbit.getVariant());

        if (petRabbit.getVariant() == targetVariant) return InteractionResult.PASS;

        if (petRabbit.level() instanceof ServerLevel)
        {
            petRabbit.setVariant(targetVariant);
            itemStack.consume(1, player);
        }

        return  InteractionResult.CONSUME;
    }

    public static Rabbit.Variant resolveTargetVariant(ItemStack itemStack, Rabbit.Variant currentVariant) {
        if (itemStack.is(Items.GOLD_INGOT)) return Rabbit.Variant.GOLD;
        if (itemStack.is(Items.IRON_INGOT)) return Rabbit.Variant.WHITE;
        if (itemStack.is(Items.COAL)) return Rabbit.Variant.BLACK;
        if (itemStack.is(Items.DIRT)) return Rabbit.Variant.BROWN;
        if (itemStack.is(Items.FLINT)) return Rabbit.Variant.WHITE_SPLOTCHED;
        if (itemStack.is(Items.APPLE)) return Rabbit.Variant.SALT;
        if (itemStack.is(Items.WITHER_ROSE)) return Rabbit.Variant.EVIL;

        return currentVariant;
    } 
}
