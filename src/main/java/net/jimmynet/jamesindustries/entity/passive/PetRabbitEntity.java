package net.jimmynet.jamesindustries.entity.passive;

import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.jimmynet.jamesindustries.item.ModItems;
import net.jimmynet.jamesindustries.loot.PetRabbitLoot;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
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

        if (itemStack.isEmpty()) {
            
            if (!this.level().isClientSide() && this.level() instanceof ServerLevel serverLevel) {
                this.patRabbit(player, serverLevel);
            } 
            
            return InteractionResult.SUCCESS;
        } else if (this.isFood(itemStack)) {
            return super.mobInteract(player, hand);
        }

        if (itemStack.is(ModItems.SILVER_INGOT)) {
            if (this.getType().canSerialize() && !player.level().isClientSide() && this.isAlive()) {
                this.setCustomName(Component.literal("Pet Rabbit"));

                return InteractionResult.SUCCESS;
            }
        }

        if (this.getVariant() != Rabbit.Variant.EVIL || !itemStack.is(Items.WITHER_ROSE)) {
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
                this.getY()+ 0.5F,
                this.getZ() + 0.5F,
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
                this.getY()+ 0.5F,
                this.getZ() + 0.5F,
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
}