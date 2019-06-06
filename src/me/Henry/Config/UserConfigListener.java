package me.Henry.Config;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class UserConfigListener implements Listener {

    private JavaPlugin plugin;
    private UserConfig userConfig;

    public UserConfigListener(JavaPlugin plugin, UserConfig userConfig) {
        this.plugin = plugin;
        this.userConfig = userConfig;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        userConfig.createConfig(event.getPlayer().getUniqueId().toString() + ".yml", false);
    }
}
