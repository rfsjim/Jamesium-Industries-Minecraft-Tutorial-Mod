package net.jimmynet.jamesindustries.item;

import net.jimmynet.jamesindustries.JamesiumIndustries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
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
            }).build());
}