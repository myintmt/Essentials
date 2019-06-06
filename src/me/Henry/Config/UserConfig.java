package me.Henry.Config;

import org.bukkit.plugin.java.JavaPlugin;

public class UserConfig extends Config {

    private JavaPlugin plugin;

    public UserConfig(JavaPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public void initConfig() {
        getConfig().set("Settings.Sounds.Flight", true);
        getConfig().set("Settings.Sounds.Menu", true);
    }

}
