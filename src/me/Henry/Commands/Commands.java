package me.Henry.Commands;

import me.Henry.Utils.MessagesConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Commands implements CommandExecutor {

    // Dependencies
    private JavaPlugin plugin;
    private MessagesConfig config;

    private String commandName;
    private String permission;
    private boolean canConsoleUse;


    public Commands(JavaPlugin plugin, MessagesConfig config, String commandName, String permission, boolean canConsoleUse) {
        // Dependencies
        this.plugin = plugin;
        this.config = config;

        this.commandName = commandName;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
        plugin.getCommand(commandName).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!command.getLabel().equalsIgnoreCase(commandName))
            return true;
        if (!sender.hasPermission(permission)) {
            config.sendMessage(sender, config.getConfig().getString("nopermission"));
            return true;
        }
        if (!canConsoleUse && !(sender instanceof Player)) {
            config.sendMessage(sender, config.getConfig().getString("onlyplayer"));
            return true;
        }
        execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);
    /*
    public final static void registerCommands(JavaPlugin pl) {
        plugin = pl;
        new Help();
    }
     */

}
