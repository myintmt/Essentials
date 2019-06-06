package me.Henry.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public abstract class Config {

    private JavaPlugin plugin;
    private String name;
    private File file;
    private FileConfiguration config;

    public Config(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void createConfig(String name, boolean saveResource) {
        saveResource(saveResource);
        load(name);
        if (!file.exists()) {
            initConfig();
            saveConfig();
        }
    }

    public void load(String name) {
        setFile(new File(plugin.getDataFolder(), name));
        setConfig(YamlConfiguration.loadConfiguration(getFile()));
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

    public void saveResource(boolean saveResource) {
        if (saveResource)
            plugin.saveResource("messages.yml", false);
    };

}
