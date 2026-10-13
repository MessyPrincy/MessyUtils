package dev.messyprincy.messyUtils.logger;

import dev.messyprincy.messyUtils.MessyUtils;
import dev.messyprincy.messyUtils.managers.BotMessagesManager;
import dev.messyprincy.messyUtils.managers.StaffChatManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
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

    public static void sendAiMessage(Player sender, Component message) {
        Component formatted = Component.text()
                .append(Component.text("[")
                        .color(TextColor.color(0x222658)))
                .append(Component.text("AI")
                        .color(TextColor.color(0x467ADB)))
                .append(Component.text("] ")
                        .color(TextColor.color(0x222658)))
                .append(sender.name().colorIfAbsent(NamedTextColor.GRAY))
                .append(Component.text(": ", NamedTextColor.DARK_GRAY))
                .append(message.colorIfAbsent(TextColor.color(0x467ADB)))
                .build();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (BotMessagesManager.isEnabled(p)) {
                p.sendMessage(formatted);
            }
        }
        Bukkit.getConsoleSender().sendMessage(formatted);
    }

    public static void sendStaffMessage(Player sender, Component message) {
        Component formatted = Component.text()
                .append(Component.text("[")
                        .color(TextColor.color(0x22582C)))
                .append(Component.text("Staff Chat")
                        .color(TextColor.color(0x46DB62)))
                .append(Component.text("] ")
                        .color(TextColor.color(0x22582C)))
                .append(sender.name().colorIfAbsent(NamedTextColor.GRAY))
                .append(Component.text(": ", NamedTextColor.DARK_GRAY))
                .append(message.colorIfAbsent(NamedTextColor.WHITE))
                .build();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission(StaffChatManager.STAFF_PERMISSION)) {
                p.sendMessage(formatted);
            }
        }
        Bukkit.getConsoleSender().sendMessage(formatted);
    }
}
