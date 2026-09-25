package net.jimmynet.jamesindustries.helpers;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public final class EnchantHelpers {
    private EnchantHelpers() {}

    public static ItemStack applyEnchantmentValidated(
        ItemStack stack,
        HolderLookup.Provider provider,
        ResourceKey<Enchantment> enchantment
    ) {
        return applyEnchantmentValidated(stack, provider, enchantment, 1);
    }

    public static ItemStack applyEnchantmentValidated(
        ItemStack stack,
        HolderLookup.Provider provider,
        ResourceKey<Enchantment> enchantment,
        int enchantmentLevel
    ) {
        var holder = getEnchantment(provider, enchantment);
        requireValidLevel(enchantmentLevel);

        if (!stack.supportsEnchantment(holder)) {
            return stack;
        }

        return setEnchantment(stack, holder, enchantmentLevel);
    }

    public static ItemStack applyEnchantmentUnchecked(
        ItemStack stack,
        HolderLookup.Provider provider,
        ResourceKey<Enchantment> enchantment
    ) {
        return applyEnchantmentUnchecked(stack, provider, enchantment, 1);
    }

    public static ItemStack applyEnchantmentUnchecked(
        ItemStack stack,
        HolderLookup.Provider provider,
        ResourceKey<Enchantment> enchantment,
        int enchantmentLevel
    ) {
        var holder = getEnchantment(provider, enchantment);
        requireValidLevel(enchantmentLevel);

        return setEnchantment(stack, holder, enchantmentLevel);
    }

    private static ItemStack setEnchantment(
        ItemStack stack,
        Holder<Enchantment> enchantment,
        int level
    ) {
        stack.enchant(enchantment, level);
        return  stack;
    }

    private static Holder<Enchantment> getEnchantment(
        HolderLookup.Provider provider,
        ResourceKey<Enchantment> enchantment
    ) {
        return provider
            .lookupOrThrow(Registries.ENCHANTMENT)
            .getOrThrow(enchantment);
    }

    private static void requireValidLevel(int enchantmentLevel) {
        if (enchantmentLevel < 1) {
            throw new IllegalArgumentException(
                "Enchantment Level must be at least 1"
            );
        }
    }
}