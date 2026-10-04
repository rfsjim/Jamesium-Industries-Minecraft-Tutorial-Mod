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
        Rabbit.Variant targetVariant = setTargetVariant(itemStack, petRabbit);

        if (petRabbit.getVariant() == targetVariant) {
            return InteractionResult.PASS;
        }

        if (petRabbit.level() instanceof ServerLevel)
        {
            petRabbit.setVariant(targetVariant);
            itemStack.consume(1, player);
        }

        return  InteractionResult.CONSUME;
    }

    private static Rabbit.Variant setTargetVariant(ItemStack itemStack, PetRabbitEntity petRabbit) {
        if (itemStack.is(Items.GOLD_INGOT)) {
            return  Rabbit.Variant.GOLD;
        } else if (itemStack.is(Items.IRON_INGOT)) { 
            return  Rabbit.Variant.WHITE;
        } else if (itemStack.is(Items.COAL)) {
            return  Rabbit.Variant.BLACK;
        } else if (itemStack.is(Items.DIRT)) {
            return  Rabbit.Variant.BROWN;
        } else if (itemStack.is(Items.FLINT)) {
            return  Rabbit.Variant.WHITE_SPLOTCHED;
        } else if (itemStack.is(Items.APPLE)) {
            return  Rabbit.Variant.SALT;
        } else if (itemStack.is(Items.WITHER_ROSE)) {
            return  Rabbit.Variant.EVIL;
        } else {
            return petRabbit.getVariant();
        }
    }
}
