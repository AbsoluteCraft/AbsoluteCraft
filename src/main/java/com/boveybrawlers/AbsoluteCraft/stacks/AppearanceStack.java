package com.boveybrawlers.AbsoluteCraft.stacks;

import com.boveybrawlers.AbsoluteCraft.utils.Skull;
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

public class AppearanceStack implements IStack {

    public Map<Integer, ItemStack> get() {
        Map<Integer, ItemStack> appearanceItems = new HashMap<Integer, ItemStack>();

        ItemStack armour = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta meta = armour.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Armour");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        armour.setItemMeta(meta);
        appearanceItems.put(2, armour);

        ItemStack particle = new ItemStack(Material.NETHER_STAR);
        meta = particle.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Particle effects");
        particle.setItemMeta(meta);
        appearanceItems.put(4, particle);

        ItemStack pets = Skull.makeCustom("1638469a599ceef7207537603248a9ab11ff591fd378bea4735b346a7fae893"); // Chicken
        meta = pets.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Pets");
        pets.setItemMeta(meta);
        appearanceItems.put(6, pets);

        return appearanceItems;
    }

    public Inventory asInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9, ChatColor.GOLD + "Appearance");
        this.get().forEach(inventory::setItem);

        return inventory;
    }

    public Map<Integer, ItemStack> get(Player player) {
        return null;
    }

}
