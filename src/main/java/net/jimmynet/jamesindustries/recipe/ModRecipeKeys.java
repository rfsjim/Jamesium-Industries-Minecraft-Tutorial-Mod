package net.jimmynet.jamesindustries.recipe;

import net.jimmynet.jamesindustries.helpers.ModResourceKeys;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;

public final class ModRecipeKeys {

    public static final ResourceKey<Recipe<?>> NETHER_BRICK_FROM_RED_ORE =
        ModResourceKeys.create(
            Registries.RECIPE,
            "nether_brick_from_red_ore"
        );

    public static final ResourceKey<Recipe<?>> SILVER_INGOT_FROM_SILVER_BLOCK =
        ModResourceKeys.create(
            Registries.RECIPE,
            "silver_ingot_from_silver_block"
        );

    public static final ResourceKey<Recipe<?>> SILVER_INGOT_FROM_SMELTING =
        ModResourceKeys.create(
            Registries.RECIPE,
            "silver_ingot_from_smelting"
        );

    private ModRecipeKeys() {}
}
