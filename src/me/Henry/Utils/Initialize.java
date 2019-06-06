package me.Henry.Utils;

import me.Henry.Commands.HelpCommand;
import me.Henry.Config.MessagesConfig;
import me.Henry.Config.UserConfig;
import me.Henry.Config.UserConfigListener;
import me.Henry.GUI.HelpEnchantmentGUI;
import me.Henry.GUI.HelpGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class Initialize {

    private JavaPlugin plugin;
    private MessagesConfig messagesConfig;
    private UserConfig userConfig;
    private HelpGUI helpGUI;
    private HelpEnchantmentGUI helpEnchantmentGUI;
    private HelpCommand helpCommand;
    private UserConfigListener userConfigListener;

    public Initialize(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        registerConfig();
        registerListeners();
        registerGUIs();
        registerCommands();
    }

    private void registerConfig() {
        messagesConfig = new MessagesConfig(plugin);
        userConfig = new UserConfig(plugin);
    }

    private void registerGUIs() {
        helpEnchantmentGUI = new HelpEnchantmentGUI(plugin);
        helpGUI = new HelpGUI(plugin, userConfig, helpEnchantmentGUI);
    }

    private void registerCommands() {
        helpCommand = new HelpCommand(plugin, messagesConfig, helpGUI);
    }

    private void registerListeners() {
        userConfigListener = new UserConfigListener(plugin, userConfig);
    }
}
