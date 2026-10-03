package net.jimmynet.jamesindustries.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public final class ModLog {
    private ModLog() {}

    private static final Pattern LOG_LEVEL_PATTERN = Pattern.compile("^[a-zA-Z]{1,7}:");

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
     * Accepts different logging levels via prefixes
     * WARN:, INFO:, ERROR:, SUCCESS:
     * Will colour code messages according to supplied logging level
     * @param player The player to log the message to.
     * @param message The message to log.
     */
    public static void log(ServerPlayer player,String message) {
        int colour = 0xFFFFFF;

        final Matcher matcher = LOG_LEVEL_PATTERN.matcher(message);
        
        if (matcher.find()) {
            String level = matcher.group();

            switch (Character.toUpperCase(level.charAt(0))) {
                case 'S':
                    colour = 0x00FF00;
                    break;
                case 'E':
                    colour = 0xFF0000;
                    break;
                case 'W':
                    colour = 0xFFFF00;
                    break;
                case 'I':
                    colour = 0x0000FF;
                    break;
                default:
                    colour = 0xFFFFFF;
                    break;
            }
        }
        
        log(
            player,
            colour,
            message
        );
    }
}