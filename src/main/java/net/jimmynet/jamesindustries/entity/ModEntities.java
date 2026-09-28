package net.jimmynet.jamesindustries.entity;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.entity.passive.PetRabbitEntity;
import net.jimmynet.jamesindustries.helpers.ModResourceKeys;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * 
 * ModEntities provides attributes for entities
 */
public class ModEntities {

    public static final DeferredRegister.Entities ENTITY_TYPES =
        DeferredRegister.createEntities(JamesiumIndustries.MODID);

    public static final Supplier<EntityType<PetRabbitEntity>> PET_RABBIT =
        ENTITY_TYPES.register(
            "pet_rabbit",
            () -> EntityType.Builder
                .of(PetRabbitEntity::new, MobCategory.CREATURE)
                .sized(0.4f, 0.5f)
                .build(
                    ModResourceKeys.create(
                        Registries.ENTITY_TYPE,
                        "pet_rabbit"
                    )
                )
        );

    private ModEntities() {}
}