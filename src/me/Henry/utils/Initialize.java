package me.Henry.utils;

import me.Henry.commands.HelpCommands;
import me.Henry.config.MessagesConfig;
import me.Henry.config.SQLConfig;
import me.Henry.config.UserConfig;
import me.Henry.database.SQLMain;
import me.Henry.gui.help.Help;
import me.Henry.gui.help.HelpEnchantmentGUI;
import me.Henry.gui.help.settings.HelpSettingsGUI;
import me.Henry.listeners.BleedEffect;
import me.Henry.listeners.UserConfigLoad;
import org.bukkit.plugin.java.JavaPlugin;

public class Initialize {

    private JavaPlugin plugin;
    private MessagesConfig messagesConfig;
    private UserConfig userConfig;

    private SQLMain sqlMain;
    private SQLConfig sqlConfig;

    private BleedEffect bleedEffect;
    private UserConfigLoad userConfigLoad;

    private Help help;
    private HelpEnchantmentGUI helpEnchantmentGUI;
    private HelpSettingsGUI helpSettingsGUI;

    private HelpCommands helpCommands;

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
        sqlConfig = new SQLConfig(plugin);
    }

    private void registerGUIs() {
        helpEnchantmentGUI = new HelpEnchantmentGUI(plugin, userConfig);
        helpSettingsGUI = new HelpSettingsGUI(plugin, userConfig);
        help = new Help(plugin, userConfig, helpEnchantmentGUI, helpSettingsGUI);
    }

    private void registerCommands() {
        helpCommands = new HelpCommands(plugin, messagesConfig, help);
    }

    private void registerListeners() {
        bleedEffect = new BleedEffect(plugin, userConfig);
        userConfigLoad = new UserConfigLoad(plugin, userConfig);
    }

    private void registerSQL() {
        sqlMain = new SQLMain(plugin, sqlConfig);
    }
}
