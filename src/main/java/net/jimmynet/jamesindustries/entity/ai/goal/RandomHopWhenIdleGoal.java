package net.jimmynet.jamesindustries.entity.ai.goal;

import net.jimmynet.jamesindustries.entity.passive.PetRabbitEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class RandomHopWhenIdleGoal extends Goal {
        private final PetRabbitEntity petRabbit;
        private int idleTimer;

        public RandomHopWhenIdleGoal(PetRabbitEntity petRabbit) {
            this.petRabbit = petRabbit;
            this.idleTimer = 0;
        }

        @Override
        public boolean canUse() {
            return PetRabbitEntity.nextFloat(petRabbit) < 0.02F &&
            petRabbit.onGround() &&
            !petRabbit.isInWater() &&
            !petRabbit.isInLava(); 
        }

        @Override
        public boolean canContinueToUse() {
            return this.idleTimer > 0;
        }

        @Override
        public void tick() {
            if (petRabbit.onGround() && !PetRabbitEntity.canJump(petRabbit)) {
                PetRabbitEntity.setCanJump(petRabbit, true);
            }
            
            if (idleTimer > 0) {
                --this.idleTimer;
            }
        }

        @Override 
        public void start() {
            petRabbit.jumpFromGround();
            idleTimer = 20 + PetRabbitEntity.nextInt(petRabbit, 20);
        }
    }