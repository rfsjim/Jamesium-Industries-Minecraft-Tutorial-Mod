package net.jimmynet.jamesindustries.gametest;

import java.util.function.Consumer;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModGameTestRegistry {
    private ModGameTestRegistry() {}

    public static final DeferredRegister<Consumer<GameTestHelper>> TEST_FUNCTIONS = DeferredRegister.create(
        BuiltInRegistries.TEST_FUNCTION,
        JamesiumIndustries.MODID
    );

    public static final DeferredHolder<Consumer<GameTestHelper>,
    Consumer<GameTestHelper>
    > SAME_VARIANT_PASSES_AND_CONSUMES_NOTHING =
    TEST_FUNCTIONS.register(
        "same_variant_passes_and_consumes_nothing",
        () -> SetRabbitVariantInteractionGameTests
        ::sameVariantPassesAndConsumesNothing
    );
}
