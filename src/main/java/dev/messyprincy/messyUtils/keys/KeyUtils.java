package dev.messyprincy.messyUtils.keys;

import dev.messyprincy.messyUtils.MessyUtils;
import org.bukkit.NamespacedKey;

public final class KeyUtils {
    private static NamespacedKey playerKey;

    public static void init() {
        playerKey = new NamespacedKey(MessyUtils.instance, "enabledAiMessages");
    }

    public static NamespacedKey getPlayerKey() {
        return playerKey;
    }
}
