package com.boveybrawlers.AbsoluteCraft.commands;

import com.boveybrawlers.AbsoluteCraft.utils.APILoadCallback;
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
	
	private AbsoluteCraft plugin;

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
		    player.getTokens(new APILoadCallback() {
                public void run(Object response) {
                    int tokens = (Integer) response;
                    player.sendMessage(plugin.prefix + ChatColor.GOLD + player.p.getName() + (player.p.getName().endsWith("s") ? "'" : "'s") + " Tokens: " + ChatColor.GREEN + tokens);
                }
            });

			return true;
		} else if(args.length == 1) {
			String username = args[0];
			@SuppressWarnings("deprecation")
			OfflinePlayer offline = Bukkit.getOfflinePlayer(username);
			if(offline == null) {
				sender.sendMessage(plugin.prefix + ChatColor.RED + "That player does not exist");
				return true;
			}
			
			final ACPlayer otherPlayer = new ACPlayer(this.plugin);
			otherPlayer.setPlayer(offline);

            otherPlayer.getTokens(new APILoadCallback() {
                public void run(Object response) {
                    int tokens = (Integer) response;
                    player.sendMessage(plugin.prefix + ChatColor.GOLD + otherPlayer.p.getName() + (otherPlayer.p.getName().endsWith("s") ? "'" : "'s") + " Tokens: " + ChatColor.GREEN + tokens);
                }
            });

			return true;
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
				
				ACPlayer recipient = new ACPlayer(this.plugin);
                recipient.setPlayer(offline);

                recipient.addTokens(amount, player);
                recipient.sendMessage(plugin.prefix + ChatColor.GOLD + "Tokens were added to you by " + player.p.getName() + ChatColor.GREEN + " [+" + amount + "]");
				
				sender.sendMessage(plugin.prefix + "Added " + amount + " " + ((amount > 1) ? "tokens" : "token") + " to " + username);
				
				return true;
			}
		}
		
		sender.sendMessage(plugin.prefix + "Usage: /tokens");
		sender.sendMessage(plugin.prefix + "Usage: /tokens <player>");
		
		return true;
	}

	
	
}
