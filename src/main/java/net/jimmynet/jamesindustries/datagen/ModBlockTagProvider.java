package net.jimmynet.jamesindustries.datagen;

import java.util.concurrent.CompletableFuture;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, JamesiumIndustries.MODID);
    }

    @Override 
    protected void addTags(HolderLookup.Provider provider)
    {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.RED_ORE_BLOCK.get())
            .add(ModBlocks.SILVER_ORE_BLOCK.get())
            .add(ModBlocks.SILVER_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
            .add(ModBlocks.MAPLE_LOG_BLOCK.get());
 
        this.tag(BlockTags.LOGS_THAT_BURN)
            .add(ModBlocks.MAPLE_LOG_BLOCK.get());
    }
}