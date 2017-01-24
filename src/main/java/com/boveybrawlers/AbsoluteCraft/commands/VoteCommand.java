package com.boveybrawlers.AbsoluteCraft.commands;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VoteCommand implements CommandExecutor {

    private AbsoluteCraft plugin;

    public VoteCommand(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.AQUA + "Please vote for us on Planet Minecraft: " + ChatColor.WHITE + "http://bit.ly/absolutevote");

        return true;
    }

}