package dev.messyprincy.messyUtils.listeners;

import dev.messyprincy.messyUtils.managers.BotMessagesManager;
import dev.messyprincy.messyUtils.managers.StaffChatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (StaffChatManager.isEnabled(player.getUniqueId())) {
            StaffChatManager.setEnabled(player.getUniqueId(), false);
        }

        BotMessagesManager.unload(player);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        BotMessagesManager.load(player);
    }
}
