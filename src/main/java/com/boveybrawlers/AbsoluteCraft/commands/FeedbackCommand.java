package com.boveybrawlers.AbsoluteCraft.commands;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FeedbackCommand implements CommandExecutor {

    private AbsoluteCraft plugin;

    public FeedbackCommand(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "Submit and vote on ideas and issues on our Trello board:");
        sender.sendMessage("https://trello.com/b/pcS9kKji");

        return true;
    }

}