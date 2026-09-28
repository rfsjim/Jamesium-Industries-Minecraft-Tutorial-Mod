package net.jimmynet.jamesindustries.worldgen;

import java.util.List;

import net.jimmynet.jamesindustries.helpers.ModResourceKeys;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;   
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

/**
 * 
 * ModPlacedFeatures - A placed feature describes where and how often to attempt generating it
 */
public class ModPlacedFeatures {
    private ModPlacedFeatures() {}

    public static final ResourceKey<PlacedFeature> SILVER_ORE_PLACED_FEATURE = ModResourceKeys.create(
        Registries.PLACED_FEATURE,
        "silver_ore_placed_feature"
    );

    public static final ResourceKey<PlacedFeature> RED_ORE_PLACED_FEATURE = ModResourceKeys.create(
        Registries.PLACED_FEATURE,
        "red_ore_placed_feature"
    );
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
            SILVER_ORE_PLACED_FEATURE,
            new PlacedFeature(
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SILVER_ORE),
                ModOrePlacement.commonOrePlacement(
                    12,
                    HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(80)
                    )
                )
            )
        );

        List<PlacementModifier> red_ore_placement = List.of(
            CountPlacement.of(32),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.matchesBlocks(Blocks.NETHERRACK),
                32),
            BiomeFilter.biome()
        );

        context.register(
            RED_ORE_PLACED_FEATURE,
            new PlacedFeature(
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_RED_ORE),
                red_ore_placement
            )
        );
    }
}
