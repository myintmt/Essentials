package me.Henry.GUI;

import me.Henry.Config.UserConfig;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class HelpGUI extends GUI implements Listener{

    private JavaPlugin plugin;
    private HelpEnchantmentGUI helpEnchantmentGUI;
    private UserConfig userConfig;

    public HelpGUI(JavaPlugin plugin, UserConfig userConfig, HelpEnchantmentGUI helpEnchantmentGUI) {
        super(9, "Help");
        this.plugin = plugin;
        this.userConfig = userConfig;
        this.helpEnchantmentGUI = helpEnchantmentGUI;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        buildItems();
    }

    private void buildItems() {
        setItem(0, new ItemStack(Material.DIAMOND_CHESTPLATE), player -> {
            helpEnchantmentGUI.open(player);
        });
    }

    private void print() {
        for (UUID uuid: getPlayerOpenedInventories()) {
            System.out.println(uuid.toString());
        }
    }

    @Override
    public void afterOpen(Player player) {
        if (userConfig.getConfig().getBoolean("Settings.Sounds.Menu"))
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1,1);
    }

    @Override
    public void afterClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

    }

    @Override
    public void afterQuit(PlayerQuitEvent event) {

    }
}
