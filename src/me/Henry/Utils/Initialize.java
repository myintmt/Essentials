package me.Henry.Utils;

import me.Henry.Commands.HelpCommand;
import me.Henry.Config.MessagesConfig;
import me.Henry.GUI.HelpGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class Initialize {

    private JavaPlugin plugin;
    private MessagesConfig messagesConfig;
    private HelpGUI helpGUI;

    public Initialize(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        registerConfig();
        registerGUIs();
        registerCommands();
    }

    private void registerConfig() {
        messagesConfig = new MessagesConfig(plugin);
    }

    private void registerGUIs() {
        helpGUI = new HelpGUI(plugin);
    }

    private void registerCommands() {
        new HelpCommand(plugin, messagesConfig, helpGUI);
    }


}
