package com.boveybrawlers.AbsoluteCraft.listeners;

import com.boveybrawlers.AbsoluteCraft.ACPlayer;
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

import java.util.logging.Level;

public class JoinItemsMove implements Listener {
	
	private AbsoluteCraft plugin;
	
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
		
		if(clicked != null && clicked.getItemMeta() != null && inventory != null) {
            String displayName = clicked.getItemMeta().getDisplayName();

            if(inventory.getTitle().contains("Warps")) {
                if(displayName.contains("Creative")) {
                    player.closeInventory();

                    if(this.plugin.serverName.equals("creative")) {
                        player.performCommand("warp creative");
                    } else {
                        player.performCommand("server creative");
                    }
                } else if(displayName.contains("Plot")) {
                    player.closeInventory();

                    if(this.plugin.serverName.equals("creative")) {
                        player.performCommand("warp plot");
                    } else {
                        player.performCommand("server creative");
                    }
                } else if(displayName.contains("Survival")) {
                    player.closeInventory();

                    if(this.plugin.serverName.equals("survival")) {
                        player.performCommand("warp spawn");
                    } else {
                        player.performCommand("server survival");
                    }
                }

                event.setCancelled(true);
            } else if(inventory.getTitle().contains("Profile")) {
                if(displayName.contains("profile")) {
                    // Send the player a message with their profile URL
                    player.closeInventory();
                    player.sendMessage(ChatColor.YELLOW + "https://absolutecraft.co.uk/player/" + player.getName());
                } else if(displayName.contains("Achievements")) {
                    // Open the achievements GUI
                    player.performCommand("achievements");
                } else if(displayName.contains("Register")) {
                    // Send the player their Registration info
                    player.closeInventory();
                    player.performCommand("register");
                } else if(displayName.contains("Voting")) {
                    // Send the player information on voting
                    player.closeInventory();
                    player.performCommand("vote");
                } else if(displayName.contains("Feedback")) {
                    // Send the player information on submitting feedback
                    player.closeInventory();
                    player.performCommand("feedback");
                }

                event.setCancelled(true);
            } else if(inventory.getTitle().contains("Appearance")) {
                if(displayName.contains("Armour")) {
                    // Open the armour GUI
                    player.performCommand("armour");
                } else if(displayName.contains("Particle effects")) {
                    // Open the particle effects GUI
                    player.performCommand("upack menu");
                } else if(displayName.contains("Pets")) {
                    // Open the pets GUI
                    player.performCommand("pet menu");
                }

                event.setCancelled(true);
            } else if(inventory.getTitle().contains("Achievements")) {
                // TODO: Load player achievements from the API
                event.setCancelled(true);
            }
		}
	}
	
}