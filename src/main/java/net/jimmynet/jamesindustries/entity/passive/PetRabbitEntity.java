package net.jimmynet.jamesindustries.entity.passive;

import org.jspecify.annotations.Nullable;

import net.jimmynet.jamesindustries.entity.ModEntities;
import net.jimmynet.jamesindustries.entity.ai.interaction.PatRabbitInteraction;
import net.jimmynet.jamesindustries.entity.ai.interaction.SetRabbitVariantInteraction;
import net.jimmynet.jamesindustries.entity.ai.step.DropGiftStep;
import net.jimmynet.jamesindustries.entity.ai.goal.FollowPlayerGoal;
import net.jimmynet.jamesindustries.entity.ai.goal.RandomHopWhenIdleGoal;
import net.jimmynet.jamesindustries.item.ModItems;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

/**
 * 
 * PetRabbitEntity - Petting heals player and mob,
 * mob periodically drops resource gifts,
 * mob variant can be changed with items that are consumed on variant change.
 */
public class PetRabbitEntity extends Rabbit {

    private static final int GIFT_INTERVAL_RANGE = 6000;
    private int giftTime;
    
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

        if (itemStack.is(ModItems.SILVER_INGOT) && this.getType().canSerialize() && this.isAlive()) {
            if (this.level() instanceof ServerLevel) {
                this.setCustomName(Component.literal("Pet Rabbit"));
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
                this.giftTime = this.nextInt(PetRabbitEntity.GIFT_INTERVAL_RANGE) + PetRabbitEntity.GIFT_INTERVAL_RANGE;
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

        this.goalSelector.addGoal(4, new FollowPlayerGoal(this, 1.25, false));
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
}