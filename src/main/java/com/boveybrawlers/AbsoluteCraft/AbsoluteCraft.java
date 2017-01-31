package com.boveybrawlers.AbsoluteCraft;

import com.boveybrawlers.AbsoluteCraft.commands.*;
import com.boveybrawlers.AbsoluteCraft.listeners.*;
import com.boveybrawlers.AbsoluteCraft.managers.AnnouncementManager;
import net.milkbowl.vault.chat.Chat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.boveybrawlers.AbsoluteCraft.managers.PlayerManager;
import com.boveybrawlers.AbsoluteCraft.utils.ErrorUtil;
import org.bukkit.scheduler.BukkitScheduler;

public class AbsoluteCraft extends JavaPlugin {

    public AbsoluteCraft plugin;
    public String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Absolute" + ChatColor.DARK_GREEN + "Craft" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
    
    public APIClient client;
    
    public PlayerManager players;
    public AnnouncementManager announcements;
    
    public ErrorUtil errors;
    
    public Chat chat = null;
    public boolean actionBarApi = false;

    public String serverName;

    public void onEnable() {
        plugin = this;

        this.saveDefaultConfig();
        this.serverName = this.plugin.getConfig().getString("server_name");

        // TODO: Setup Sentry logging

        this.registerCommands();
        this.registerListeners();
        
        this.client = new APIClient(this);
        this.players = new PlayerManager(this);
        this.announcements = new AnnouncementManager(this);
        
        this.errors = new ErrorUtil(this);
        
        this.setupChat();
        
        // Check for the ActionBarAPI plugin
        if(getServer().getPluginManager().isPluginEnabled("ActionBarAPI")) {
			this.actionBarApi = true;
		}

		// Run logic after the server has finished loading
        this.onServerLoad();
    }

    private void registerCommands() {
        getCommand("achievements").setExecutor(new AchievementsCommand(this));
        getCommand("feedback").setExecutor(new FeedbackCommand(this));
		getCommand("help").setExecutor(new HelpCommand(this));
		getCommand("profile").setExecutor(new ProfileCommand(this));
		getCommand("register").setExecutor(new RegisterCommand(this));
		getCommand("tokens").setExecutor(new TokenCommand(this));
        getCommand("vote").setExecutor(new VoteCommand(this));
    }

    private void registerListeners() {
    	getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);

        getServer().getPluginManager().registerEvents(new AchievementsInventoryClick(this), this);
        getServer().getPluginManager().registerEvents(new AppearanceInventoryClick(this), this);
		getServer().getPluginManager().registerEvents(new JoinItemsInteract(this), this);
        getServer().getPluginManager().registerEvents(new ProfileInventoryClick(this), this);
        getServer().getPluginManager().registerEvents(new WarpInventoryClick(this), this);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = this.getServer().getServicesManager().getRegistration(Chat.class);
        if(rsp != null) {
            this.chat = rsp.getProvider();
        }

        return this.chat != null;
    }

    private void onServerLoad() {
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                // Load announcements from API and schedule broadcasting
                announcements.load();
            }
        }, 0);
    }

}
