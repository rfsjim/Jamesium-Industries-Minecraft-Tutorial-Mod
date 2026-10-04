package net.jimmynet.jamesindustries.entity.ai.step;

import net.jimmynet.jamesindustries.entity.passive.PetRabbitEntity;
import net.jimmynet.jamesindustries.loot.PetRabbitLoot;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.storage.loot.LootTable;

public final class DropGiftStep {

    private DropGiftStep() {}

    public static void dropGift(ServerLevel serverLevel, PetRabbitEntity petRabbit) {

        ResourceKey<LootTable> lootTableId = PetRabbitLoot.getLootTableForVariant(petRabbit.getVariant());

        if (petRabbit.dropFromGiftLootTable(serverLevel, lootTableId, petRabbit::spawnAtLocation)) {
            petRabbit.playSound(
                SoundEvents.CHICKEN_EGG,
                1.0F,
                (petRabbit.nextFloat() - petRabbit.nextFloat()) * 0.2F + 1.0F
            );

            serverLevel.sendParticles(
                ParticleTypes.EGG_CRACK,
                petRabbit.getX(),
                petRabbit.getY(),
                petRabbit.getZ(),
                5,
                0.2,
                0.2,
                0.2,
                0
            );
        }
    }
}
