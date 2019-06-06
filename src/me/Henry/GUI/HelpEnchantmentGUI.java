package me.Henry.GUI;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpEnchantmentGUI extends GUI implements Listener {

    private JavaPlugin plugin;

    public HelpEnchantmentGUI(JavaPlugin plugin) {
        super(9, "Enchantments");
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
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
