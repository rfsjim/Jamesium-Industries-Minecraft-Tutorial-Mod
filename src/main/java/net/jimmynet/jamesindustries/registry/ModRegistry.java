package net.jimmynet.jamesindustries.registry;

import net.jimmynet.jamesindustries.block.ModBlocks;
import net.jimmynet.jamesindustries.entity.ModEntities;
import net.jimmynet.jamesindustries.gametest.ModGameTestRegistry;
import net.jimmynet.jamesindustries.item.ModCreativeModeTabs;
import net.jimmynet.jamesindustries.item.ModItems;
import net.neoforged.bus.api.IEventBus;

public final class ModRegistry {

    private ModRegistry() {}

    public static void register(IEventBus modEventBus) {

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModGameTestRegistry.TEST_FUNCTIONS.register(modEventBus);
    }
}