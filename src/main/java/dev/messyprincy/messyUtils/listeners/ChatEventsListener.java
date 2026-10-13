package dev.messyprincy.messyUtils.listeners;

import dev.messyprincy.messyUtils.managers.StaffChatManager;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class ChatEventsListener implements Listener {
    @EventHandler (priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onAsyncChatEvent(AsyncChatEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();

        if (StaffChatManager.isEnabled(uuid)) {
            e.setCancelled(true);
            StaffChatManager.send(e.getPlayer(), e.message());
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (StaffChatManager.isEnabled(player.getUniqueId())) {
            StaffChatManager.setEnabled(player.getUniqueId(), false);
        }
    }
}
