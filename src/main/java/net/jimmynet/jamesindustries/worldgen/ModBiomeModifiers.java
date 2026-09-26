package net.jimmynet.jamesindustries.worldgen;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    private ModBiomeModifiers() {}

    public static final ResourceKey<BiomeModifier> ADD_SILVER_ORE = ResourceKey.create(
        NeoForgeRegistries.Keys.BIOME_MODIFIERS,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "add_silver_ore")
    );

    public static final void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        context.register(
            ADD_SILVER_ORE,
            new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SILVER_ORE_PLACED_FEATURE)),
                GenerationStep.Decoration.UNDERGROUND_ORES
            )
        );
    }
}
