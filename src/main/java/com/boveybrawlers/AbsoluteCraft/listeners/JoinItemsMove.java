package com.boveybrawlers.AbsoluteCraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;

public class JoinItemsMove implements Listener {
	
	AbsoluteCraft plugin;
	
	public JoinItemsMove(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryCreative(InventoryCreativeEvent event) {
		// Stop defaults from accessing blocks, items or changing armour/hats
		Player player = (Player) event.getWhoClicked();
		
		if(player.getWorld().getName().equalsIgnoreCase("creative") && !player.hasPermission("essentials.gamemode")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inventory = event.getInventory();
		
		if(clicked != null && inventory != null) {
			if(clicked.getItemMeta() != null && clicked.getItemMeta().getDisplayName() != null && inventory.getName() != null) {
				if(inventory.getName().contains("Profile")) {
					if(clicked.getItemMeta().getDisplayName().contains("profile")) {
						player.closeInventory();
						player.sendMessage(ChatColor.YELLOW + "http://absolutecraft.co.uk/player/" + player.getName());
					} else if(clicked.getItemMeta().getDisplayName().contains("Achievements")) {
						if(clicked.getType() == Material.EMERALD) {
							player.performCommand("/achievements");
						}
					} else if(clicked.getItemMeta().getDisplayName().contains("Register")) {
						player.closeInventory();
						player.performCommand("/register");
					} else if(clicked.getItemMeta().getDisplayName().contains("Voting")) {
						player.closeInventory();
						player.performCommand("/vote");
					} else if(clicked.getItemMeta().getDisplayName().contains("Surveys")) {
						player.closeInventory();
						player.performCommand("/surveys");
					}
					
					event.setCancelled(true);	
				} else if(inventory.getName().contains("Appearance")) {
					if(clicked.getItemMeta().getDisplayName().contains("Armour")) {
						player.closeInventory();
						player.performCommand("/armour");
					} else if(clicked.getItemMeta().getDisplayName().contains("Particle effects")) {
						player.performCommand("/upack menu");
					} else if(clicked.getItemMeta().getDisplayName().contains("Pets")) {
						player.performCommand("/pet menu");
					}
					
					event.setCancelled(true);
				} else if(inventory.getName().contains("Achievements")) {
					event.setCancelled(true);
				}
			}
		}
	}
	
}