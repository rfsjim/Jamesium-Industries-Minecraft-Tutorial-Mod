package net.jimmynet.jamesindustries.models;

import java.util.Optional;

import net.jimmynet.jamesindustries.block.ModBlocks;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;

public final class ModModelTemplates {

    private static final ModelTemplate RED_ORE_TEMPLATE = new ModelTemplate(
        ModelTemplates.CUBE_BOTTOM_TOP.model,
        Optional.empty(),
        TextureSlot.TOP,
        TextureSlot.BOTTOM,
        TextureSlot.SIDE);
    
    public static final TexturedModel.Provider red_block_texture_provider = TexturedModel.createDefault(
            block -> new TextureMapping()
                .put(
                    TextureSlot.TOP,
                    TextureMapping.getBlockTexture(ModBlocks.RED_ORE_BLOCK.get(), "_top"))
                .put(
                    TextureSlot.BOTTOM,
                    TextureMapping.getBlockTexture(ModBlocks.RED_ORE_BLOCK.get(), "_bottom"))
                .put(
                    TextureSlot.SIDE,
                    TextureMapping.getBlockTexture(ModBlocks.RED_ORE_BLOCK.get(), "_side")),
                RED_ORE_TEMPLATE
        );

    private ModModelTemplates() {}
}
