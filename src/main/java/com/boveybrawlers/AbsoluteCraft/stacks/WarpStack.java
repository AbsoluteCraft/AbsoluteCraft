package com.boveybrawlers.AbsoluteCraft.stacks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class WarpStack implements IStack {

    public Map<Integer, ItemStack> get() {
        Map<Integer, ItemStack> warpItems = new HashMap<Integer, ItemStack>();

        ItemStack creative = new ItemStack(Material.GRASS);
        ItemMeta meta = creative.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN + "Creative");
        creative.setItemMeta(meta);
        warpItems.put(2, creative);

        ItemStack plot = new ItemStack(Material.WOOD_AXE);
        meta = plot.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Plot");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        plot.setItemMeta(meta);
        warpItems.put(4, plot);

        ItemStack survival = new ItemStack(Material.STONE_SWORD);
        meta = survival.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Survival");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        survival.setItemMeta(meta);
        warpItems.put(6, survival);

        return warpItems;
    }

    public Inventory asInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9, ChatColor.GREEN + "Warps");
        this.get().forEach(inventory::setItem);

        return inventory;
    }

    public Map<Integer, ItemStack> get(Player player) {
        return null;
    }

}
