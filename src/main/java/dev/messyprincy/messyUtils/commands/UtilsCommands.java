package dev.messyprincy.messyUtils.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.messyprincy.messyUtils.utils.LoggerUtil;
import dev.messyprincy.messyUtils.managers.StaffChatManager;
import dev.messyprincy.messyUtils.utils.PlayerUtils;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class UtilsCommands {
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("messyutils")
                .then(Commands.literal("velocity")
                        .requires(source -> source.getSender().hasPermission(StaffChatManager.STAFF_PERMISSION))
                        .then(Commands.argument("target", ArgumentTypes.player())
                                .then(Commands.argument("yaw", FloatArgumentType.floatArg(-180, 180))
                                        .then(Commands.argument("pitch", FloatArgumentType.floatArg(-90, 90))
                                                .then(Commands.argument("power", DoubleArgumentType.doubleArg(0, 10.0))
                                                        .executes(context -> {
                                                            CommandSender sender = context.getSource().getSender();

                                                            PlayerSelectorArgumentResolver resolver =
                                                                    context.getArgument("target", PlayerSelectorArgumentResolver.class);
                                                            List<Player> targets = resolver.resolve(context.getSource());

                                                            if (targets.isEmpty()) {
                                                                LoggerUtil.sendErrorMessage(sender, "No players matched your selector");
                                                                return Command.SINGLE_SUCCESS;
                                                            }

                                                            float yaw = context.getArgument("yaw", Float.class);
                                                            float pitch = context.getArgument("pitch", Float.class);
                                                            double power = context.getArgument("power", Double.class);

                                                            for (Player target : targets) {
                                                                PlayerUtils.setVelocity(target, yaw, pitch, power);
                                                            }

                                                            LoggerUtil.sendSuccessMessage(sender, "Set velocity for " + targets.size() + " player(s)");
                                                            return Command.SINGLE_SUCCESS;
                                                        })))))
                )
                .build();
    }
}
