package net.jimmynet.jamesindustries.entity.ai.goal;

import java.util.EnumSet;

import org.jspecify.annotations.Nullable;

import net.jimmynet.jamesindustries.entity.passive.PetRabbitEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class FollowPlayerGoal extends Goal {
    private static final TargetingConditions FOLLOW_PLAYER = TargetingConditions.forNonCombat().ignoreLineOfSight();
    private final TargetingConditions targetingConditions;
    private final PetRabbitEntity petRabbit;
    private @Nullable Player player;
    private final double speedModifier;
    private final double stopDistance = 2.5;
    private final double startDistance = 4.0;
    private final boolean canScare; 
    private int calmDown;
    private double px;
    private double py;
    private double pz;
    private double pRotX;
    private double pRotY;
    private boolean isRunning;
    private int timeToRecalcPath; 

    public FollowPlayerGoal(PetRabbitEntity petRabbit, double speedModifier, boolean canScare) {
        this(petRabbit, speedModifier, canScare, 2.5);
    }

    public FollowPlayerGoal(PetRabbitEntity petRabbit, double speedModifier, boolean canScare, double stopDistance) {
        this.petRabbit = petRabbit;
        this.speedModifier = speedModifier;
        this.canScare = canScare;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.targetingConditions = FOLLOW_PLAYER.copy().selector((target, level) -> this.shouldFollow(target));
    }

    private boolean shouldFollow(final LivingEntity player) {
        return true;
    }

    @Override
    public boolean canUse() {
        if (this.calmDown > 0) {
            --this.calmDown;
            return false;
        }
        
        double followRange = this.petRabbit.getAttributeValue(Attributes.TEMPT_RANGE);
        
        this.player = getServerLevel(this.petRabbit).getNearestPlayer(
            this.targetingConditions.range(followRange),
            this.petRabbit
        );
        
        if (this.player == null || !this.player.isAlive()) {
            return false;
        }
        
        return this.petRabbit.distanceToSqr(this.player) >
        this.startDistance * this.startDistance; 
    }

    @Override
    public boolean canContinueToUse() {
        if (this.player == null || !this.player.isAlive()) {
            return false;
        }
        
        double followRange = this.petRabbit.getAttributeValue(Attributes.TEMPT_RANGE);
        double distanceSquared = this.petRabbit.distanceToSqr(this.player);

        if (distanceSquared <= this.stopDistance * this.stopDistance) {
            return false;
        }

        if (distanceSquared > followRange * followRange) {
            return false;
        }

        if (this.canScare()) {
            if (this.petRabbit.distanceToSqr(this.player) < (double)36.0F) {
                if (this.player.distanceToSqr(this.px, this.py, this.pz) > 0.010000000000000002) {
                    return false;
                }

                if (
                    Math.abs((double)this.player.getXRot() - this.pRotX) > (double)5.0F ||
                    Math.abs((double)this.player.getYRot() - this.pRotY) > (double)5.0F
                ) {
                    return false;
                }
            } else {
                this.px = this.player.getX();
                this.py = this.player.getY();
                this.pz = this.player.getZ();
            }

            this.pRotX = (double)this.player.getXRot();
            this.pRotY = (double)this.player.getYRot();
        }

        return distanceSquared > this.stopDistance * this.stopDistance &&
        distanceSquared <= followRange * followRange;
    }

    protected boolean canScare() {
        return this.canScare;
    }

    @Override 
    public void start() {
        this.px = this.player.getX();
        this.py = this.player.getY();
        this.pz = this.player.getZ();
        this.isRunning = true;
        this.timeToRecalcPath = 0;
    }

    @Override 
    public void stop() {
        this.player = null;
        this.stopNavigation();
        this.calmDown = reducedTickDelay(100);
        this.isRunning = false;
    }

    @Override 
    public void tick() {
        this.petRabbit.getLookControl().setLookAt(
            this.player,
            (float)(this.petRabbit.getMaxHeadYRot() + 20),
            (float)(this.petRabbit.getMaxHeadXRot())
        );
        if (this.petRabbit.distanceToSqr(this.player) < this.stopDistance * this.stopDistance) {
            this.stopNavigation();
        } else {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                this.navigateTowards(this.player);
            }
        }
    }

    protected void stopNavigation() {
        this.petRabbit.getNavigation().stop();
    }
    
    protected void navigateTowards(final Player player) {
        this.petRabbit.getNavigation().moveTo((Entity)player, this.speedModifier);
    }

    public boolean isRunning() {
        return this.isRunning;
    }

}
