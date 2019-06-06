package me.Henry.gui.help.settings;

import me.Henry.config.UserConfig;
import me.Henry.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpSettingsSounds extends GUI implements Listener {

    private JavaPlugin plugin;
    private UserConfig userConfig;

    public HelpSettingsSounds(JavaPlugin plugin, UserConfig userConfig) {
        super(9, "Sounds");
        this.plugin = plugin;
    }

    @Override
    public void afterOpen(Player player) {

    }

    @Override
    public void afterClose(InventoryCloseEvent event) {

    }

    @Override
    public void afterQuit(PlayerQuitEvent event) {

    }
}
