package me.Henry;

import me.Henry.Commands.Commands;
import me.Henry.Commands.Help;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Commands commands;
    private Help commandHelp;

    @Override
    public void onEnable() {
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        commandHelp = new Help(this);
    }
}
