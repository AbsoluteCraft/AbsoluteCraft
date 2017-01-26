package com.boveybrawlers.AbsoluteCraft.stacks;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface IStack {

    Map<Integer, ItemStack> get();
    Map<Integer, ItemStack> get(Player player);

    Inventory asInventory(Player player);

}
