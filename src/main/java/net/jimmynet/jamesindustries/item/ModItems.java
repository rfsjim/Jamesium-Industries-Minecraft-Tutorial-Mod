package net.jimmynet.jamesindustries.item;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.block.ModBlocks;
import net.jimmynet.jamesindustries.entity.ModEntities;
import net.jimmynet.jamesindustries.item.tools.ModToolMaterials;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(JamesiumIndustries.MODID);

    public static final DeferredItem<Item> MAGIC_CUBE = ITEMS.registerSimpleItem(
        "magic_cube",
        p -> p.food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    public static final DeferredItem<BlockItem> RED_ORE_ITEM = ITEMS.registerSimpleBlockItem(
        "red_ore_block",
        ModBlocks.RED_ORE_BLOCK);

    public static final DeferredItem<BlockItem> MAPLE_LOG_ITEM = ITEMS.registerSimpleBlockItem(
        "maple_log_block",
        ModBlocks.MAPLE_LOG_BLOCK);

    public static final DeferredItem<SpawnEggItem> PET_RABBIT_SPAWN_EGG = ITEMS.registerItem(
        "pet_rabbit_spawn_egg",
        p -> new SpawnEggItem(
            p.spawnEgg(ModEntities.PET_RABBIT.get())
        )
    );

    public static final DeferredItem<Item> NETHER_SWORD = ITEMS.registerItem(
        "nether_sword",
        p -> new Item(
            p.sword(
                ModToolMaterials.NETHER_MATERIAL,
                3,
                -2
                )
        )
    );

    private ModItems() {}
}
