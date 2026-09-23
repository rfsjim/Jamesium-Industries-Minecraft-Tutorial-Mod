package net.jimmynet.jamesindustries.datagen;

import java.util.concurrent.CompletableFuture;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.block.ModBlocks;
import net.jimmynet.jamesindustries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, JamesiumIndustries.MODID);
    }

    @Override 
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SWORDS).add(ModItems.NETHER_SWORD.get());

        tag(ItemTags.LOGS_THAT_BURN).add(ModBlocks.MAPLE_LOG_BLOCK.get().asItem());
    }
}