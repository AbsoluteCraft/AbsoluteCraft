package com.boveybrawlers.AbsoluteCraft.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.boveybrawlers.AbsoluteCraft.ACPlayer;
import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;

public class HelpCommand implements CommandExecutor {

	AbsoluteCraft plugin;
	
	public HelpCommand(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ACPlayer player = plugin.players.find(sender);
		
		if(player == null) {
			sender.sendMessage(this.plugin.errors.commandPlayerOnly());
			return true;
		}
		
		if(args.length == 0) {
			player.sendMessage(ChatColor.GRAY + "Absolute" + ChatColor.DARK_GREEN + "Craft " + ChatColor.WHITE + "Help");
			player.sendMessage("Click the green section titles to get help in that category");
			player.sendMessage(ChatColor.DARK_GRAY + "----------------------------------------");
			
			player.sendClickableCommand("General",
					net.md_5.bungee.api.ChatColor.DARK_GREEN,
					"/help general",
					ChatColor.GRAY + " - Navigating and chatting on the server");
			
			player.sendClickableCommand("Survival",
					net.md_5.bungee.api.ChatColor.DARK_GREEN,
					"/help survival",
					ChatColor.GRAY + " - Protecting land and shopping");
			
			player.sendClickableCommand("Plot",
					net.md_5.bungee.api.ChatColor.DARK_GREEN, 
					"/help plot", 
					ChatColor.GRAY + " - Building in the plot world");
			
			player.sendClickableCommand("Games",
					net.md_5.bungee.api.ChatColor.DARK_GREEN,
					"/help games",
					ChatColor.GRAY + " - Information on games and tokens");
			
			player.sendClickableCommand("Donating",
					net.md_5.bungee.api.ChatColor.DARK_GREEN,
					"/help donating",
					ChatColor.GRAY + " - Information on donating");
			
			player.sendClickableCommand("About",
					net.md_5.bungee.api.ChatColor.DARK_GREEN,
					"/help about",
					ChatColor.GRAY + " - Information on the server");
		} else {
			List<String> commands = new ArrayList<String>();
			
			if(args[0].equalsIgnoreCase("general")) {
				commands.add("/help warping" + ChatColor.GRAY + " - Navigate around the server");
				commands.add("/help messaging" + ChatColor.GRAY + " - Chat and message other players");
				this.sendHelpSection(player, "General", commands);
			} else if(args[0].equalsIgnoreCase("survival")) {
				commands.add("/help protecting" + ChatColor.GRAY + " - Protect your land from others");
				commands.add("/help shopping" + ChatColor.GRAY + " - Buy and sell items");
				this.sendHelpSection(player, "Survival", commands);
			} else if(args[0].equalsIgnoreCase("plot")) {
				commands.add("/plot auto" + ChatColor.GRAY + " - Claims an empty plot that you can start building in");
				commands.add("/plot home:[#]" + ChatColor.GRAY + " - Brings you back to your plot");
				commands.add(ChatColor.GRAY + "Replace [#] with a number to get to your other plots");
				commands.add("/plot list" + ChatColor.GRAY + " - Lists your plots");
				commands.add("/plot clear" + ChatColor.GRAY + " - Removes all blocks from your plot");
				commands.add("/plot add <player>" + ChatColor.GRAY + " - Gives permision for another player to build on your plot");
				commands.add("/plot remove <player>" + ChatColor.GRAY + " - Removes permission for another player to build on your plot");
				this.sendHelpSection(player, "Plot", commands);
			} else if(args[0].equalsIgnoreCase("games")) {
				commands.add(ChatColor.AQUA + "Playing and winning games on AbsoluteCraft will give you " + ChatColor.GOLD + "Tokens " + ChatColor.AQUA + "which you can spend at http://absolutecraft.co.uk/shop");
				commands.add("/tokens" + ChatColor.GRAY + " - Displays your Token balance");
				commands.add("/toptokens" + ChatColor.GRAY + " - Displays the current Token leaderboard");
				this.sendHelpSection(player, "Games", commands);
			} else if(args[0].equalsIgnoreCase("donate")) {
				player.sendMessage(ChatColor.GOLD + "Donating on " + ChatColor.GRAY + "Absolute" + ChatColor.DARK_GREEN + "Craft");
				player.sendMessage(ChatColor.GOLD + "----------------------------------------");
				player.sendMessage(ChatColor.GRAY + "The AbsoluteCraft server is able to run because of generous donations from our players. In return we offer the " + ChatColor.GOLD + "[VIP] " + ChatColor.GRAY + "rank to donators!");
				player.sendMessage(ChatColor.GRAY + "Here is a list of some of the perks you can receive:");
				player.sendMessage(ChatColor.GOLD + "- An awesome [VIP] chat prefix in server chat");
				player.sendMessage(ChatColor.GOLD + "- Access to hats, pets, particle effects and nicknames");
				player.sendMessage(ChatColor.GOLD + "- Join the server even when it's full");
				player.sendMessage(ChatColor.GOLD + "- 3 extra plots (6 in total) in the Plot world");
				player.sendMessage(ChatColor.GOLD + "- No expiration date - Lifetime rank!");
				player.sendMessage(ChatColor.GOLD + "If you want to donate any amount or get ranks please visit " + ChatColor.DARK_GREEN + "http://absolutecraft.co.uk/shop");
				player.sendMessage(ChatColor.GOLD + "----------------------------------------");
			} else if(args[0].equalsIgnoreCase("about")) {
				player.sendMessage(ChatColor.GRAY + "Absolute" + ChatColor.DARK_GREEN + "Craft" + ChatColor.AQUA + " v" + plugin.getDescription().getVersion() + ChatColor.GRAY + " by" + ChatColor.RED + " boveybrawlers");
				player.sendMessage(ChatColor.DARK_GREEN + "----------------------------------------");
				player.sendMessage(ChatColor.GRAY + "Visit our website for more details: " + ChatColor.DARK_GREEN + "http://absolutecraft.co.uk");
				player.sendMessage(ChatColor.DARK_GREEN + "----------------------------------------");
			} else {
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Sorry, that help section was not found. Try /help.");
			}
		}
		
		return true;
	}
	
	public void sendHelpSection(ACPlayer player, String name, List<String> commands) {
		player.sendMessage(ChatColor.GRAY + "Absolute" + ChatColor.DARK_GREEN + "Craft " + ChatColor.WHITE + name + " Help");
		player.sendMessage(ChatColor.DARK_GREEN + "----------------------------------------");
		
		for(String cmd : commands) {
			player.sendMessage(cmd);
		}
		
		player.sendMessage(ChatColor.DARK_GREEN + "----------------------------------------");
	}
	
}
