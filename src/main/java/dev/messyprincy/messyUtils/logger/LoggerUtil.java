package dev.messyprincy.messyUtils.logger;

import dev.messyprincy.messyUtils.MessyUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class LoggerUtil {
    private static final Component introText = Component.text("[")
            .color(TextColor.color(0x46B7DB))
            .append(Component.text("Meßy's Utils")
                    .color(TextColor.color(0xD94198)))
            .append(Component.text("] ")
                    .color(TextColor.color(0x46B7DB)));

    public static void sendSuccessMessage(CommandSender sender, String text) {
        if (sender instanceof Player player) {
            sendSuccessMessage(player, text);
            return;
        }

        successLog(text);
    }

    public static void sendErrorMessage(CommandSender sender, String text) {
        if (sender instanceof Player player) {
            sendErrorMessage(player, text);
            return;
        }

        errorLog(text);
    }

    public static void sendSuccessMessage(Player player, String text) {
        player.sendMessage(introText.append(
                Component.text(text)
        ));
    }

    public static void sendErrorMessage(Player player, String text) {
        player.sendMessage(introText.append(
                Component.text(text).color(NamedTextColor.RED)
        ));
    }

    public static void successLog(String text) {
        MessyUtils.instance.getLogger().info("[Meßy's Journal] " + text);
    }

    public static void errorLog(String text) {
        MessyUtils.instance.getLogger().warning("[Meßy's Journal] " + text);
    }
}
