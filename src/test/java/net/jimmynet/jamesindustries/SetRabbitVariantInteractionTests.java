package net.jimmynet.jamesindustries;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import net.jimmynet.jamesindustries.entity.ai.interaction.SetRabbitVariantInteraction;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class SetRabbitVariantInteractionTests {
    private SetRabbitVariantInteractionTests() {}

    @ParameterizedTest 
    @MethodSource("variantCatalystItems")
    void validItemShouldResolveExpectedVariant(
        Item item,
        Rabbit.Variant expectedVariant
    ){
        ItemStack itemStack = new ItemStack(item);

        Assertions.assertEquals(
            expectedVariant,
            SetRabbitVariantInteraction.resolveTargetVariant(
                itemStack,
                Rabbit.Variant.WHITE_SPLOTCHED
            )
        );
    }

    private static Stream<Arguments> variantCatalystItems() {
        return Stream.of(
            Arguments.of(Items.GOLD_INGOT, Rabbit.Variant.GOLD),
            Arguments.of(Items.IRON_INGOT, Rabbit.Variant.WHITE),
            Arguments.of(Items.COAL, Rabbit.Variant.BLACK),
            Arguments.of(Items.DIRT, Rabbit.Variant.BROWN),
            Arguments.of(Items.FLINT, Rabbit.Variant.WHITE_SPLOTCHED),
            Arguments.of(Items.APPLE, Rabbit.Variant.SALT),
            Arguments.of(Items.WITHER_ROSE, Rabbit.Variant.EVIL)
        );
    }

    @Test 
    void unresolvedItemShouldNotChangeVariant() {
        Rabbit.Variant currentVariant = Rabbit.Variant.SALT;

        Rabbit.Variant productVariant = SetRabbitVariantInteraction.resolveTargetVariant(
            new ItemStack(Items.DIAMOND),
            currentVariant
        );
        
        Assertions.assertEquals(currentVariant, productVariant);
    }

    @Test
    void emptyItemStackShouldNotChangeVariant() {
        Rabbit.Variant currentVariant = Rabbit.Variant.EVIL;

        Rabbit.Variant productVariant = SetRabbitVariantInteraction.resolveTargetVariant(
            ItemStack.EMPTY,
            currentVariant
        );

        Assertions.assertEquals(currentVariant, productVariant);
    }

    @Test 
    void itemForCurrentVariantShouldNotChangeVariant() {
        Rabbit.Variant currentVariant = Rabbit.Variant.BROWN;

        Rabbit.Variant productVariant = SetRabbitVariantInteraction.resolveTargetVariant(
            new ItemStack(Items.DIRT),
            currentVariant);

        Assertions.assertEquals(currentVariant, productVariant);
    }
}