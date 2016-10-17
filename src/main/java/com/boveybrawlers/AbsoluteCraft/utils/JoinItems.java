package com.boveybrawlers.AbsoluteCraft.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class JoinItems {
	
	public static void add(Player player) {
		if(player.hasPermission("absolutecraft.joinitems")) {
			// Set slot 1 to a compass
			ItemStack compass = new ItemStack(Material.COMPASS, 1);
			ItemMeta meta = compass.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Warps");
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "View the different servers you can connect");
			lore.add(ChatColor.GRAY + "to on Absolute" + ChatColor.GREEN + "Craft" + ChatColor.GRAY + "!");
			meta.setLore(lore);
			compass.setItemMeta(meta);
			
			if(!player.getInventory().contains(compass)) {
				if(player.getInventory().getItem(1) != null && !player.getInventory().getItem(1).getType().equals(Material.COMPASS)) {
					ItemStack item = player.getInventory().getItem(1);
					player.getInventory().addItem(item);
				}
				
				player.getInventory().setItem(0, compass);
			}
			
			// Set slot 2 to the player's head
			ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta skullmeta = (SkullMeta) head.getItemMeta();
			skullmeta.setOwner(player.getName());
			skullmeta.setDisplayName(ChatColor.YELLOW + "Profile");
			lore.clear();
			lore.add(ChatColor.GRAY + "View your player profile");
			skullmeta.setLore(lore);
			head.setItemMeta(skullmeta);
			
			if(!player.getInventory().contains(head)) {
				if(player.getInventory().getItem(1) != null && !player.getInventory().getItem(1).getType().equals(Material.SKULL_ITEM)) {
					ItemStack item = player.getInventory().getItem(1);
					player.getInventory().addItem(item);
				}
						
				player.getInventory().setItem(1, head);
			}
			
			// Set slot 3 to an armor stand
			ItemStack armorstand = new ItemStack(Material.ARMOR_STAND, 1);
			meta = armorstand.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Appearance");
			lore.clear();
			lore.add(ChatColor.GRAY + "Customise your armour,");
			lore.add(ChatColor.GRAY + "particles or pets");
			meta.setLore(lore);
			armorstand.setItemMeta(meta);
			
			if(player.getInventory().contains(armorstand)) {
				if(player.getInventory().getItem(2) != null && !player.getInventory().getItem(2).getType().equals(Material.ARMOR_STAND)) {
					ItemStack item = player.getInventory().getItem(2);
					player.getInventory().addItem(item);
				}
				
				player.getInventory().setItem(2, armorstand);
			}
		}
	}
	
}
