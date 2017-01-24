package com.boveybrawlers.AbsoluteCraft.utils;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WarpItems {

    private AbsoluteCraft plugin;

    private Inventory inventory = null;

    public WarpItems(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    public Inventory getInventory(Player player) {
        this.inventory = Bukkit.createInventory(player, 9, "Warps");

        ItemStack creative = new ItemStack(Material.GRASS);
        ItemMeta meta = creative.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN + "Creative");gi
        creative.setItemMeta(meta);
        this.inventory.setItem(2, creative);

        ItemStack plot = new ItemStack(Material.WOOD_AXE);
        meta = plot.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Plot");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        plot.setItemMeta(meta);
        this.inventory.setItem(4, plot);

        ItemStack survival = new ItemStack(Material.STONE_SWORD);
        meta = survival.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Survival");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        survival.setItemMeta(meta);
        this.inventory.setItem(6, survival);

        return this.inventory;
    }

}
