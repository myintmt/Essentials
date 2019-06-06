package me.Henry.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private ItemStack item;

    public ItemBuilder(Material material, int amount) {
        item = new ItemStack(material, amount);
    }

    public ItemBuilder setDisplayName(String name) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (itemMeta.hasLore())
            lore = new ArrayList<>(itemMeta.getLore());
        lore.add(line);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLoreLine(String line, int index) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (itemMeta.hasLore())
            lore = new ArrayList<>(itemMeta.getLore());
        lore.set(index, line);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder removeLoreLine(String line) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        if (!lore.contains(line))
            return this;
        lore.remove(line);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder removeLoreLine(int index) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        if (index < 0 || index > lore.size())
            return this;
        lore.remove(index);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack toItemStack() {
        return item;
    }

}
