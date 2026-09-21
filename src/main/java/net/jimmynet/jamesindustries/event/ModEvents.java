package net.jimmynet.jamesindustries.event;

import java.util.List;
import java.util.Set;

import net.jimmynet.jamesindustries.datagen.ModLootTableSubProvider;
import net.jimmynet.jamesindustries.datagen.ModModelProvider;
import net.jimmynet.jamesindustries.datagen.ModRecipeProvider;
import net.jimmynet.jamesindustries.entity.ModEntities;
import net.jimmynet.jamesindustries.item.ModItems;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

/**
 * 
 * ModEvents event listeners and methods
 */
public final class ModEvents {

    private ModEvents() {}

    public static void registerListeners(IEventBus modEventBus) {

        modEventBus.addListener(ModEvents::gatherData);
        modEventBus.addListener(ModEvents::addCreative);
        modEventBus.addListener(ModEvents::registerAttributes);
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.RED_ORE_ITEM);
            event.accept(ModItems.MAPLE_LOG_ITEM);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.PET_RABBIT_SPAWN_EGG);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.NETHER_SWORD);
        }
    }

    private static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModModelProvider::new);
        event.createProvider(ModRecipeProvider.Runner::new);
        event.createProvider(
            (output, lookupProvider) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(
                    new LootTableProvider.SubProviderEntry(
                        ModLootTableSubProvider::new,
                        LootContextParamSets.EMPTY
                    )
                ),
                lookupProvider
            )
        );
    }

    private static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(
            ModEntities.PET_RABBIT.get(),
            Rabbit.createAttributes().build()
        );
    }
}