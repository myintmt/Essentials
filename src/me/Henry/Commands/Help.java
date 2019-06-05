package me.Henry.Commands;

import me.Henry.Main;
import org.bukkit.command.CommandSender;

public class Help extends Commands{

    private Main plugin;

    public Help(Main plugin) {
        super(plugin, "help", "essentials.command.help", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("Okay, now this is epic.");
    }
}
