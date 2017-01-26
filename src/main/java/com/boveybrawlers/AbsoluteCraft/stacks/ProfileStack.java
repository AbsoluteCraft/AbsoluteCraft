package com.boveybrawlers.AbsoluteCraft.stacks;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import com.boveybrawlers.AbsoluteCraft.utils.Skull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileStack implements IStack {

    AbsoluteCraft plugin;

    public ProfileStack(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    public Map<Integer, ItemStack> get(Player player) {
        Map<Integer, ItemStack> profileItems = new HashMap<Integer, ItemStack>();

        ItemStack playerInfo = Skull.makePlayer(player.getName());
        ItemMeta meta = playerInfo.getItemMeta();

        String name = player.getDisplayName();
        name += player.getDisplayName().endsWith("s") ? "'" : "'s";

        if(this.plugin.chat != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.plugin.chat.getPlayerPrefix(player)) + name + ChatColor.RESET + " profile");
        } else {
            meta.setDisplayName(ChatColor.YELLOW + "[Player]" + name + " profile");
        }
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Click to see your AbsoluteCraft profile link");
        meta.setLore(lore);
        playerInfo.setItemMeta(meta);

        profileItems.put(1, playerInfo);

        ItemStack tokens = new ItemStack(Material.GOLD_INGOT, 1);
        meta = tokens.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Tokens");
        tokens.setItemMeta(meta);

        profileItems.put(3, tokens);

        ItemStack achievements = Skull.makeCustom("13cfbf2bdfd48514bfbace9518c7664112df2c173e8c7ad92b3e65621a9ed6e0"); // Present
        meta = achievements.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Achievements");
        lore.clear();
        lore.add(ChatColor.GRAY + "View your progress on achievements");
        meta.setLore(lore);
        achievements.setItemMeta(meta);

        profileItems.put(5, achievements);

        ItemStack register = Skull.makeCustom("8ae52ae8c98ac19fd07637a469ffa256ab0b3b10ece6243186188ba38df154"); // Computer
        meta = register.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN + "Register");
        lore.clear();
        lore.add(ChatColor.GRAY + "Register on our website");
        lore.add(ChatColor.GRAY + "to edit your profile and view stats");
        meta.setLore(lore);
        register.setItemMeta(meta);

        profileItems.put(7, register);

        ItemStack voting = Skull.makeCustom("b1dd4fe4a429abd665dfdb3e21321d6efa6a6b5e7b956db9c5d59c9efab25"); // Globe
        meta = voting.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Voting");
        lore.clear();
        lore.add(ChatColor.GRAY + "Vote for the server on");
        lore.add(ChatColor.GRAY + "Planet Minecraft");
        meta.setLore(lore);
        voting.setItemMeta(meta);

        profileItems.put(11, voting);

        ItemStack feedback = Skull.makeCustom("5059d59eb4e59c31eecf9ece2f9cf3934e45c0ec476fc86bfaef8ea913ea710"); // Big Grin
        meta = feedback.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Feedback");
        lore.clear();
        lore.add(ChatColor.GRAY + "Make your voice heard by voting");
        lore.add(ChatColor.GRAY + "on ideas and suggesting ideas");
        lore.add(ChatColor.GRAY + "on our public Trello board.");
        meta.setLore(lore);
        feedback.setItemMeta(meta);

        profileItems.put(15, feedback);

        return profileItems;
    }

    public Inventory asInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 18, ChatColor.YELLOW + "Profile");
        this.get(player).forEach(inventory::setItem);

        return inventory;
    }

    public Map<Integer, ItemStack> get() {
        return null;
    }

}
