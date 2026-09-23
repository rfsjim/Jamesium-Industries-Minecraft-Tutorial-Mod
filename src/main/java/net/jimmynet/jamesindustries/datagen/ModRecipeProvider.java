package net.jimmynet.jamesindustries.datagen;

import java.util.concurrent.CompletableFuture;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.item.ModItems;
import net.jimmynet.jamesindustries.recipe.ModRecipeKeys;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;

import net.neoforged.neoforge.common.Tags.Items;

/**
 * 
 * ModRecipeProvider Custom mod crafting, smelting, anvil, brewing, smith-ing, stonecutting, transmuting recipes,
 * transform a set of objects into other objects 
 */
public class ModRecipeProvider extends RecipeProvider  {
    
    protected ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    @Override 
    protected void buildRecipes() {

        // Shapeless recipe for Pet Rabbit Spawn Egg
        ShapelessRecipeBuilder.shapeless(
            this.registries.lookupOrThrow(Registries.ITEM),
            RecipeCategory.MISC,
            ModItems.PET_RABBIT_SPAWN_EGG)
        .requires(Items.EGGS)
        .requires(Items.CROPS_CARROT)
        .unlockedBy("has_chicken_egg", this.has(Items.EGGS))
        .unlockedBy("has_carrot", this.has(Items.CROPS_CARROT))
        .save(this.output);

        // Furnace recipe for smelting Red Ore into Nether Bricks
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.RED_ORE_ITEM),
            RecipeCategory.BUILDING_BLOCKS,
            net.minecraft.world.item.Items.NETHER_BRICK,
            0.1f,
            10
        )
        .unlockedBy("has_red_ore", this.has(ModItems.RED_ORE_ITEM))
        .save(this.output,ModRecipeKeys.NETHER_BRICK_FROM_RED_ORE);

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.SILVER_ORE_ITEM),
            RecipeCategory.BUILDING_BLOCKS,
            ModItems.SILVER_INGOT,
            0.1f,
            10
        )
        .unlockedBy("has_silver_ore", this.has(ModItems.SILVER_ORE_ITEM))
        .save(this.output);
        
        // Shaped recipe
        ShapedRecipeBuilder.shaped(
            this.registries.lookupOrThrow(Registries.ITEM),
            RecipeCategory.COMBAT,
            ModItems.NETHER_SWORD
        )
        .pattern(" N ")
        .pattern(" N ")
        .pattern(" S ")
        .define('N', Items.BRICKS_NETHER)
        .define('S', net.minecraft.world.item.Items.STICK)
        .unlockedBy("has_nether_brick", this.has(Items.BRICKS_NETHER))
        .save(this.output);

        ShapedRecipeBuilder.shaped(
            this.registries.lookupOrThrow(Registries.ITEM),
            RecipeCategory.DECORATIONS,
            ModItems.SILVER_ITEM
        )
        .pattern("III")
        .pattern("III")
        .pattern("III")
        .define('I', ModItems.SILVER_INGOT)
        .unlockedBy("has_silver_ingot", this.has(ModItems.SILVER_INGOT))
        .save(this.output);
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override 
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new ModRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return JamesiumIndustries.MODID + "_recipes";
        }
    }
}