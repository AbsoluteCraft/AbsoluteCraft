package com.boveybrawlers.AbsoluteCraft;

import net.milkbowl.vault.chat.Chat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.boveybrawlers.AbsoluteCraft.commands.HelpCommand;
import com.boveybrawlers.AbsoluteCraft.commands.ProfileCommand;
import com.boveybrawlers.AbsoluteCraft.commands.RegisterCommand;
import com.boveybrawlers.AbsoluteCraft.commands.TokenCommand;
import com.boveybrawlers.AbsoluteCraft.listeners.JoinItemsInteract;
import com.boveybrawlers.AbsoluteCraft.listeners.JoinItemsMove;
import com.boveybrawlers.AbsoluteCraft.listeners.PlayerJoin;
import com.boveybrawlers.AbsoluteCraft.managers.PlayerManager;
import com.boveybrawlers.AbsoluteCraft.utils.ErrorUtil;

public class AbsoluteCraft extends JavaPlugin {

    public AbsoluteCraft plugin;
    public String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Absolute" + ChatColor.DARK_GREEN + "Craft" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
    
    public APIClient client;
    
    public PlayerManager players;
    
    public ErrorUtil errors;
    
    public Chat chat = null;
    public boolean actionBarApi = false;

    public void onEnable() {
        plugin = this;

        this.saveDefaultConfig();

        this.registerCommands();
        this.registerListeners();
        
        this.client = new APIClient(this);
        this.players = new PlayerManager(this);
        
        this.errors = new ErrorUtil(this);
        
        this.setupChat();
        
        // Check for the ActionBarAPI plugin
        if(getServer().getPluginManager().isPluginEnabled("ActionBarAPI")) {
			this.actionBarApi = true;
		}
    }

    public void registerCommands() {
		getCommand("help").setExecutor(new HelpCommand(this));
		getCommand("profile").setExecutor(new ProfileCommand(this));
		getCommand("register").setExecutor(new RegisterCommand(this));
		getCommand("tokens").setExecutor(new TokenCommand(this));
    }

    public void registerListeners() {
    	getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
    	
    	getServer().getPluginManager().registerEvents(new JoinItemsMove(this), this);
		getServer().getPluginManager().registerEvents(new JoinItemsInteract(this), this);
    }
    
    public boolean setupChat() {
    	RegisteredServiceProvider<Chat> chatProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            this.chat = chatProvider.getProvider();
        }

        return (this.chat != null);
    }

}
