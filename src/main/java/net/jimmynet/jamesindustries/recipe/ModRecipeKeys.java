package net.jimmynet.jamesindustries.recipe;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;

public final class ModRecipeKeys {

    public static final ResourceKey<Recipe<?>> NETHER_BRICK_FROM_RED_ORE =
        ResourceKey.create(
            Registries.RECIPE,
            Identifier.fromNamespaceAndPath(
                JamesiumIndustries.MODID, "nether_brick_from_red_ore"
            )
        );

    public static final ResourceKey<Recipe<?>> SILVER_INGOT_FROM_SILVER_BLOCK =
        ResourceKey.create(
            Registries.RECIPE,
            Identifier.fromNamespaceAndPath(
                JamesiumIndustries.MODID, "silver_ingot_from_silver_block"
            )
        );

    public static final ResourceKey<Recipe<?>> SILVER_INGOT_FROM_SMELTING =
        ResourceKey.create(
            Registries.RECIPE,
            Identifier.fromNamespaceAndPath(
                JamesiumIndustries.MODID, "silver_ingot_from_smelting"
            )
        );

    private ModRecipeKeys() {}
}
