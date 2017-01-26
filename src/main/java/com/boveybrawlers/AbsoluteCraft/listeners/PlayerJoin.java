package com.boveybrawlers.AbsoluteCraft.listeners;

import com.boveybrawlers.AbsoluteCraft.stacks.JoinStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class PlayerJoin implements Listener {
	
	private AbsoluteCraft plugin;
	
	public PlayerJoin(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		// Add to the players manager
		this.plugin.players.add(player);

        Map<Integer, ItemStack> joinItems = new JoinStack().get(player);
		
		// Add hub items to the player
		if(player.hasPermission("absolutecraft.joinitems")) {
            joinItems.forEach((Integer i, ItemStack item) -> {
                // Store the reference to the existing slot
                ItemStack existing = player.getInventory().getItem(i);
                player.getInventory().setItem(i, item);

                if(existing != null) {
                    existing = existing.clone();
                    player.getInventory().addItem(existing);
                }
            });
        } else {
            joinItems.forEach((Integer i, ItemStack item) -> player.getInventory().remove(item));
        }
	}
	
}