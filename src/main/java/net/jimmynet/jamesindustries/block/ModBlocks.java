package net.jimmynet.jamesindustries.block;

import net.jimmynet.jamesindustries.JamesiumIndustries;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 
 * ModBlocks - Creates new blocks for the mod
 */
public final class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
        DeferredRegister.createBlocks(JamesiumIndustries.MODID);

    public static final DeferredBlock<Block> RED_ORE_BLOCK = 
        BLOCKS.registerSimpleBlock(
            "red_ore_block",
            p -> p.mapColor(MapColor.STONE)
        );

    public static final DeferredBlock<Block> MAPLE_LOG_BLOCK =
        BLOCKS.registerSimpleBlock(
            "maple_log_block",
            p -> p
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(1)
            .sound(SoundType.WOOD)
            .ignitedByLava()
        );

    private ModBlocks() {}
}