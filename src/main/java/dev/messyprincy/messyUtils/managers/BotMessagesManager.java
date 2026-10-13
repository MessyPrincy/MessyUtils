package dev.messyprincy.messyUtils.managers;

import dev.messyprincy.messyUtils.keys.KeyUtils;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class BotMessagesManager {
    private static final Set<UUID> enabled = ConcurrentHashMap.newKeySet();

    public static void load(Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();

        if (pdc.getOrDefault(KeyUtils.getPlayerKey(), PersistentDataType.BOOLEAN, false)) {
            enabled.add(player.getUniqueId());
        }
    }

    public static void unload(Player player) {
        enabled.remove(player.getUniqueId());
    }

    public static boolean isEnabled(Player player) {
        return enabled.contains(player.getUniqueId());
    }

    public static void enable(Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();

        enabled.add(player.getUniqueId());

        pdc.set(KeyUtils.getPlayerKey(), PersistentDataType.BOOLEAN, true);
    }

    public static void disable(Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();

        enabled.remove(player.getUniqueId());

        pdc.remove(KeyUtils.getPlayerKey());
    }

    public static boolean toggle(Player player) {
        boolean now = !isEnabled(player);

        if (now) {
            enable(player);
        } else {
            disable(player);
        }

        return now;
    }
}
