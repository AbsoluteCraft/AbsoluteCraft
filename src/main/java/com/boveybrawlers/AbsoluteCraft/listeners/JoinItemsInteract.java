 package com.boveybrawlers.AbsoluteCraft.listeners;

import com.boveybrawlers.AbsoluteCraft.stacks.AppearanceStack;
import com.boveybrawlers.AbsoluteCraft.stacks.WarpStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCreativeEvent;
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

		ItemStack item = p.getInventory().getItemInMainHand();
		
		if(item != null && item.getItemMeta() != null && p.hasPermission("absolutecraft.joinitems")) {
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				String displayName = item.getItemMeta().getDisplayName();

				if(item.getType() == Material.COMPASS && displayName.contains("Warps")) {
                    p.openInventory((new WarpStack()).asInventory(p));

				    // Stop the block being placed
				    event.setCancelled(true);
                } else if(item.getType() == Material.SKULL_ITEM && displayName.contains("Profile")) {
					player.openProfileGUI();

					// Stop the block being placed
					event.setCancelled(true);
				} else if(item.getType() == Material.ARMOR_STAND && displayName.contains("Appearance")) {
                    p.openInventory(new AppearanceStack().asInventory(p));

					// Stop the block being placed
					event.setCancelled(true);
				}
			}
		}
	}

     @EventHandler
     public void onInventoryCreative(InventoryCreativeEvent event) {
         // Stop defaults from accessing blocks, items or changing armour/hats
         Player player = (Player) event.getWhoClicked();

         if(player.getWorld().getName().equalsIgnoreCase("creative") && !player.hasPermission("essentials.gamemode")) {
             event.setCancelled(true);
         }
     }
	
}
