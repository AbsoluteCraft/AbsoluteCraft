 package com.boveybrawlers.AbsoluteCraft.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.boveybrawlers.AbsoluteCraft.ACPlayer;
import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.inventory.ItemStack;

 public class JoinItemsInteract implements Listener {
	
	private AbsoluteCraft plugin;
	
	public JoinItemsInteract(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		ACPlayer player = plugin.players.find(p);
		
		if(player == null) {
			plugin.errors.unknownPlayer(p);
			return;
		}

		ItemStack item = p.getInventory().getItemInHand();
		
		if(item != null && p.hasPermission("absolutecraft.joinitems")) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				String displayName = item.getItemMeta().getDisplayName();

				if(item.getType() == Material.SKULL_ITEM && displayName.contains("Profile")) {
					player.openProfileGUI();

					// Stop the block being placed
					event.setCancelled(true);
				} else if(item.getType() == Material.ARMOR_STAND && displayName.contains("Appearance")) {
					// TODO - Open Apperance GUI
					// player.openApperanceGUI();

					// Stop the block being placed
					event.setCancelled(true);
				}
			}
		}
	}
	
}
