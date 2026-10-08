package net.jimmynet.jamesindustries.gametest;

import java.util.function.Consumer;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.helpers.ModResourceKeys;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.gametest.framework.FunctionGameTestInstance;
import net.minecraft.gametest.framework.GameTestEnvironments;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

/* *********
 * TODO
 * Full Registration usage is available as a helper
 * See [master 061cbea] Refactor: registerFunctionGameTest assists in removing framework plumbing calls that are duplicated,
 * added additional method that makes all registration options available
 * ********* 
 */

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
            registerFunctionGameTest(
                bootstrap,
                SAME_VARIANT_PASSES_AND_CONSUMES_NOTHING,
                ModGameTestRegistry
                .SAME_VARIANT_PASSES_AND_CONSUMES_NOTHING
                .getKey()
            );

            registerFunctionGameTest(
                bootstrap,
                VALID_INTERACTION_CONSUMES_EXACTLY_ONE,
                ModGameTestRegistry
                .VALID_INTERACTION_CONSUMES_EXACTLY_ONE
                .getKey()
            );

            registerFunctionGameTest(
                bootstrap,
                CREATIVE_CONSUMPTION_IS_INTENDED,
                ModGameTestRegistry
                .CREATIVE_CONSUMPTION_IS_INTENDED
                .getKey()
            );
        }

    private static Holder.Reference<GameTestInstance> registerFunctionGameTest(
        BootstrapContext<GameTestInstance> bootstrapContext,
        ResourceKey<GameTestInstance> gameTestInstanceKey,
        ResourceKey<Consumer<GameTestHelper>> gameTestFunctionKey
    ) {
        return bootstrapContext.register(
            gameTestInstanceKey,
            new FunctionGameTestInstance(
                gameTestFunctionKey,
                new TestData<> (
                    bootstrapContext
                    .lookup(
                        Registries.TEST_ENVIRONMENT
                    )
                    .getOrThrow(
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
}