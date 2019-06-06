package me.Henry.commands;

import me.Henry.config.MessagesConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpCommands extends Commands {

    private JavaPlugin plugin;
    private MessagesConfig messagesConfig;
    private me.Henry.gui.help.Help help;

    public HelpCommands(JavaPlugin plugin, MessagesConfig messagesConfig, me.Henry.gui.help.Help help) {
        super(plugin, messagesConfig, "help", "essentials.command.help", true);
        this.plugin = plugin;
        this.help = help;
        plugin.getCommand("help").setExecutor(this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        help.open(player);
    }

}
