package com.boveybrawlers.AbsoluteCraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.boveybrawlers.AbsoluteCraft.ACPlayer;
import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;

public class RegisterCommand implements CommandExecutor {

	private AbsoluteCraft plugin;

	public RegisterCommand(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ACPlayer player = plugin.players.find(sender);
		
		if(player == null) {
			sender.sendMessage(this.plugin.errors.commandPlayerOnly());
			return true;
		}
		
		if(args.length == 0) {
			if(player.isRegistered()) {
				player.sendMessage(ChatColor.BLUE + "You are already registered with the email " + ChatColor.DARK_GREEN + player.getEmail());
				player.sendMessage("If you have forgotten your login details, please go to " + ChatColor.YELLOW + "https://absolutecraft.co.uk/recover/password");
				player.sendMessage(ChatColor.RED + "If you believe this is an error, please tell an Operator or contact " + ChatColor.WHITE + "hello@absolutecraft.co.uk");
			} else {
				String code = this.generateCode(player);
				
				player.sendMessage(ChatColor.AQUA + "Your " + ChatColor.GRAY + "Absolute" + ChatColor.DARK_GREEN + "Craft " + ChatColor.AQUA + "Registration Code");
				player.sendMessage(ChatColor.DARK_GREEN + "----------------------------------------");
				player.sendMessage(ChatColor.AQUA + "Your registration code is " + ChatColor.BLUE + code + ".");
				player.sendMessage(ChatColor.DARK_GREEN + "----------------------------------------");				
				player.sendMessage(ChatColor.GRAY + "Go to " + ChatColor.DARK_GREEN + "http://absolutecraft.co.uk/register " + ChatColor.GRAY + "to register on the website with this code.");
			}
		} else {
			player.sendMessage(plugin.prefix + ChatColor.GRAY + "Register on the AbsoluteCraft website with the code given with /register");
		}
		
		return true;
	}
	
	private String generateCode(ACPlayer player) {
		String username = player.p.getName().toLowerCase().replaceAll("[^a-z]", "").substring(0, 4);
		
		int letter0;
		int letter1;
		int letter2;
		int letter3;
		
		char[] letters  = username.toCharArray();
		
		if(letters.length >= 4) {
			letter0 = (int)letters[0] - 96;
			letter1 = (int)letters[1] - 96;
			letter2 = (int)letters[2] - 96;
			letter3 = (int)letters[3] - 96;
		} else if(letters.length == 3) {
			letter0 = (int)letters[0] - 96;
			letter1 = (int)letters[1] - 96;
			letter2 = (int)letters[2] - 96;
			letter3 = 0;
		} else if(letters.length == 2) {
			letter0 = (int)letters[0] - 96;
			letter1 = (int)letters[1] - 96;
			letter2 = 0;
			letter3 = 0;
		} else {
			letter0 = (int)letters[0] - 96;
			letter1 = 0;
			letter2 = 0;
			letter3 = 0;
		}
		
		String code0 = String.format("%02d", letter0);
		String code1 = String.format("%02d", letter1);
		String code2 = String.format("%02d", letter2);
		String code3 = String.format("%02d", letter3);
		
		return code0 + "-" + code1 + "-" + code2 + "-" + code3;
	}
	
}
