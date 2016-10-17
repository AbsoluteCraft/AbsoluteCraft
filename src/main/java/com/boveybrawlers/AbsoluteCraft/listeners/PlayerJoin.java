package com.boveybrawlers.AbsoluteCraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import com.boveybrawlers.AbsoluteCraft.utils.JoinItems;

public class PlayerJoin implements Listener {
	
	AbsoluteCraft plugin;
	
	public PlayerJoin(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = (Player) event.getPlayer();
		
		// Add hub items to the player
		JoinItems.add(player);
	}
	
}