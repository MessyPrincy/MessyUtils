package dev.messyprincy.messyUtils.listeners;

import dev.messyprincy.messyUtils.utils.LoggerUtil;
import dev.messyprincy.messyUtils.managers.StaffChatManager;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.UUID;

public class ChatEventsListener implements Listener {
    private static final String AI_PREFIX = "IAMAB0T";

    @EventHandler (priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onAsyncChatEvent(AsyncChatEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();

        if (StaffChatManager.isEnabled(uuid)) {
            e.setCancelled(true);
            LoggerUtil.sendStaffMessage(e.getPlayer(), e.message());
            return;
        }

        if (PlainTextComponentSerializer.plainText().serialize(e.message()).startsWith(AI_PREFIX)) {
            e.setCancelled(true);
            String messageString = PlainTextComponentSerializer.plainText().serialize(e.message());
            messageString = messageString.replace(AI_PREFIX, "");

            LoggerUtil.sendAiMessage(e.getPlayer(), Component.text(messageString));
            return;
        }
    }
}
