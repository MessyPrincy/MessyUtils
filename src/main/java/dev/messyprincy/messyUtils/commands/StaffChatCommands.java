package dev.messyprincy.messyUtils.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.messyprincy.messyUtils.logger.LoggerUtil;
import dev.messyprincy.messyUtils.managers.StaffChatManager;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


public class StaffChatCommands {
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("staffchat")
                .requires(source -> source.getSender().hasPermission(StaffChatManager.PERMISSION))
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();
                    Entity executor = context.getSource().getExecutor();

                    if (!(executor instanceof Player player)) {
                        LoggerUtil.sendErrorMessage(sender, "Only players can use this command!");
                        return Command.SINGLE_SUCCESS;
                    }

                    boolean now = StaffChatManager.toggle(player.getUniqueId());

                    LoggerUtil.sendSuccessMessage(player, "Staff chat has been " + (now ? "enabled" : "disabled"));
                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.argument("message", StringArgumentType.greedyString())
                        .executes(context -> {
                            CommandSender sender = context.getSource().getSender();
                            Entity executor = context.getSource().getExecutor();

                            if (!(executor instanceof Player player)) {
                                LoggerUtil.sendErrorMessage(sender, "Only players can use this command!");
                                return Command.SINGLE_SUCCESS;
                            }

                            String message = context.getArgument("message", String.class);
                            Component componentMessage = Component.text(message);

                            StaffChatManager.send(player, componentMessage);
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
