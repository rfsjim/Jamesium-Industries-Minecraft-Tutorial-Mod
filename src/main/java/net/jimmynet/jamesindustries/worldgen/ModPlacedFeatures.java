package net.jimmynet.jamesindustries.worldgen;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    private ModPlacedFeatures() {}

    public static final ResourceKey<PlacedFeature> SILVER_ORE_PLACED_FEATURE = ResourceKey.create(
        Registries.PLACED_FEATURE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "silver_ore_placed_feature")
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
    }
}
