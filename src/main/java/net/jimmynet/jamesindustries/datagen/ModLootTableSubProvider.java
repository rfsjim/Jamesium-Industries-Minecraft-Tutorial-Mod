package net.jimmynet.jamesindustries.datagen;

import java.util.function.BiConsumer;

import net.jimmynet.jamesindustries.loot.ModLootTableKeys;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

/**
 * 
 * ModLootTableSubProvider Custom Loot Tables
 */
public class ModLootTableSubProvider implements LootTableSubProvider {
    protected final HolderLookup.Provider lookupProvider;
    private static final ConstantValue ONE_ROLL = ConstantValue.exactly(1);

    private record ItemEntry(ItemLike item, int weight) {
        private ItemEntry(ItemLike item) {
            this(item, 1);
        }
    }
    
    public ModLootTableSubProvider(HolderLookup.Provider lookupProvider) {
        this.lookupProvider = lookupProvider;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_EASTER_GIFT,
            LootTable.lootTable()
                .withPool(itemPool(
                    Items.EMERALD,
                    Items.DIAMOND
                )));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_GOLD,
            LootTable.lootTable()
                .withPool(itemPool(
                    Items.GOLD_NUGGET
                )));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_BLACK,
            LootTable.lootTable()
                .withPool(itemPool(
                    Items.COAL
                )));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_BROWN,
            LootTable.lootTable()
                .withPool(itemPool(
                    Items.GRASS_BLOCK,
                    Items.DIRT,
                    Items.COBBLESTONE
                )));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_SALT,
            LootTable.lootTable()
                .withPool(itemPool(
                    Items.APPLE,
                    Items.CARROT,
                    Items.POTATO,
                    Items.BEETROOT,
                    Items.MELON,
                    Items.PUMPKIN
                )));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_WHITE,
            LootTable.lootTable()
            .withPool(itemPool(
                Items.IRON_NUGGET
            )));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_WHITE_SPLOTCHED,
            LootTable.lootTable()
            .withPool(itemPool(
                Items.FLINT,
                Items.BIRCH_WOOD,
                Items.GRANITE
            )));
        consumer.accept(
            ModLootTableKeys.PET_RABBIT_EVIL,
            LootTable.lootTable()
            .withPool(itemPool(
                Items.WITHER_SKELETON_SKULL
            )));
        consumer.accept(
            ModLootTableKeys.NO_LOOT,
            LootTable.lootTable()
            .withPool(emptyPool(
            )));
    }

    /**
     * Creates a loot pool with the specified parameters.
     * @param rolls
     * @param itemCount
     * @param bonusRolls
     * @param entries
     * @return
     */
    private static LootPool.Builder itemPool(
        NumberProvider rolls,
        NumberProvider itemCount,
        NumberProvider bonusRolls,
        ItemEntry... entries
    ) {
        LootPool.Builder pool = LootPool.lootPool()
            .setRolls(rolls);

        if (bonusRolls != null) {
            pool.setBonusRolls(bonusRolls);
        }

        for (ItemEntry entry : entries) {
            LootItem.Builder<?> lootItem = LootItem.lootTableItem(entry.item())
                .setWeight(entry.weight());

            if (itemCount != null) {
                lootItem.apply(SetItemCountFunction.setCount(itemCount));
            }

            pool.add(lootItem);
        }

        return pool;
    }

    /**
     * Creates a loot pool with the specified items.
     * @param items The items to include in the pool.
     * @return The created loot pool.
     */
    private static LootPool.Builder itemPool(ItemLike... items) {
        return itemPool(
            ONE_ROLL,
            null,
            null,
            toEntries(items)
        );
    }

    private static LootPool.Builder itemPool(
        NumberProvider rolls,
        ItemLike... items
    ) {
        return itemPool(
            rolls,
            null,
            null,
            toEntries(items)
        );
    }

    private static LootPool.Builder itemPool(
        NumberProvider rolls,
        NumberProvider itemCount,
        ItemLike... items
    ) {
        return itemPool(
            rolls,
            itemCount,
            null,
            toEntries(items)
        );
    }

    private static LootPool.Builder weightedItemPool(
        NumberProvider rolls,
        ItemEntry... entries
    ) {
        return itemPool(
            rolls,
            null,
            null,
            entries
        );
    }

    private static LootPool.Builder weightedItemPool(
        NumberProvider rolls,
        NumberProvider itemCount,
        ItemEntry... entries
    ) {
        return itemPool(
            rolls,
            itemCount,
            null,
            entries
        );
    }

    private static LootPool.Builder emptyPool() {
        return LootPool.lootPool()
            .setRolls(ONE_ROLL)
            .add(EmptyLootItem.emptyItem());
    }

    private static ItemEntry[] toEntries(ItemLike... items) {
        ItemEntry[] entries = new ItemEntry[items.length];

        for (int i = 0; i < items.length; i++) {
            entries[i] = new ItemEntry(items[i]);
        }

        return entries;
    }

    private static ItemEntry weighted(ItemLike item, int weight) {
        return new ItemEntry(item, weight);
    }
}