package net.jimmynet.jamesindustries.datagen;

import java.util.function.BiConsumer;

import net.jimmynet.jamesindustries.loot.ModLootTableKeys;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

/**
 * 
 * ModLootTableSubProvider Custom Loot Tables
 */
public class ModLootTableSubProvider implements LootTableSubProvider {
    protected final HolderLookup.Provider lookupProvider;
    private static final ConstantValue ONE_ROLL = ConstantValue.exactly(1);
    
    public ModLootTableSubProvider(HolderLookup.Provider lookupProvider) {
        this.lookupProvider = lookupProvider;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_SEASONAL_GIFT,
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                .setRolls(ONE_ROLL)
                .add(LootItem.lootTableItem(Items.EMERALD))
                .add(LootItem.lootTableItem(Items.DIAMOND))
            )
        );
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_GOLD,
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                .setRolls(ONE_ROLL)
                .add(LootItem.lootTableItem(Items.GOLD_NUGGET))
            )
        );
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_BLACK,
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                .setRolls(ONE_ROLL)
                .add(LootItem.lootTableItem(Items.COAL))
            ));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_BROWN,
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                .setRolls(ONE_ROLL)
                .add(LootItem.lootTableItem(Items.GRASS_BLOCK))
                .add(LootItem.lootTableItem(Items.DIRT))
                .add(LootItem.lootTableItem(Items.COBBLESTONE))
            ));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_SALT,
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                .setRolls(ONE_ROLL)
                .add(LootItem.lootTableItem(Items.APPLE))
                .add(LootItem.lootTableItem(Items.CARROT))
                .add(LootItem.lootTableItem(Items.POTATO))
                .add(LootItem.lootTableItem(Items.BEETROOT))
                .add(LootItem.lootTableItem(Items.MELON))
                .add(LootItem.lootTableItem(Items.PUMPKIN))
            ));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_WHITE,
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                .setRolls(ONE_ROLL)
                .add(LootItem.lootTableItem(Items.IRON_NUGGET))
            ));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_WHITE_SPLOTCHED,
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                .setRolls(ONE_ROLL)
                .add(LootItem.lootTableItem(Items.FLINT))
                .add(LootItem.lootTableItem(Items.BIRCH_WOOD))
                .add(LootItem.lootTableItem(Items.GRANITE))
            ));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_EVIL,
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                .setRolls(ONE_ROLL)
                .add(LootItem.lootTableItem(Items.WITHER_SKELETON_SKULL))
            ));
        consumer.accept(
            ModLootTableKeys.NO_LOOT,
            LootTable.lootTable().withPool(
                LootPool.lootPool()
                .setRolls(ONE_ROLL)
                .add(EmptyLootItem.emptyItem())
            ));
    }
}