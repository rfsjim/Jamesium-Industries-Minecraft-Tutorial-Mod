package net.jimmynet.jamesindustries.loot;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * 
 * ModLootTableKeys Shared Runtime Data Definition as a reference for LootTableKeys
 */
public final class ModLootTableKeys {

    public static final ResourceKey<LootTable> PET_RABBIT_SEASONAL_GIFT = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_seasonal_gift")
    );

    public static final ResourceKey<LootTable> PET_RABBIT_GOLD = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_gold")
    );
    
    public static final ResourceKey<LootTable> PET_RABBIT_WHITE = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_white")
    );

    public static final ResourceKey<LootTable> PET_RABBIT_BROWN = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_brown")
    );

    public static final ResourceKey<LootTable> PET_RABBIT_WHITE_SPLOTCHED = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_white_splotched")
    );

    public static final ResourceKey<LootTable> PET_RABBIT_BLACK = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_black")
    );

    public static final ResourceKey<LootTable> PET_RABBIT_SALT = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_salt")
    );

    public static final ResourceKey<LootTable> PET_RABBIT_EVIL = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_evil")
    );

    public static  final ResourceKey<LootTable> NO_LOOT = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "no_loot")
    );

    private ModLootTableKeys() {}
}