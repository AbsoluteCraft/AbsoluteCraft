package com.boveybrawlers.AbsoluteCraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.JSONObject;

import com.boveybrawlers.AbsoluteCraft.ACPlayer;
import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import com.boveybrawlers.AbsoluteCraft.utils.APICallback;

public class TokenCommand implements CommandExecutor {
	
	AbsoluteCraft plugin;

	public TokenCommand(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final ACPlayer player = plugin.players.find(sender);
		
		if(player == null) {
			sender.sendMessage(this.plugin.errors.commandPlayerOnly());
			return true;
		}
		
		if(args.length == 0) {
			player.load(new APICallback() {
				public void run(JSONObject resp) {
					player.sendMessage(plugin.prefix + ChatColor.GOLD + player.p.getName() + (player.p.getName().endsWith("s") ? "'" : "'s") + " Tokens: " + ChatColor.GREEN + player.getTokens());
				}
			});
		} else if(args.length == 1) {
			String username = args[1];
			@SuppressWarnings("deprecation")
			OfflinePlayer offline = Bukkit.getOfflinePlayer(username);
			if(offline == null) {
				sender.sendMessage(plugin.prefix + ChatColor.RED + "That player does not exist");
				return true;
			}
			
			final ACPlayer acPlayer = new ACPlayer(this.plugin);
			acPlayer.setPlayer(offline);
			
			acPlayer.load(new APICallback() {
				public void run(JSONObject resp) {
					player.sendMessage(plugin.prefix + ChatColor.GOLD + acPlayer.p.getName() + (acPlayer.p.getName().endsWith("s") ? "'" : "'s") + " Tokens: " + ChatColor.GREEN + acPlayer.getTokens());					
				}
			});
		} else if(args.length == 3 && player.p.isOp()) {
			if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {
				String username = args[1];
				Integer amount = 0;
				try { 
			        amount = Integer.parseInt(args[2]); 
			    } catch(NumberFormatException e) { 
			    	sender.sendMessage(plugin.prefix + ChatColor.RED + "The amount specified was invalid");
			    	sender.sendMessage(plugin.prefix + "Usage: /tokens add <player> <amount>");
			    	return true;
			    }
				
				@SuppressWarnings("deprecation")
				OfflinePlayer offline = Bukkit.getOfflinePlayer(username);
				if(offline == null) {
					sender.sendMessage(plugin.prefix + ChatColor.RED + "That player does not exist.");
					return true;
				}
				
				ACPlayer acPlayer = new ACPlayer(this.plugin);
				acPlayer.setPlayer(offline);
				
				acPlayer.addTokens(amount);
				acPlayer.sendMessage(plugin.prefix + ChatColor.GOLD + "Tokens were added to you by " + player.p.getName() + ChatColor.GREEN + " [+" + amount + "]");
				
				sender.sendMessage(plugin.prefix + "Added " + amount + " " + ((amount > 1) ? "tokens" : "token") + " to " + username);
				
				return true;
			}
		}
		
		sender.sendMessage(plugin.prefix + "Usage: /tokens");
		sender.sendMessage(plugin.prefix + "Usage: /tokens <player>");
		
		return true;
	}

	
	
}
