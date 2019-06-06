package me.Henry.config;

import org.bukkit.plugin.java.JavaPlugin;

public class SQLConfig extends Config {

    private JavaPlugin plugin;

    public SQLConfig(JavaPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
        createConfig("sql.yml", true);
    }

    @Override
    public void initConfig() {

    }
}
