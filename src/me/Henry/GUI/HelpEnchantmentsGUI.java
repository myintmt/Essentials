package me.Henry.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class HelpEnchantmentsGUI extends GUI implements Listener {

    private JavaPlugin plugin;
    private ItemStack armor, weapons;
    private ItemMeta armorMeta, weaponsMeta;

    public HelpEnchantmentsGUI(JavaPlugin plugin) {
        super(9, "Enchantments");
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        buildItems();
        setItem(2, armor);
        setItem(4, weapons);

    }

    private void buildItems() {

        armor = new ItemStack(Material.DIAMOND_CHESTPLATE);
        armorMeta = armor.getItemMeta();
        armorMeta.setDisplayName(ChatColor.YELLOW + "Armor Enchantments");
        armorMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
        armorMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        armor.setItemMeta(armorMeta);

        weapons = new ItemStack(Material.DIAMOND_SWORD);
        weaponsMeta = weapons.getItemMeta();
        weaponsMeta.setDisplayName(ChatColor.YELLOW + "Weapon Enchantments");
        weaponsMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
        weaponsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        weapons.setItemMeta(weaponsMeta);
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
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (!getPlayerOpenedInventories().contains(event.getPlayer().getUniqueId()))
            return;
        getPlayerOpenedInventories().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (!getPlayerOpenedInventories().contains(event.getPlayer().getUniqueId()))
            return;
        getPlayerOpenedInventories().remove(event.getPlayer().getUniqueId());

    }
}
