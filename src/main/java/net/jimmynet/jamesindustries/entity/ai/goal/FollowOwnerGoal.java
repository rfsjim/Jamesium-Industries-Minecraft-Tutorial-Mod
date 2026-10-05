package net.jimmynet.jamesindustries.entity.ai.goal;

import javax.annotation.Nullable;

import net.jimmynet.jamesindustries.entity.passive.PetRabbitEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.PathType;

public class FollowOwnerGoal extends Goal {
    private final PetRabbitEntity petRabbit;
    private @Nullable LivingEntity owner;
    private final double stopDistance = 2.5;
    private final double startDistance = 4.0;
    private final double speedModifier;
    private final PathNavigation pathNavigation;
    private int timeToRecalcPath;
    private float oldWaterCost;

    public FollowOwnerGoal(PetRabbitEntity petRabbit, double speedModifier) {
        this.petRabbit = petRabbit;
        this.pathNavigation = petRabbit.getNavigation();
        this.speedModifier = speedModifier;
    }

    @Override 
    public boolean canUse() {
        LivingEntity owner = this.petRabbit.getOwner();

        if (!shouldFollow(owner)) {
            return false;
        } else if (this.petRabbit.distanceToSqr(owner) < this.startDistance * this.startDistance) {
            return false;
        } else {
            this.owner = owner;
            return true;
        }
    }

    @Override 
    public boolean canContinueToUse() {
        if (!shouldFollow(owner)) {
            return false;
        }
        if (this.pathNavigation.isDone()) {
            return false;
        }
        
        double followRange = this.petRabbit.getAttributeValue(Attributes.TEMPT_RANGE);
        double distanceSquared = this.petRabbit.distanceToSqr(this.owner);

        if (distanceSquared <= this.stopDistance * this.stopDistance) {
            return false;
        }
        if (distanceSquared > followRange * followRange) {
            return false;
        }

        return true;
    }

    @Override 
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.petRabbit.getPathfindingMalus(PathType.WATER);
        this.petRabbit.setPathfindingMalus(PathType.WATER, 0F);
    }

    @Override 
    public void stop() {
        this.owner = null;
        this.pathNavigation.stop();
        this.petRabbit.setPathfindingMalus(PathType.WATER, this.oldWaterCost);
    }

    @Override 
    public void tick() {
        this.petRabbit.getLookControl().setLookAt(
            this.owner,
            (float)(this.petRabbit.getMaxHeadYRot() + 20),
            (float)(this.petRabbit.getMaxHeadXRot())
        );

        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.pathNavigation.moveTo(this.owner, this.speedModifier);
        }
    }

    private boolean shouldFollow(LivingEntity owner) {
        return petRabbit.isTame() && owner != null && owner.isAlive();
    }
}