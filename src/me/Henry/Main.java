package me.Henry;

import me.Henry.Commands.Help;
import me.Henry.Listeners.UserConfigListener;
import me.Henry.Utils.MessagesConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private MessagesConfig config;

    @Override
    public void onEnable() {
        registerConfigs();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        new Help(this, config);
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new UserConfigListener(this), this);
    }

    private void registerConfigs() {
        config = new MessagesConfig(this);
    }
}
