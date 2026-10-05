package net.jimmynet.jamesindustries.entity.ai.interaction;

import net.jimmynet.jamesindustries.entity.passive.PetRabbitEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public final class PatRabbitInteraction {

    private PatRabbitInteraction() {}

    public static  void patRabbit(Player player, ServerLevel serverLevel, PetRabbitEntity petRabbit) {
        
        long currentTime = petRabbit.level().getGameTime();

        if (petRabbit.tryBeginPettingCooldown(currentTime)) {

            petRabbit.heal(2.0F);
            player.heal(2.0F);

            serverLevel.sendParticles(
                ParticleTypes.HEART,
                petRabbit.getX(),
                petRabbit.getY(),
                petRabbit.getZ(),
                5,
                0.2,
                0.2,
                0.2,
                0
            );
        } else {
            serverLevel.playSound(
                null,
                petRabbit.getX(),
                petRabbit.getY(),
                petRabbit.getZ(),
                SoundEvents.RABBIT_AMBIENT,
                SoundSource.NEUTRAL,
                0.5F,
                1.6F
            );
        }
    }
}
