package net.jimmynet.jamesindustries.gametest;

import net.jimmynet.jamesindustries.entity.ModEntities;
import net.jimmynet.jamesindustries.entity.ai.interaction.SetRabbitVariantInteraction;
import net.jimmynet.jamesindustries.entity.passive.PetRabbitEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;

public final class SetRabbitVariantInteractionGameTests {
    private SetRabbitVariantInteractionGameTests() {}

    private static final int STACK_SIZE = 8;

    /*
     * Successful Completion
     * - #succeed	The test is marked as successful.
     * - #succeedIf	The supplied Runnable is tested immediately and succeeds if
     * no GameTestAssertException is thrown.
     * If the test does not succeed on the immediate tick, then it is marked as a failure.
     * - #succeedWhen The supplied Runnable is tested every tick until timeout and succeeds
     * if the check on one of the ticks does not throw a GameTestAssertException.
     * - #succeedOnTickWhen	The supplied Runnable is tested on the specified tick and will succeed
     * if no GameTestAssertException is thrown.
     * If the Runnable succeeds on any other tick, then it is marked as a failure.
     */

    public static void sameVariantPassesAndConsumesNothing(GameTestHelper helper) {
        PetRabbitEntity petRabbit = helper.spawn(
            ModEntities.PET_RABBIT.get(),
            new BlockPos(1, 1, 1)
        );

        petRabbit.setVariant(Rabbit.Variant.WHITE);

        Player player = helper.makeMockPlayer(GameType.SURVIVAL);

        ItemStack stack = new ItemStack(
            Items.IRON_INGOT,
            STACK_SIZE
        );

        InteractionResult result = SetRabbitVariantInteraction.interactionChangeVariant(
            player,
            stack,
            petRabbit
        );

        helper.assertValueEqual(
            InteractionResult.PASS,
            result,
            "interaction result"
        );
        helper.assertValueEqual(Rabbit.Variant.WHITE,
            petRabbit.getVariant(),
            "Rabbit Variant"
        );
        helper.assertValueEqual(
            STACK_SIZE,
            stack.getCount(),
            "Stack Size"
        );

        helper.succeed();
    }

    // public static void validInteractionConsumesExactlyOne(GameTestHelper helper) {

    // }

    // public static void creativeConsumptionIsIntended(GameTestHelper helper) {

    // }
}