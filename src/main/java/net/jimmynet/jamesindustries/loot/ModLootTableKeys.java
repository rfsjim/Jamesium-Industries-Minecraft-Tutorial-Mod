package net.jimmynet.jamesindustries.loot;

import net.jimmynet.jamesindustries.helpers.ModResourceKeys;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * 
 * ModLootTableKeys Shared Runtime Data Definition as a reference for LootTableKeys
 */
public final class ModLootTableKeys {

    public static final ResourceKey<LootTable> PET_RABBIT_EASTER_GIFT = ModResourceKeys.create(
        Registries.LOOT_TABLE,
        "pet_rabbit_easter_gift"
    );

    public static final ResourceKey<LootTable> PET_RABBIT_GOLD = ModResourceKeys.create(
        Registries.LOOT_TABLE,
        "pet_rabbit_gold"
    );
    
    public static final ResourceKey<LootTable> PET_RABBIT_WHITE = ModResourceKeys.create(
        Registries.LOOT_TABLE,
        "pet_rabbit_white"
    );

    public static final ResourceKey<LootTable> PET_RABBIT_BROWN = ModResourceKeys.create(
        Registries.LOOT_TABLE,
        "pet_rabbit_brown"
    );

    public static final ResourceKey<LootTable> PET_RABBIT_WHITE_SPLOTCHED = ModResourceKeys.create(
        Registries.LOOT_TABLE,
        "pet_rabbit_white_splotched"
    );

    public static final ResourceKey<LootTable> PET_RABBIT_BLACK = ModResourceKeys.create(
        Registries.LOOT_TABLE,
        "pet_rabbit_black"
    );

    public static final ResourceKey<LootTable> PET_RABBIT_SALT = ModResourceKeys.create(
       Registries.LOOT_TABLE,
        "pet_rabbit_salt"
    );

    public static final ResourceKey<LootTable> PET_RABBIT_EVIL = ModResourceKeys.create(
        Registries.LOOT_TABLE,
        "pet_rabbit_evil"
    );

    public static  final ResourceKey<LootTable> NO_LOOT = ModResourceKeys.create(
        Registries.LOOT_TABLE,
        "no_loot"
    );

    private ModLootTableKeys() {}
}