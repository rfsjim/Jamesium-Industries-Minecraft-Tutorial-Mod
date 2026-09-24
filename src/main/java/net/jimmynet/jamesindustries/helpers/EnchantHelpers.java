package net.jimmynet.jamesindustries.helpers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public final class EnchantHelpers {
    private EnchantHelpers() {}

    public static ItemStack applyEnchantment(
        ItemStack stack,
        HolderLookup.Provider provider,
        ResourceKey<Enchantment> enchantment
    ) {
        return applyEnchantment(stack, provider, enchantment, 1);
    }

    public static ItemStack applyEnchantment(
        ItemStack stack,
        HolderLookup.Provider provider,
        ResourceKey<Enchantment> enchantment,
        int level
    ) {
        var enchantmentHolder = provider
            .lookupOrThrow(Registries.ENCHANTMENT)
            .getOrThrow(enchantment);
        
        stack.enchant(enchantmentHolder, level);

        return stack;
    }
}
