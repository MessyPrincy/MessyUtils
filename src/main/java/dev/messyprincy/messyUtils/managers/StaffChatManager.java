package dev.messyprincy.messyUtils.managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class StaffChatManager {
    public static final String PERMISSION = "messyutils.staff";
    private static final Set<UUID> enabled = ConcurrentHashMap.newKeySet();

    public static void setEnabled(UUID uuid, boolean value) {
        if (value) enabled.add(uuid);
        else enabled.remove(uuid);
    }

    public static boolean isEnabled(UUID uuid) {
        return enabled.contains(uuid);
    }

    public static boolean toggle(UUID uuid) {
        boolean now = !isEnabled(uuid);
        setEnabled(uuid, now);
        return now;
    }

    public static void send(Player sender, Component message) {
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
            if (p.hasPermission(PERMISSION)) {
                p.sendMessage(formatted);
            }
        }
        Bukkit.getConsoleSender().sendMessage(formatted);
    }
}
