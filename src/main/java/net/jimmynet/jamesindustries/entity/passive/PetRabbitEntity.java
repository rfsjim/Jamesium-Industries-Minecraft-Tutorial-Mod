package net.jimmynet.jamesindustries.entity.passive;

import java.util.Optional;

import org.jspecify.annotations.Nullable;

import net.jimmynet.jamesindustries.entity.ModEntities;
import net.jimmynet.jamesindustries.entity.ai.interaction.PatRabbitInteraction;
import net.jimmynet.jamesindustries.entity.ai.interaction.SetRabbitVariantInteraction;
import net.jimmynet.jamesindustries.entity.ai.step.DropGiftStep;
import net.jimmynet.jamesindustries.entity.ai.goal.FollowOwnerGoal;
import net.jimmynet.jamesindustries.entity.ai.goal.RandomHopWhenIdleGoal;
import net.jimmynet.jamesindustries.item.ModItems;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/**
 * 
 * PetRabbitEntity - Petting heals player and mob,
 * mob periodically drops resource gifts,
 * mob variant can be changed with items that are consumed on variant change.
 */
public class PetRabbitEntity extends Rabbit {

    private static final int GIFT_INTERVAL_RANGE = 6000;
    private static final String GIFT_TIME_TAG = "GiftTime";
    private static final String OWNER_NAME_TAG = "OwnerName";
    private static final String RABBIT_TAMED_TAG = "RabbitTamed";
    private int giftTime;
    protected static final EntityDataAccessor<Optional<EntityReference<LivingEntity>>> DATA_OWNER_UUID_ID =
        SynchedEntityData.defineId(
            PetRabbitEntity.class,
            EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE
        );
    private boolean tamed;
    private static final long PETTING_COOLDOWN_TICKS = 200L;
    private long nextPetTime = 0L;
    
    public PetRabbitEntity(EntityType<? extends Rabbit> entityType, Level level) {
        super(entityType, level);

        this.giftTime = this.nextGiftTime();
        this.setTame(false);
    }

    private int nextGiftTime() {
        return this.random.nextInt(GIFT_INTERVAL_RANGE) + GIFT_INTERVAL_RANGE;
    }

    @Override 
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        
        ItemStack itemStack = player.getItemInHand(hand);

        if (this.bothHandsAreEmpty(player)) {
            if (this.level() instanceof ServerLevel serverLevel) {
                 // Genuine empty-handed petting.
                PatRabbitInteraction.patRabbit(player, serverLevel, this);
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

        if (itemStack.is(ModItems.SILVER_INGOT) && this.getType().canSerialize() && this.isAlive() && !this.isTame()) {
            if (this.level() instanceof ServerLevel) {
                this.tame(player);
                }

            return InteractionResult.SUCCESS;
        }

        if (this.getVariant() != Rabbit.Variant.EVIL && !itemStack.is(Items.WITHER_ROSE)) {
            InteractionResult variantResult = SetRabbitVariantInteraction.interactionChangeVariant(player, itemStack, this);
            
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
                DropGiftStep.dropGift(serverLevel, this);
                this.giftTime = this.nextGiftTime();
            }
        }
    }

    private boolean bothHandsAreEmpty(Player player) {
        return player.getMainHandItem().isEmpty() &&
        player.getOffhandItem().isEmpty();
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
        
        if (offspring != null && otherPetRabbitParent instanceof PetRabbitEntity parentRabbit) {
            int inheritanceRoll = this.random.nextInt(4);

            if (
                parentRabbit.getVariant() != Variant.EVIL &&
                this.getVariant() != Variant.EVIL &&
                inheritanceRoll < 3
            ) {
                if (this.random.nextBoolean()) {
                    offspring.setVariant(this.getVariant());
                } else {
                    offspring.setVariant(parentRabbit.getVariant());
                }
            } else {
                do {
                    variant = Util.getRandom(
                        variants,
                        this.random
                    );
                } while (variant == Rabbit.Variant.EVIL);
                offspring.setVariant(variant);
            }
            
            if (this.isTame() && parentRabbit.isTame()) {
                if (this.random.nextBoolean()) {
                    offspring.setOwnerReference(this.getOwnerReference());
                } else {
                    offspring.setOwnerReference(parentRabbit.getOwnerReference());
                }
                offspring.setTame(true);
            }
        }

        return offspring;
    }

    @Override 
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.25));
        this.goalSelector.addGoal(9, new RandomHopWhenIdleGoal(this));
    }

    public boolean canJump() {
        return ((RabbitJumpControl) this.jumpControl).canJump();
    }

    public void setCanJump(boolean canJump) {
        ((RabbitJumpControl) this.jumpControl).setCanJump(canJump);
    }

    public int nextInt(int bound) {
        return this.random.nextInt(bound);
    }

    public float nextFloat() {
        return this.random.nextFloat();
    }

    @Override
    public void setVariant(Rabbit.Variant targetVariant) {
        super.setVariant(targetVariant);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_OWNER_UUID_ID, Optional.empty());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.giftTime = input.getIntOr(
            GIFT_TIME_TAG,
            this.nextGiftTime()
        );
        EntityReference<LivingEntity> entityReference = 
            EntityReference.readWithOldOwnerConversion(
                input,
                OWNER_NAME_TAG,
                this.level()
        );

        if (entityReference != null) {
            try {
                this.entityData.set(
                    DATA_OWNER_UUID_ID,
                    Optional.of(entityReference)
                );
                this.setTame(true);
            } catch (Throwable t) {
                this.setTame(false);
            }
        } else {
            this.entityData.set(
                DATA_OWNER_UUID_ID,
                Optional.empty()
            );
            this.setTame(false);
        }

        this.tamed = input.getBooleanOr(
            RABBIT_TAMED_TAG,
            false
        );
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt(
            GIFT_TIME_TAG,
            this.giftTime
        );
        
        EntityReference<LivingEntity> entityReference = this.getOwnerReference();
        EntityReference.store(entityReference, output, OWNER_NAME_TAG);

        output.putBoolean(
            RABBIT_TAMED_TAG,
            this.tamed
        );
    }

    public boolean isTame() {
        return this.entityData.get(DATA_OWNER_UUID_ID).isPresent() && this.tamed;
    }

    private void tame(Player player) {
        this.setTame(true);
        this.setOwner(player);
    }

    private void setTame(boolean tamed) {
        this.tamed = tamed;
    }

    private void setOwner(@Nullable LivingEntity player) {
        this.entityData.set(
            DATA_OWNER_UUID_ID,
            Optional.ofNullable(player).map(EntityReference::of)
        );
    }

    private void setOwnerReference(@Nullable EntityReference<LivingEntity> player) {
        this.entityData.set(
            DATA_OWNER_UUID_ID,
            Optional.ofNullable(player)
        );
    }

    public boolean isOwnedBy(LivingEntity entity) {
        return entity == this.getOwner();
    }

    public @Nullable EntityReference<LivingEntity> getOwnerReference() {
        return this.entityData.get(DATA_OWNER_UUID_ID).orElse(null);
    }

    public @Nullable LivingEntity getOwner() {
        return EntityReference.getLivingEntity(
            this.getOwnerReference(),
            this.level()
        );
    }

    public boolean tryBeginPettingCooldown(long time) {
        if (time >= this.nextPetTime) {
            this.nextPetTime = time + PETTING_COOLDOWN_TICKS;
            return true;
        }

        return false;
    }
}