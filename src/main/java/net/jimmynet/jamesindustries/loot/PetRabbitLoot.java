package net.jimmynet.jamesindustries.loot;

import net.jimmynet.jamesindustries.helpers.SeasonalHelpers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.level.storage.loot.LootTable;

public class PetRabbitLoot {
    private PetRabbitLoot() {}

    public static ResourceKey<LootTable> getLootTableForVariant(Rabbit.Variant variant) {

        SeasonalHelpers season = new SeasonalHelpers();

        if (season.isAroundEaster()) {
            return ModLootTableKeys.PET_RABBIT_SEASONAL_GIFT;
        }
        else {
            switch (variant) {
                case GOLD:
                    return ModLootTableKeys.PET_RABBIT_GOLD;
                case WHITE:
                    return ModLootTableKeys.PET_RABBIT_WHITE;
                case BLACK:
                    return ModLootTableKeys.PET_RABBIT_BLACK;
                case BROWN:
                    return ModLootTableKeys.PET_RABBIT_BROWN;
                case WHITE_SPLOTCHED:
                    return ModLootTableKeys.PET_RABBIT_WHITE_SPLOTCHED;
                case SALT:
                    return ModLootTableKeys.PET_RABBIT_SALT;
                case EVIL:
                    return ModLootTableKeys.PET_RABBIT_EVIL;
                default:
                return ModLootTableKeys.NO_LOOT;
            }
        }
    }
}
