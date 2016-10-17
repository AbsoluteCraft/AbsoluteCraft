 package com.boveybrawlers.AbsoluteCraft.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.boveybrawlers.AbsoluteCraft.ACPlayer;
import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;

public class JoinItemsInteract implements Listener {
	
	AbsoluteCraft plugin;
	
	public JoinItemsInteract(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		ACPlayer player = plugin.players.find(p);
		
		if(player == null) {
			plugin.errors.unknownPlayer(p);
		}
		
		if(p.hasPermission("absolutecraft.joinitems")) {
			if(p.getInventory().getItemInHand().getType() == Material.SKULL_ITEM) {
				if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().contains("Profile")) {
					if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						player.openProfileGUI();
						
						// Stop the block being placed
						event.setCancelled(true);
					}
				}
			} else if(p.getInventory().getItemInHand().getType() == Material.ARMOR_STAND) {
				if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().contains("Appearance")) {
					if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						// TODO - Open Apperance GUI
						// player.openApperanceGUI();
						
						// Stop the block being placed
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
}
