package me.Henry.gui.help;

import me.Henry.config.UserConfig;
import me.Henry.gui.GUI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpEnchantmentGUI extends GUI implements Listener {

    private JavaPlugin plugin;
    private UserConfig userConfig;

    public HelpEnchantmentGUI(JavaPlugin plugin, UserConfig userConfig) {
        super(9, "Enchantments");
        this.plugin = plugin;
        this.userConfig = userConfig;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void afterOpen(Player player) {
    }

    @Override
    public void afterClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (userConfig.getConfig().getBoolean("Settings.Sounds.Menu"))
            player.playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHEST_CLOSE, 1,1);
    }

    @Override
    public void afterQuit(PlayerQuitEvent event) {

    }
}
