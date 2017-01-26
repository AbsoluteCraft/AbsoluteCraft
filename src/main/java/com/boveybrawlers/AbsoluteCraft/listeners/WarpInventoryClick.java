package com.boveybrawlers.AbsoluteCraft.listeners;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WarpInventoryClick implements Listener {

    private AbsoluteCraft plugin;

    public WarpInventoryClick(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if(clicked != null && clicked.getItemMeta() != null && inventory != null) {
            String displayName = clicked.getItemMeta().getDisplayName();

            if(inventory.getTitle().contains("Warps")) {
                if(displayName.contains("Creative")) {
                    player.closeInventory();

                    if(this.plugin.serverName.equals("creative")) {
                        player.performCommand("warp creative");
                    } else {
                        player.performCommand("server creative");
                    }
                } else if(displayName.contains("Plot")) {
                    player.closeInventory();

                    if(this.plugin.serverName.equals("creative")) {
                        player.performCommand("warp plot");
                    } else {
                        player.performCommand("server creative");
                    }
                } else if(displayName.contains("Survival")) {
                    player.closeInventory();

                    if(this.plugin.serverName.equals("survival")) {
                        player.performCommand("warp spawn");
                    } else {
                        player.performCommand("server survival");
                    }
                }

                event.setCancelled(true);
            }
        }
    }

}
