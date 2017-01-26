package com.boveybrawlers.AbsoluteCraft.listeners;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ProfileInventoryClick implements Listener {

    private AbsoluteCraft plugin;

    public ProfileInventoryClick(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if(clicked != null && clicked.getItemMeta() != null && inventory != null) {
            String displayName = clicked.getItemMeta().getDisplayName();

            if(inventory.getTitle().contains("Profile")) {
                if(displayName.contains("profile")) {
                    // Send the player a message with their profile URL
                    player.closeInventory();
                    player.sendMessage(ChatColor.YELLOW + "https://absolutecraft.co.uk/player/" + player.getName());
                } else if(displayName.contains("Achievements")) {
                    // Open the achievements GUI
                    player.performCommand("achievements");
                } else if(displayName.contains("Register")) {
                    // Send the player their Registration info
                    player.closeInventory();
                    player.performCommand("register");
                } else if(displayName.contains("Voting")) {
                    // Send the player information on voting
                    player.closeInventory();
                    player.performCommand("vote");
                } else if(displayName.contains("Feedback")) {
                    // Send the player information on submitting feedback
                    player.closeInventory();
                    player.performCommand("feedback");
                }

                event.setCancelled(true);
            }
        }
    }

}
