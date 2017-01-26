package com.boveybrawlers.AbsoluteCraft.listeners;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AppearanceInventoryClick implements Listener {

    private AbsoluteCraft plugin;

    public AppearanceInventoryClick(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if(clicked != null && clicked.getItemMeta() != null && inventory != null) {
            String displayName = clicked.getItemMeta().getDisplayName();

            if(inventory.getTitle().contains("Appearance")) {
                if(displayName.contains("Armour")) {
                    // Open the armour GUI
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "The armour feature is not available yet :(");
                } else if(displayName.contains("Particle effects")) {
                    // Open the particle effects GUI
                    player.performCommand("upack menu");
                } else if(displayName.contains("Pets")) {
                    // Open the pets GUI
                    player.performCommand("pet menu");
                }

                event.setCancelled(true);
            }
        }
    }

}
