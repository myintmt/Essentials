package me.Henry.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public abstract class Config {

    private JavaPlugin plugin;
    private File file;
    private FileConfiguration config;

    public Config(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        saveResource();
        setFile(new File(plugin.getDataFolder(), name + ".yml"));
        setConfig(YamlConfiguration.loadConfiguration(getFile()));
        createConfig();
    }

    private void createConfig() {
        if (!file.exists()) {
            initConfig();
            saveConfig();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setConfig(FileConfiguration config) {
        this.config = config;
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("Could not save file.");
            e.printStackTrace();
        }
    }


    public abstract void initConfig();
    public void saveResource() {
        plugin.saveResource("messages.yml", false);
    };

}
