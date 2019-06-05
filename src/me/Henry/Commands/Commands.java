package me.Henry.Commands;

import me.Henry.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class Commands implements CommandExecutor {

    private Main plugin;
    private String commandName;
    private String permission;
    private boolean canConsoleUse;


    public Commands(Main plugin, String commandName, String permission, boolean canConsoleUse) {
        this.plugin = plugin;
        this.commandName = commandName;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
        plugin.getCommand(commandName).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!command.getLabel().equalsIgnoreCase(commandName))
            return true;
        if (!sender.hasPermission(permission))
            return true;
        if (!canConsoleUse && !(sender instanceof Player))
            return true;
        execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);
}
