package me.Henry.Config;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MessagesConfig extends Config {

    private JavaPlugin plugin;

    public MessagesConfig(JavaPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
        createConfig("messages.yml", true);
    }

    @Override
    public void initConfig() {

    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
