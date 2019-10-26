package me.Stefan923.SuperCoreLite.Listeners;

import me.Stefan923.SuperCoreLite.Main;
import me.Stefan923.SuperCoreLite.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener, MessageUtils {

    @EventHandler
    public void onPlayerOuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        Main instance = Main.instance;
        FileConfiguration settings = instance.getSettingsManager().getConfig();

        instance.removeUser(playerName);

        event.setQuitMessage("");

        if (settings.getBoolean("On Quit.Enable Quit Message")) {
            FileConfiguration languageConfig;
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                languageConfig = instance.getLanguageManager(instance.getUser(onlinePlayer).getLanguage()).getConfig();
                onlinePlayer.sendMessage(formatAll(languageConfig.getString("On Quit.Quit Message").replace("%playername%", playerName)));
            }
        }
    }

}
