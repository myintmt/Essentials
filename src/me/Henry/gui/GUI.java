package me.Henry.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class GUI implements Listener {

    private Inventory inventory;
    private Map<Integer, Action> actions;
    private Set<UUID> playerOpenedInventories;

    public GUI(int size, String name) {
        inventory = Bukkit.createInventory(null, size, name);
        playerOpenedInventories = new HashSet<>();
        actions = new HashMap<>();
    }

    public interface Action {
        void click(Player player);
    }

    public void setItem(int slot, ItemStack item, Action action) {
        inventory.setItem(slot, item);
        if (action != null) {
            actions.put(slot, action);
        }
    }

    public void setItem(int slot, ItemStack item) {
        setItem(slot, item, null);
    }

    public void open(Player player) {
        player.openInventory(inventory);
        getPlayerOpenedInventories().add(player.getUniqueId());
        afterOpen(player);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Map<Integer, Action> getActions() {
        return actions;
    }

    public Set<UUID> getPlayerOpenedInventories() {
        return playerOpenedInventories;
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
    public void onClose(InventoryCloseEvent event) {
        if (getPlayerOpenedInventories().contains(event.getPlayer().getUniqueId())) {
            afterClose(event);
            getPlayerOpenedInventories().remove(event.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (getPlayerOpenedInventories().contains(event.getPlayer().getUniqueId())) {
            afterQuit(event);
            getPlayerOpenedInventories().remove(event.getPlayer().getUniqueId());
        }
    }

    public abstract void afterOpen(Player player);
    public abstract void afterClose(InventoryCloseEvent event);
    public abstract void afterQuit(PlayerQuitEvent event);

}
