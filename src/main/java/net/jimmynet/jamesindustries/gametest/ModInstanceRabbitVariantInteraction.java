package net.jimmynet.jamesindustries.gametest;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.gametest.framework.FunctionGameTestInstance;
import net.minecraft.gametest.framework.GameTestEnvironments;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public final class ModInstanceRabbitVariantInteraction {
    private ModInstanceRabbitVariantInteraction() {}

    public static final ResourceKey<GameTestInstance> MOD_INSTANCE_RABBIT_VARIANT_INTERACTION = ResourceKey.create(
        Registries.TEST_INSTANCE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "mod_instance_rabbit_variant_interaction")
    );

    public static void bootstrap(
        BootstrapContext<GameTestInstance> bootstrap
    ) {
            HolderGetter<TestEnvironmentDefinition> environments = bootstrap.lookup(Registries.TEST_ENVIRONMENT);

            bootstrap.register(
                MOD_INSTANCE_RABBIT_VARIANT_INTERACTION,
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
        }
}
