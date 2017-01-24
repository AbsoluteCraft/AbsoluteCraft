package com.boveybrawlers.AbsoluteCraft.utils;

import java.util.logging.Level;

import org.bukkit.ChatColor;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;

public class ErrorUtil {
	
	private AbsoluteCraft plugin;
	private String prefix = ChatColor.RED + "[Error] ";
	
	public ErrorUtil(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}
	
	public String unknownPlayer(Object player) {
		String error = "Unknown Player";
		this.plugin.getLogger().log(Level.WARNING, error, player);
		return prefix + error;
	}
	
	public String commandPlayerOnly() {
		return prefix + "This command can only be used by a player";
	}

}
