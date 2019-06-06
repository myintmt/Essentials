package me.Henry.gui.help;

import me.Henry.config.UserConfig;
import me.Henry.gui.GUI;
import me.Henry.gui.help.settings.HelpSettingsGUI;
import me.Henry.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Help extends GUI implements Listener{

    private JavaPlugin plugin;
    private HelpEnchantmentGUI helpEnchantmentGUI;
    private HelpSettingsGUI helpSettingsGUI;
    private UserConfig userConfig;

    private ItemStack enchantments, settings;
    private boolean clickedEnchantments, clickedSettings;

    public Help(JavaPlugin plugin, UserConfig userConfig, HelpEnchantmentGUI helpEnchantmentGUI, HelpSettingsGUI helpSettingsGUI) {
        super(9, "Help");
        this.plugin = plugin;
        this.userConfig = userConfig;
        this.helpEnchantmentGUI = helpEnchantmentGUI;
        this.helpSettingsGUI = helpSettingsGUI;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        buildItems();

        setItem(0, enchantments, player -> {
            clickedEnchantments = true;
            helpEnchantmentGUI.open(player);
        });

        setItem(1, settings, player -> {
            clickedSettings = true;
            helpSettingsGUI.open(player);
        });
    }

    private void buildItems() {
        enchantments = new ItemBuilder(Material.ENCHANTED_BOOK, 1)
                .setDisplayName(ChatColor.AQUA + "Enchantments")
                .addLoreLine(ChatColor.GRAY + "Click to see a list of")
                .addLoreLine(ChatColor.GRAY + "custom enchantments.")
                .toItemStack();

        settings = new ItemBuilder(Material.COMPASS, 1)
                .setDisplayName(ChatColor.AQUA + "Settings")
                .addLoreLine(ChatColor.GRAY + "Click to view and")
                .addLoreLine(ChatColor.GRAY + "change your settings.")
                .toItemStack();
    }

    @Override
    public void afterOpen(Player player) {
        if (userConfig.getConfig().getBoolean("Settings.Sounds.Menu"))
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1,1);
    }

    @Override
    public void afterClose(InventoryCloseEvent event) {
        if (!clickedEnchantments) {
            Player player = (Player) event.getPlayer();
            if (userConfig.getConfig().getBoolean("Settings.Sounds.Menu"))
                player.playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHEST_CLOSE, 1,1);
        }
    }

    @Override
    public void afterQuit(PlayerQuitEvent event) {

    }
}
