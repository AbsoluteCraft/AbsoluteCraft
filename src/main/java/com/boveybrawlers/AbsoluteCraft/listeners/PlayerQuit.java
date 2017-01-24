package com.boveybrawlers.AbsoluteCraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private AbsoluteCraft plugin;

    public PlayerQuit(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Remove from the players manager
        this.plugin.players.remove(player);
    }

}