package dev.messyprincy.messyUtils.managers;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class StaffChatManager {
    public static final String STAFF_PERMISSION = "messyutils.staff";
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
}
