package dev.messyprincy.messyUtils.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class PlayerUtils {
    public static void setVelocity(Player player, float yaw, float pitch, double power) {
        Location pLocation = player.getLocation();

        pLocation.setYaw(yaw);
        pLocation.setPitch(-pitch);

        player.setVelocity(pLocation.getDirection().multiply(power));
    }
}
