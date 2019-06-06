package me.Henry.GUI;

import me.Henry.Config.UserConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class HelpGUI extends GUI implements Listener {

    private JavaPlugin plugin;
    private HelpEnchantmentsGUI helpEnchantmentsGUI;

    private UserConfig userConfig;
    private BukkitTask task;


    private ItemStack enchantments;
    private ItemMeta enchantmentsMeta;
    private List<String> lore;

    public HelpGUI(JavaPlugin plugin) {
        super(9, "Help");
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        enchantments = new ItemStack(Material.ENCHANTED_BOOK);
        enchantmentsMeta = enchantments.getItemMeta();
        enchantmentsMeta.setDisplayName(ChatColor.AQUA + "Enchantments");
        lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Click to see a list of custom");
        lore.add(ChatColor.GRAY + "enchantments and what they do.");
        enchantmentsMeta.setLore(lore);
        enchantments.setItemMeta(enchantmentsMeta);

        helpEnchantmentsGUI = new HelpEnchantmentsGUI(plugin);

        setItem(0, enchantments, player -> {
            player.openInventory(helpEnchantmentsGUI.getInventory());
        });
    }

    public void runTask() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis()/1000;
                enchantments = new ItemStack(Material.ENCHANTED_BOOK);
                enchantmentsMeta = enchantments.getItemMeta();
                enchantmentsMeta.setDisplayName(String.valueOf(time));
                enchantments.setItemMeta(enchantmentsMeta);
                setItem(0, enchantments, player -> {
                    player.sendMessage("You clicked on enchantments!");
                });
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player))
            return;
        Player player = (Player) event.getWhoClicked();
        if (getPlayerOpenedInventories().contains(player.getUniqueId())) {
            event.setCancelled(true);
            if (getActions().get(event.getSlot()) != null)
                getActions().get(event.getSlot()).click(player);
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        getPlayerOpenedInventories().add(event.getPlayer().getUniqueId());
        Player player = (Player) event.getPlayer();
        userConfig = new UserConfig(plugin, player.getUniqueId());
        userConfig.init();
        if (userConfig.getConfig().getBoolean("Settings.Sounds.Menu"))
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (!getPlayerOpenedInventories().contains(event.getPlayer().getUniqueId()))
            return;
        Player player = (Player) event.getPlayer();
        userConfig = new UserConfig(plugin, player.getUniqueId());
        userConfig.init();
        if (userConfig.getConfig().getBoolean("Settings.Sounds.Menu"))
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
        getPlayerOpenedInventories().remove(event.getPlayer().getUniqueId());
        // cancel task
        //task.cancel();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (!getPlayerOpenedInventories().contains(event.getPlayer().getUniqueId()))
            return;
        getPlayerOpenedInventories().remove(event.getPlayer().getUniqueId());
        // cancel task
        //task.cancel();
    }

    public HelpEnchantmentsGUI getHelpEnchantmentsGUI() {
        return helpEnchantmentsGUI;
    }
}
