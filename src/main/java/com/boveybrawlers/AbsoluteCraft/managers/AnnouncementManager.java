package com.boveybrawlers.AbsoluteCraft.managers;

import com.boveybrawlers.AbsoluteCraft.ACPlayer;
import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import com.boveybrawlers.AbsoluteCraft.Announcement;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.bukkit.scheduler.BukkitScheduler;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class AnnouncementManager {

    public AbsoluteCraft plugin;
    private List<Announcement> announcements = new ArrayList<Announcement>();
    private int current = -1;

    public AnnouncementManager(AbsoluteCraft plugin) {
        this.plugin = plugin;
    }

    public Announcement add(String message) {
        Announcement announcement = new Announcement(message);
        this.announcements.add(announcement);

        return announcement;
    }

    public void load() {
        this.plugin.client.get("/announcements", new Callback<JsonNode>() {
            public void cancelled() {
                plugin.getLogger().log(Level.SEVERE, "API call cancelled");
            }
            public void failed(UnirestException e) {
                plugin.getLogger().log(Level.SEVERE, "Failed to load /announcements", e);
            }

            public void completed(HttpResponse<JsonNode> response) {
                JSONArray resp = response.getBody().getArray();

                for(int i = 0 ; i < resp.length(); i++) {
                    String message = resp.getString(i);

                    add(message);
                }

                BukkitScheduler scheduler = plugin.getServer().getScheduler();
                scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
                    public void run() {
                        current++;

                        // Loop back around to the beginning
                        if(current == announcements.size()) {
                            current = 0;
                        }

                        Announcement announcement = announcements.get(current);

                        List<ACPlayer> players = plugin.players.all();
                        for(ACPlayer player : players) {
                            if(player.isOnline() && !player.hasPermission("absolutecraft.announcements.ignore")){
                                player.sendMessage(announcement.getMessage());
                            }
                        }
                    }
                }, 2400L, 4800L); // 2 minutes after server load, every 4 minutes
            }
        });
    }

}
