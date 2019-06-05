package me.Henry.Listeners;

import me.Henry.Utils.UserConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class UserConfigListener implements Listener {

    private JavaPlugin plugin;
    private UserConfig userConfig;

    public UserConfigListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        userConfig = new UserConfig(plugin, event.getPlayer().getUniqueId());
    }

}
