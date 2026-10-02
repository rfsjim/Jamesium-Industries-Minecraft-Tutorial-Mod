package net.jimmynet.jamesindustries.helpers;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ModLog {
    private ModLog() {}

    /**
     * Logs a message to the player with a specified colour.
     * @param player The player to log the message to.
     * @param colour The colour of the message.
     * @param message The message to log. 
     */
    public static void log(ServerPlayer player, int colour, String message) {
        player.sendSystemMessage(
            Component.literal(message)
                .withColor(colour)
        );
    }

    /**
     * Logs a message to the player with the default colour of white.
     * @param player The player to log the message to.
     * @param message The message to log.
     */
    public static void log(ServerPlayer player,String message) {
        log(
            player,
            0xFFFFFF,
            message
        );
    }
}
