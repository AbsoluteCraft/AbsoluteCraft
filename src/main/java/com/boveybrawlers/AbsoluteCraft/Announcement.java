package com.boveybrawlers.AbsoluteCraft;

import org.bukkit.ChatColor;

public class Announcement {

    private String message;

    public Announcement(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
