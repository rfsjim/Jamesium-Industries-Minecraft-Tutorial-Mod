package net.jimmynet.jamesindustries.datagen;

import java.util.Set;

import net.jimmynet.jamesindustries.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class ModBlockLootSubProvider extends BlockLootSubProvider  {
    
    public ModBlockLootSubProvider(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
    }

    @Override 
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
        .stream()
        .map(e -> (Block) e.value())
        .toList();
    }

    @Override 
    protected void generate() {
        this.dropSelf(ModBlocks.MAPLE_LOG_BLOCK.get());
        this.dropSelf(ModBlocks.RED_ORE_BLOCK.get());
        this.dropSelf(ModBlocks.SILVER_ORE_BLOCK.get());
        this.dropSelf(ModBlocks.SILVER_BLOCK.get());
    }
}
