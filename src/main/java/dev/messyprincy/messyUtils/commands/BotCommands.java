package dev.messyprincy.messyUtils.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.messyprincy.messyUtils.logger.LoggerUtil;
import dev.messyprincy.messyUtils.managers.BotMessagesManager;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class BotCommands {
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("botmessages")
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();
                    Entity executor = context.getSource().getExecutor();

                    if (!(executor instanceof Player player)) {
                        LoggerUtil.sendErrorMessage(sender, "Only players can use this command!");
                        return Command.SINGLE_SUCCESS;
                    }

                    boolean now = BotMessagesManager.toggle(player);

                    LoggerUtil.sendSuccessMessage(player, "Ai Chatbots messages have been " + (now ? "enabled" : "disabled"));
                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}
