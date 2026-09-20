package net.jimmynet.jamesindustries.models;

import java.util.Optional;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

public final class ModModelTemplates {

    private static final ModelTemplate CUBE_BOTTOM_TOP_TEMPLATE = new ModelTemplate(
        ModelTemplates.CUBE_BOTTOM_TOP.model,
        Optional.empty(),
        TextureSlot.TOP,
        TextureSlot.BOTTOM,
        TextureSlot.SIDE);

    private static final ModelTemplate CUBE_TOP_TEMPLATE = new ModelTemplate(
        ModelTemplates.CUBE_TOP.model,
        Optional.empty(),
        TextureSlot.TOP,
        TextureSlot.SIDE
    );
    
    public static final TexturedModel.Provider cubeBottomTopTextureProvider(Block block) {
        return TexturedModel.createDefault(
            b -> new TextureMapping()
            .put(
                TextureSlot.TOP,
                TextureMapping.getBlockTexture(block, "_top")
            )
            .put(
                TextureSlot.BOTTOM,
                TextureMapping.getBlockTexture(block, "_bottom")
            )
            .put(
                TextureSlot.SIDE,
                TextureMapping.getBlockTexture(block, "_side")
            ),
            CUBE_BOTTOM_TOP_TEMPLATE);
    }

    public static final TexturedModel.Provider cubeTopTextureProvider(Block block) {
        return TexturedModel.createDefault(
            b -> new TextureMapping()
            .put(
                TextureSlot.TOP,
                TextureMapping.getBlockTexture(block, "_top")
            )
            .put(
                TextureSlot.SIDE,
                TextureMapping.getBlockTexture(block, "_side")
            ),
            CUBE_TOP_TEMPLATE);
    }

    private ModModelTemplates() {}
}
