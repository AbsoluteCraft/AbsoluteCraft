package com.boveybrawlers.AbsoluteCraft.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.boveybrawlers.AbsoluteCraft.ACPlayer;
import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;

public class PlayerManager {
	
	public AbsoluteCraft plugin;
	private List<ACPlayer> players = new ArrayList<ACPlayer>();
	
	public PlayerManager(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}

	/**
	 * Get a list of all players
	 *
	 * @return List of ACPlayers
	 */
	public List<ACPlayer> all() {
		return this.players;
	}
	
	/**
	 * Add a Player to the players list
	 * 
	 * @param player
	 * @return ACPlayer
	 */
	public ACPlayer add(Player player) {
		ACPlayer acPlayer = new ACPlayer(this.plugin);
		acPlayer.setPlayer(player);
		this.players.add(acPlayer);
		
		return acPlayer;
	}
	
	/**
	 * Remove an ACPlayer from the players list
	 * 
	 * @param player
	 * @return
	 */
	public boolean remove(ACPlayer player) {
		return this.players.remove(player);
	}
	
	/**
	 * Remove a Player from the players list
	 * 
	 * @param player
	 * @return
	 */
	public boolean remove(Player player) {
		ACPlayer acPlayer = this.find(player);
		if(acPlayer != null) {
			return this.players.remove(acPlayer);
		}
		
		return false;
	}
	
	/**
	 * Find an ACPlayer by username
	 * 
	 * @param username
	 * @return
	 */
	public ACPlayer find(String username) {
		for(ACPlayer player : players) {
			if(player.p.getName().equals(username)) {
				return player;
			}
		}
		
		return null;
	}
	
	/**
	 * Find an ACPlayer by Player
	 * 
	 * @param player
	 * @return
	 */
	public ACPlayer find(Player player) {
        for(ACPlayer acPlayer : players) {
            OfflinePlayer offP = (OfflinePlayer) acPlayer.getPlayer();
            if(offP.getUniqueId().equals(player.getUniqueId())) {
                return acPlayer;
            }
        }
		
		return null;
	}

	/**
	 * Find an ACPlayer by CommandSender
	 * 
	 * @param sender
	 * @return
	 */
	public ACPlayer find(CommandSender sender) {
		if(sender instanceof Player) {
			return this.find((Player) sender);
		}
		
		return null;
	}

}
