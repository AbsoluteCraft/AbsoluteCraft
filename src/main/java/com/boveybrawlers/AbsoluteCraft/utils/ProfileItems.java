package com.boveybrawlers.AbsoluteCraft.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;

public class ProfileItems {

	AbsoluteCraft plugin;
	
	private Inventory inventory;
	
	public Inventory getInventory(Player player) {
		ItemStack playerInfo = new ItemStack(Material.SKULL_ITEM);
		SkullMeta skullmeta = (SkullMeta) playerInfo.getItemMeta();
		skullmeta.setOwner(player.getName());
		
		String name;
		if(!player.getDisplayName().endsWith("s")) {
			name = player.getDisplayName() + "'s";
		} else {
			name = player.getDisplayName() + "'";
		}
		if(plugin.chat != null) {
			skullmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.chat.getPlayerPrefix(player)) + name + ChatColor.RESET + " profile");
		} else {
			skullmeta.setDisplayName(ChatColor.YELLOW + "[Player]" + name + " profile");
		}
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Click to see your AbsoluteCraft profile link");
		skullmeta.setLore(lore);
		playerInfo.setItemMeta(skullmeta);
		
		this.inventory.setItem(1, playerInfo);
		
		ItemStack tokens = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta meta = tokens.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Tokens");
		tokens.setItemMeta(meta);
		
		this.inventory.setItem(3, tokens);
		
		ItemStack achievements = new ItemStack(Material.EMERALD, 1);
		meta = achievements.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Achievements");
		lore.clear();
		lore.add(ChatColor.GRAY + "View your progress on achievements");
		meta.setLore(lore);
		achievements.setItemMeta(meta);
		
		this.inventory.setItem(5, achievements);
		
		ItemStack register = new ItemStack(Material.NAME_TAG, 1);
		meta = register.getItemMeta();
		lore.clear();
		lore.add(ChatColor.GRAY + "Register on our website");
		lore.add(ChatColor.GRAY + "to edit your profile and view stats");
		meta.setLore(lore);
		register.setItemMeta(meta);
		
		this.inventory.setItem(7, register);
		
		ItemStack voting = new ItemStack(Material.PAPER, 1);
		meta = voting.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Voting");
		lore.clear();
		lore.add(ChatColor.GRAY + "Vote for the server");
		meta.setLore(lore);
		voting.setItemMeta(meta);
		
		this.inventory.setItem(11, voting);
		
		ItemStack surveys = new ItemStack(Material.SIGN, 1);
		meta = surveys.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Surveys");
		lore.clear();
		lore.add(ChatColor.GRAY + "Make your voice heard by voting");
		lore.add(ChatColor.GRAY + "on ideas and suggesting ideas");
		lore.add(ChatColor.GRAY + "for the server");
		meta.setLore(lore);
		surveys.setItemMeta(meta);
		
		this.inventory.setItem(15, surveys);
		
		return this.inventory;
	}
	
	public void setTokens(int tokens) {
		ItemStack gold = this.inventory.getItem(3);
		ItemMeta meta = gold.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "You have " + ChatColor.GREEN + tokens + ChatColor.GRAY + " Tokens");
		meta.setLore(lore);
		gold.setItemMeta(meta);
	}
	
	public void setRegistered(boolean registered) {
		ItemStack register = this.inventory.getItem(5);
		ItemMeta meta = register.getItemMeta();
		
		if(registered) {
			meta.setDisplayName(ChatColor.DARK_GREEN + "Registered ✔");
		} else {
			meta.setDisplayName(ChatColor.DARK_GREEN + "Register");
		}
		register.setItemMeta(meta);
	}
	
}
