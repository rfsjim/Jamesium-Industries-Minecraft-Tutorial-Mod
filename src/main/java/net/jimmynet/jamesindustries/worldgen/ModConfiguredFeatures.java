package net.jimmynet.jamesindustries.worldgen;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfiguredFeatures {
    private ModConfiguredFeatures() {}

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SILVER_ORE = ResourceKey.create(
        Registries.CONFIGURED_FEATURE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "overworld_silver_ore")
    );

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        context.register(
            OVERWORLD_SILVER_ORE,
            new ConfiguredFeature<>(
                Feature.ORE,
                new OreConfiguration(
                    stoneReplaceables,
                    ModBlocks.SILVER_ORE_BLOCK.get().defaultBlockState(),
                    9
                )
            )
        );
    }
}
