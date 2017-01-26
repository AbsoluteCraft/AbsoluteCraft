package com.boveybrawlers.AbsoluteCraft.listeners;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AchievementsInventoryClick implements Listener {

    private AbsoluteCraft plugin;

    public AchievementsInventoryClick(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if(clicked != null && clicked.getItemMeta() != null && inventory != null) {
            String displayName = clicked.getItemMeta().getDisplayName();

            if(inventory.getTitle().contains("Achievements")) {
                // TODO: Load player achievements from the API
                event.setCancelled(true);
            }
        }
    }

}
