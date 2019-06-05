package me.Henry.Utils;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class UserConfig extends Config {

    private JavaPlugin plugin;
    private UUID uuid;

    public UserConfig(JavaPlugin plugin, UUID uuid) {
        super(plugin, uuid.toString());
        this.plugin = plugin;
        this.uuid = uuid;
    }

    @Override
    public void initConfig() {
        getConfig().set("Settings.Sounds.Flight", true);
    }
}
