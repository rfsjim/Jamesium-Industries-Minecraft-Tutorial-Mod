package net.jimmynet.jamesindustries.item;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.block.ModBlocks;
import net.jimmynet.jamesindustries.helpers.EnchantHelpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.network.chat.Component;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB,
            JamesiumIndustries.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> JAMES_INDUSTRIES_TAB = 
        CREATIVE_MODE_TABS.register("james_industries_tab", () -> 
            CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.jamesiumindustries"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.MAGIC_CUBE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.MAGIC_CUBE.get());

                ItemStack enchantedNetherSword = EnchantHelpers.applyEnchantmentValidated(
                    new ItemStack(ModItems.NETHER_SWORD.get()),
                    parameters.holders(),
                    Enchantments.FIRE_ASPECT);

                output.accept(enchantedNetherSword);
                output.accept(ModItems.SILVER_INGOT.get());
                output.accept(ModItems.PET_RABBIT_SPAWN_EGG.get());
                output.accept(ModBlocks.MAPLE_LOG_BLOCK.get());
                output.accept(ModBlocks.RED_ORE_BLOCK.get());
                output.accept(ModBlocks.SILVER_BLOCK.get());
                output.accept(ModBlocks.SILVER_ORE_BLOCK.get());
            }).build());
}