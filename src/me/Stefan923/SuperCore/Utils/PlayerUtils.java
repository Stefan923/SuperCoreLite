package me.Stefan923.SuperCore.Utils;

import me.Stefan923.SuperCore.SuperCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface PlayerUtils {

    /* Get a set with all online players that a player can see. */

    default Set<Player> onlinePlayers(Player player) {
        return Bukkit.getOnlinePlayers().stream().filter(player::canSee).collect(Collectors.toSet());
    }

    /* Get a set with all online players that a command sender can see. */

    default Set<Player> onlinePlayers(CommandSender sender) {
        return (sender instanceof Player) ? onlinePlayers((Player) sender) : new HashSet<>(Bukkit.getOnlinePlayers());
    }

    /* Get a set with all online players that a player can see and has certain permission. */

    default Set<Player> onlinePlayers(Player player, String permission) {
        return Bukkit.getOnlinePlayers().stream().filter(onlinePlayer -> player.canSee(onlinePlayer) && onlinePlayer.hasPermission(permission)).collect(Collectors.toSet());
    }

    /* Convets a location object to string. */

    default String locationToString(Location location) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(location.getBlockX()).append(", ").append(location.getBlockY()).append(", ").append(location.getBlockZ()).append(", ").append(location.getWorld().getName());
        return stringBuffer.toString();
    }

    default long getLastOnline(Player player) {
        return getLastOnline(player.getName());
    }

    default long getLastOnline(String playerName) {
        ResultSet resultSet = SuperCore.getInstance().getDatabase("supercore_users").get(playerName, "lastonline");
        try {
            return resultSet == null ? -1 : resultSet.getLong("lastonline");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    default void setLastOnline(Player player) {
        setLastOnline(player.getName());
    }

    default void setLastOnline(String playerName) {
        SuperCore.getInstance().getDatabase("supercore_users").put(playerName, "lastonline", String.valueOf(System.currentTimeMillis()));
    }

}
