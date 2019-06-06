package me.Henry.gui.help.settings;

import me.Henry.config.UserConfig;
import me.Henry.gui.GUI;
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

public class HelpSettingsGUI extends GUI implements Listener {

    private JavaPlugin plugin;
    private UserConfig userConfig;

    private ItemStack sounds;
    private boolean clickedSounds;

    public HelpSettingsGUI(JavaPlugin plugin, UserConfig userConfig) {
        super(9, "Settings");
        this.plugin = plugin;
        this.userConfig = userConfig;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        buildItems();

        setItem(0, sounds, player -> {
            clickedSounds = true;
        });
    }

    public void buildItems() {
        sounds = new ItemBuilder(Material.NOTE_BLOCK, 1)
                .setDisplayName(ChatColor.AQUA + "Sounds")
                .addLoreLine(ChatColor.GRAY + "Click to enable or disable")
                .addLoreLine(ChatColor.GRAY + "certain sounds in-game.")
                .toItemStack();
    }

    @Override
    public void afterOpen(Player player) {
        if (userConfig.getConfig().getBoolean("Settings.Sounds.Menu"))
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1,1);
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
