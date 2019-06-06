package me.Henry.Commands;

import me.Henry.Config.MessagesConfig;
import me.Henry.GUI.HelpGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpCommand extends Commands{

    private JavaPlugin plugin;
    private MessagesConfig messagesConfig;
    private HelpGUI helpGUI;

    public HelpCommand(JavaPlugin plugin, MessagesConfig messagesConfig, HelpGUI helpGUI) {
        super(plugin, messagesConfig,"help", "essentials.command.help", true);
        this.plugin = plugin;
        this.messagesConfig = messagesConfig;
        this.helpGUI = helpGUI;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        helpGUI.open(player);
    }

}
