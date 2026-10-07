package net.jimmynet.jamesindustries.gametest;

import java.util.function.Consumer;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.helpers.ModResourceKeys;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.gametest.framework.FunctionGameTestInstance;
import net.minecraft.gametest.framework.GameTestEnvironments;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Rotation;

public final class ModInstanceRabbitVariantInteraction {
    private ModInstanceRabbitVariantInteraction() {}

    public static final ResourceKey<GameTestInstance> SAME_VARIANT_PASSES_AND_CONSUMES_NOTHING = ModResourceKeys.create(
        Registries.TEST_INSTANCE,
        "same_variant_passes_and_consumes_nothing"
    );

    public static final ResourceKey<GameTestInstance> VALID_INTERACTION_CONSUMES_EXACTLY_ONE = ModResourceKeys.create(
        Registries.TEST_INSTANCE,
        "valid_interaction_consumes_exactly_one"
    );
    
    public static final ResourceKey<GameTestInstance> CREATIVE_CONSUMPTION_IS_INTENDED = ModResourceKeys.create(
        Registries.TEST_INSTANCE,
        "creative_consumption_is_intended"
    ); 

    public static void bootstrap(
        BootstrapContext<GameTestInstance> bootstrap
    ) {
            HolderGetter<TestEnvironmentDefinition> environments = bootstrap.lookup(Registries.TEST_ENVIRONMENT);

            bootstrap.register(
                SAME_VARIANT_PASSES_AND_CONSUMES_NOTHING,
                new FunctionGameTestInstance(
                    ModGameTestRegistry
                    .SAME_VARIANT_PASSES_AND_CONSUMES_NOTHING
                    .getKey(),
                    new TestData<>(
                        environments.getOrThrow(
                            GameTestEnvironments.DEFAULT_KEY
                        ),
                        Identifier.fromNamespaceAndPath(
                            JamesiumIndustries.MODID,
                            "rabbit_interaction_test"
                        ),
                        20,
                        0,
                        true
                    )
                )
            );

            bootstrap.register(
                VALID_INTERACTION_CONSUMES_EXACTLY_ONE,
                new FunctionGameTestInstance(
                    ModGameTestRegistry
                    .VALID_INTERACTION_CONSUMES_EXACTLY_ONE
                    .getKey(),
                    new TestData<>(
                        environments.getOrThrow(
                            GameTestEnvironments.DEFAULT_KEY
                        ),
                        Identifier.fromNamespaceAndPath(
                            JamesiumIndustries.MODID,
                            "rabbit_interaction_test"
                        ),
                        20,
                        0,
                        true
                    )
                )
            );

            bootstrap.register(
                CREATIVE_CONSUMPTION_IS_INTENDED,
                new FunctionGameTestInstance(
                    ModGameTestRegistry
                    .CREATIVE_CONSUMPTION_IS_INTENDED
                    .getKey(),
                    new TestData<>(
                        environments.getOrThrow(
                            GameTestEnvironments.DEFAULT_KEY
                        ),
                        Identifier.fromNamespaceAndPath(
                            JamesiumIndustries.MODID,
                            "rabbit_interaction_test"
                        ),
                        20,
                        0,
                        true
                    )
                )
            );
        }
    
    private static Holder.Reference<GameTestInstance> createGameTestInstanceReference(
        BootstrapContext<GameTestInstance> bootstrapContext,
        ResourceKey<GameTestInstance> gameTestInstanceKey,
        ResourceKey<Consumer<GameTestHelper>> gameTestFunctionKey,
        HolderGetter<TestEnvironmentDefinition> environments,
        ResourceKey<TestEnvironmentDefinition> gameTestEnvironmentKey,
        String structurePath,
        int maxTicks,
        int setupTicks,
        boolean isTestRequiredToSucceed,
        Rotation rotation,
        boolean onlyRunThroughTestCommand,
        int maxAttempts,
        int requiredSuccesses,
        boolean skyAccess
    ) {
        return bootstrapContext.register(
            gameTestInstanceKey,
            new FunctionGameTestInstance(
                gameTestFunctionKey,
                new TestData<> (
                    environments.getOrThrow(
                        gameTestEnvironmentKey
                    ),
                    Identifier.fromNamespaceAndPath(
                        JamesiumIndustries.MODID,
                        structurePath
                    ),
                    maxTicks,
                    setupTicks,
                    isTestRequiredToSucceed,
                    rotation,
                    onlyRunThroughTestCommand,
                    maxAttempts,
                    requiredSuccesses,
                    skyAccess
                )
            )
        );
    }
}