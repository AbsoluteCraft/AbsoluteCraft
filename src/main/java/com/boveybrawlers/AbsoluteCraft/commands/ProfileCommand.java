package com.boveybrawlers.AbsoluteCraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.boveybrawlers.AbsoluteCraft.ACPlayer;
import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;

public class ProfileCommand implements CommandExecutor {
	
	private AbsoluteCraft plugin;

	public ProfileCommand(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ACPlayer player = plugin.players.find(sender);
		
		if(player == null) {
			sender.sendMessage(this.plugin.errors.commandPlayerOnly());
			return true;
		}
		
		if(args.length == 0) {
			player.openProfileGUI();
		} else {
			player.sendPrefixedMessage("Show your profile GUI with /profile");
		}
		
		return true;
	}

}
