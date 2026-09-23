package net.jimmynet.jamesindustries.datagen;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.block.ModBlocks;
import net.jimmynet.jamesindustries.item.ModItems;
import net.jimmynet.jamesindustries.models.ModModelTemplates;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

/**
 * 
 * ModModelProvider Models for blocks, and items get added with this class
 */
public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, JamesiumIndustries.MODID);
    }

    @Override
    protected void registerModels(
        BlockModelGenerators blockModels,
        ItemModelGenerators itemModels) {

        blockModels.createTrivialBlock(
            ModBlocks.RED_ORE_BLOCK.get(),
            ModModelTemplates.cubeBottomTopTextureProvider(ModBlocks.RED_ORE_BLOCK.get())
        );

        blockModels.createTrivialBlock(
            ModBlocks.SILVER_ORE_BLOCK.get(),
            ModModelTemplates.cubeAllTextureProvider(ModBlocks.SILVER_ORE_BLOCK.get())
        );

        blockModels.createTrivialBlock(
             ModBlocks.SILVER_BLOCK.get(),
            ModModelTemplates.cubeAllTextureProvider(ModBlocks.SILVER_BLOCK.get())
        );

        blockModels
            .woodProvider(ModBlocks.MAPLE_LOG_BLOCK.get())
            .logWithHorizontal(ModBlocks.MAPLE_LOG_BLOCK.get());

        itemModels.generateFlatItem(
            ModItems.MAGIC_CUBE.get(),
            ModelTemplates.FLAT_ITEM
        );

        itemModels.generateFlatItem(
            ModItems.PET_RABBIT_SPAWN_EGG.get(),
            ModelTemplates.FLAT_ITEM
        );

        itemModels.generateFlatItem(
            ModItems.NETHER_SWORD.get(),
            ModelTemplates.FLAT_HANDHELD_ITEM
        );

        itemModels.generateFlatItem(
            ModItems.SILVER_INGOT.get(),
            ModelTemplates.FLAT_ITEM
        );

    }
}



