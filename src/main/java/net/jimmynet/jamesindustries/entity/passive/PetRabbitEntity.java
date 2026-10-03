package net.jimmynet.jamesindustries.entity.passive;

import java.util.EnumSet;

import org.jspecify.annotations.Nullable;

import net.jimmynet.jamesindustries.entity.ModEntities;
import net.jimmynet.jamesindustries.item.ModItems;
import net.jimmynet.jamesindustries.loot.PetRabbitLoot;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * 
 * PetRabbitEntity - Petting heals player and mob,
 * mob periodically drops resource gifts,
 * mob variant can be changed with items that are consumed on variant change.
 */
public class PetRabbitEntity extends Rabbit {

    private static final long PETTING_COOLDOWN_TICKS = 200L;
    private long nextPetTime = 0L;
    private static final int GIFT_INTERVAL_RANGE = 6000;
    public int giftTime;
    
    public PetRabbitEntity(EntityType<? extends Rabbit> entityType, Level level) {
        super(entityType, level);

        this.giftTime = this.random.nextInt(GIFT_INTERVAL_RANGE) + GIFT_INTERVAL_RANGE;
    }

    @Override 
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        
        ItemStack itemStack = player.getItemInHand(hand);

        if (this.bothHandsAreEmpty(player)) {
            if (this.level() instanceof ServerLevel serverLevel) {
                 // Genuine empty-handed petting.
                this.patRabbit(player, serverLevel);
            }

            return InteractionResult.SUCCESS;
        }

        if (itemStack.isEmpty()) {
            // This hand is empty but the other hand is not, don't pet the rabbit and check for other interactions.
            return InteractionResult.PASS;
        } 
        
        if (this.isFood(itemStack)) {
            return super.mobInteract(player, hand);
        }

        if (itemStack.is(ModItems.SILVER_INGOT) && this.getType().canSerialize() && this.isAlive()) {
            if (this.level() instanceof ServerLevel) {
                this.setCustomName(Component.literal("Pet Rabbit"));
            }
            return InteractionResult.SUCCESS;
        }

        if (this.getVariant() != Rabbit.Variant.EVIL && !itemStack.is(Items.WITHER_ROSE)) {
            InteractionResult variantResult = interactionChangeVariant(player, itemStack);
            
            if (variantResult != InteractionResult.PASS) {
                return variantResult;
            }
        }
        
        return super.mobInteract(player, hand);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level() instanceof ServerLevel serverLevel) {
            if (this.isAlive() && !this.isBaby() && --this.giftTime <=0)
            {
                this.dropGift(serverLevel);
            }
        }
    }

    private void patRabbit(Player player, ServerLevel serverLevel) {
        
        long currentTime = this.level().getGameTime();

        if (currentTime >= this.nextPetTime) {

            this.nextPetTime = currentTime + PETTING_COOLDOWN_TICKS;

            this.heal(2.0F);
            player.heal(2.0F);

            serverLevel.sendParticles(
                ParticleTypes.HEART,
                this.getX(),
                this.getY(),
                this.getZ(),
                5,
                0.2,
                0.2,
                0.2,
                0
            );
        } else {
            serverLevel.playSound(
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                SoundEvents.RABBIT_AMBIENT,
                SoundSource.NEUTRAL,
                0.5F,
                1.6F
            );
        }
    }

    private boolean bothHandsAreEmpty(Player player) {
        return player.getMainHandItem().isEmpty() &&
        player.getOffhandItem().isEmpty();
    }

    private void dropGift(ServerLevel serverLevel) {

        ResourceKey<LootTable> lootTableId = PetRabbitLoot.getLootTableForVariant(this.getVariant());

        if (this.dropFromGiftLootTable(serverLevel, lootTableId, this::spawnAtLocation)) {
            this.playSound(
                SoundEvents.CHICKEN_EGG,
                1.0F,
                (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F
            );

            serverLevel.sendParticles(
                ParticleTypes.EGG_CRACK,
                this.getX(),
                this.getY(),
                this.getZ(),
                5,
                0.2,
                0.2,
                0.2,
                0
            );
        }
        
        this.giftTime = this.random.nextInt(GIFT_INTERVAL_RANGE) + GIFT_INTERVAL_RANGE;
    }
 
    private InteractionResult interactionChangeVariant(Player player, ItemStack itemStack) {
        Rabbit.Variant targetVariant = setTargetVariant(itemStack);

        if (this.getVariant() == targetVariant) {
            return InteractionResult.PASS;
        }

        if (this.level() instanceof ServerLevel)
        {
            this.setVariant(targetVariant);
            itemStack.consume(1, player);
        }

        return  InteractionResult.CONSUME;
    }

    private Rabbit.Variant setTargetVariant(ItemStack itemStack) {
        if (itemStack.is(Items.GOLD_INGOT)) {
            return  Rabbit.Variant.GOLD;
        } else if (itemStack.is(Items.IRON_INGOT)) { 
            return  Rabbit.Variant.WHITE;
        } else if (itemStack.is(Items.COAL)) {
            return  Rabbit.Variant.BLACK;
        } else if (itemStack.is(Items.DIRT)) {
            return  Rabbit.Variant.BROWN;
        } else if (itemStack.is(Items.FLINT)) {
            return  Rabbit.Variant.WHITE_SPLOTCHED;
        } else if (itemStack.is(Items.APPLE)) {
            return  Rabbit.Variant.SALT;
        } else if (itemStack.is(Items.WITHER_ROSE)) {
            return  Rabbit.Variant.EVIL;
        } else {
            return this.getVariant();
        }
    }

    @Nullable
    @Override 
    public PetRabbitEntity getBreedOffspring(
        ServerLevel serverLevel,
        AgeableMob otherPetRabbitParent) {
        PetRabbitEntity offspring = ModEntities.PET_RABBIT.get().create(
            serverLevel,
            EntitySpawnReason.BREEDING
        );

        Rabbit.Variant variants[] = Rabbit.Variant.values();
        Rabbit.Variant variant;
        
        if (offspring != null) {
            do {
                variant = Util.getRandom(
                    variants,
                    this.random
                );
            } while (variant == Rabbit.Variant.EVIL);
            
            offspring.setVariant(variant);
        }

        return offspring;
    }

    @Override 
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(4, new PetRabbitEntity.FollowPlayer(this, 1.25, false));
        this.goalSelector.addGoal(9, new PetRabbitEntity.RandomHopWhenIdle(this));
    }

    public class FollowPlayer extends Goal {
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

        public FollowPlayer(PetRabbitEntity petRabbit, double speedModifier, boolean canScare) {
            this(petRabbit, speedModifier, canScare, 2.5);
        }

        public FollowPlayer(PetRabbitEntity petRabbit, double speedModifier, boolean canScare, double stopDistance) {
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

    public class RandomHopWhenIdle extends Goal {
        private final PetRabbitEntity petRabbit;
        private int idleTimer;

        public RandomHopWhenIdle(PetRabbitEntity petRabbit) {
            this.petRabbit = petRabbit;
            this.idleTimer = 0;
        }

        @Override
        public boolean canUse() {
            return petRabbit.random.nextFloat() < 0.02F &&
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
            if (petRabbit.onGround() && !((RabbitJumpControl) petRabbit.jumpControl).canJump()) {
                ((RabbitJumpControl) petRabbit.jumpControl).setCanJump(true);
            }
            
            if (idleTimer > 0) {
                --this.idleTimer;
            }
        }

        @Override 
        public void start() {
            petRabbit.jumpFromGround();
            idleTimer = 20 + petRabbit.random.nextInt(20);
        }
    }
}