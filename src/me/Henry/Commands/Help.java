package me.Henry.Commands;

import me.Henry.Utils.MessagesConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Help extends Commands{

    private MessagesConfig config;

    public Help(JavaPlugin plugin, MessagesConfig config) {
        super(plugin, config,"help", "essentials.command.help", true);
        this.config = config;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        
    }

}
