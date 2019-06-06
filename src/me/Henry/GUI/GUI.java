package me.Henry.GUI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class GUI {

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
        getPlayerOpenedInventories().add(player.getUniqueId());
        player.openInventory(inventory);
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

}
