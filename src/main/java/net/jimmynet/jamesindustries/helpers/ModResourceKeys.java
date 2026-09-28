package net.jimmynet.jamesindustries.helpers;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public final class ModResourceKeys {
    private ModResourceKeys() {}

    public static <T> ResourceKey<T> create(
        ResourceKey <? extends Registry<T>> registryKeyType,
        String path
    ) {
        return ResourceKey.create(
            registryKeyType,
            Identifier.fromNamespaceAndPath(
                JamesiumIndustries.MODID,
                path
            )
        );
    }
}