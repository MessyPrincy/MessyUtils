package dev.messyprincy.messyUtils;

import dev.messyprincy.messyUtils.NamespacedKeys.KeyUtils;
import dev.messyprincy.messyUtils.commands.BotCommands;
import dev.messyprincy.messyUtils.commands.StaffChatCommands;
import dev.messyprincy.messyUtils.listeners.ChatEventsListener;
import dev.messyprincy.messyUtils.listeners.PlayerConnectionListener;
import dev.messyprincy.messyUtils.logger.LoggerUtil;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessyUtils extends JavaPlugin {
    public static MessyUtils instance;

    @Override
    public void onEnable() {
        instance = this;

        KeyUtils.init();

        this.getServer().getPluginManager().registerEvents(new ChatEventsListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);

        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(new StaffChatCommands().build());
            event.registrar().register(new BotCommands().build());
        });

        LoggerUtil.successLog("MeßyUtils was enabled");
    }

    @Override
    public void onDisable() {
        LoggerUtil.successLog("MeßyUtils was disabled");
    }
}
