package com.boveybrawlers.AbsoluteCraft.commands;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AchievementsCommand implements CommandExecutor {

    private AbsoluteCraft plugin;

    public AchievementsCommand(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.RED + "Coming soon!");
        sender.sendMessage(ChatColor.RED + "We're currently working on our Achievements system.");

        return true;
    }

}