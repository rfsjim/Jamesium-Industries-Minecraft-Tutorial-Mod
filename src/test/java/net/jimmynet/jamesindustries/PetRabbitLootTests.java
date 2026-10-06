package net.jimmynet.jamesindustries;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.jimmynet.jamesindustries.loot.ModLootTableKeys;
import net.jimmynet.jamesindustries.loot.PetRabbitLoot;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.level.storage.loot.LootTable;

public final class PetRabbitLootTests {
    private PetRabbitLootTests() {}

    @Test
    void easterSundayShouldDropEasterGift() {

        Assertions.assertEquals(
            ModLootTableKeys.PET_RABBIT_EASTER_GIFT,
            PetRabbitLoot.getLootTableForVariant(
                Rabbit.Variant.GOLD,
                LocalDate.of(2026, 4, 5)
            )
        );
    }

    @Test
    void christmasShouldDropGoldGift() {

        Assertions.assertEquals(
            ModLootTableKeys.PET_RABBIT_GOLD,
            PetRabbitLoot.getLootTableForVariant(
                Rabbit.Variant.GOLD,
                LocalDate.of(2026, 12, 25)
            )
        );
    }

    @Test 
    void lootSelectionAtEasterShouldBeEasterGift() {
        LocalDate easterSaturday2027 = LocalDate.of(2027, 03, 27);

        for (Rabbit.Variant variant : Rabbit.Variant.values()) {
            Assertions.assertEquals(
                ModLootTableKeys.PET_RABBIT_EASTER_GIFT,
                PetRabbitLoot.getLootTableForVariant(variant, easterSaturday2027),
                "Incorrect loot for " + variant
            );
        }
    }

    @Test
    void lootSelectionAtChristmasShouldMatchEachVariant() {
        LocalDate christmasDay2027 = LocalDate.of(2027, 12, 25);
        Rabbit.Variant variants[] = Rabbit.Variant.values();

        HashMap<Rabbit.Variant, ResourceKey<LootTable>> validLootHashMap = new HashMap<Rabbit.Variant, ResourceKey<LootTable>>();

        validLootHashMap.put(Rabbit.Variant.GOLD, ModLootTableKeys.PET_RABBIT_GOLD);
        validLootHashMap.put(Rabbit.Variant.WHITE, ModLootTableKeys.PET_RABBIT_WHITE);
        validLootHashMap.put(Rabbit.Variant.BLACK, ModLootTableKeys.PET_RABBIT_BLACK);
        validLootHashMap.put(Rabbit.Variant.BROWN, ModLootTableKeys.PET_RABBIT_BROWN);
        validLootHashMap.put(Rabbit.Variant.WHITE_SPLOTCHED, ModLootTableKeys.PET_RABBIT_WHITE_SPLOTCHED);
        validLootHashMap.put(Rabbit.Variant.SALT, ModLootTableKeys.PET_RABBIT_SALT);
        validLootHashMap.put(Rabbit.Variant.EVIL, ModLootTableKeys.PET_RABBIT_EVIL);

        for (Rabbit.Variant variant : variants) {
            ResourceKey<LootTable> expected = validLootHashMap.getOrDefault(
                variant,
                ModLootTableKeys.NO_LOOT
            );
            
            Assertions.assertEquals(
                expected,
                PetRabbitLoot.getLootTableForVariant(
                    variant,
                    christmasDay2027
                ),
                "Incorrect loot for " + variant
            );
        }
    }
}