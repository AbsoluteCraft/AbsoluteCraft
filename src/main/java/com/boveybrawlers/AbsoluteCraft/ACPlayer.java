package com.boveybrawlers.AbsoluteCraft;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.json.JSONObject;

import com.boveybrawlers.AbsoluteCraft.utils.APICallback;
import com.boveybrawlers.AbsoluteCraft.utils.ProfileItems;
import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ACPlayer {
	
	public AbsoluteCraft plugin;
	
	public Player p = null;
	public OfflinePlayer offlineP = null;
	
	private Integer tokens;
	
	public ACPlayer(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}
	
	public void load(final APICallback callback) {
	    Map<String, Object> query = new HashMap<String, Object>();
        query.put("uuid", this.p.getUniqueId().toString());

		this.plugin.client.get("/player", query, new Callback<JsonNode>() {
			public void cancelled() {
			    plugin.getLogger().log(Level.SEVERE, "API call cancelled");
            }
			public void failed(UnirestException e) {
				plugin.getLogger().log(Level.SEVERE, "Failed to load /player", e);
			}

			public void completed(HttpResponse<JsonNode> response) {
				plugin.getLogger().log(Level.INFO, "Got " + response.getStatus() + " response", response);
				JSONObject resp = response.getBody().getObject();
                plugin.getLogger().log(Level.INFO, "Got OK response for " + resp.getString("username") + "!");
				
				// TODO: Store more attributes in this model
				tokens = resp.getInt("tokens");
				
				callback.run(resp);
			}
		});
	}
	
	public Object getPlayer() {
		if(this.p != null) {
			return this.p;
		}
		
		return this.offlineP;
	}

	public boolean isOnline() {
		if(this.p != null) {
			return true;
		}

		return false;
	}
	
	public void setPlayer(OfflinePlayer player) {
		if(player.isOnline()) {
			this.p = (Player) player;
		} else {
			this.offlineP = player;
		}
	}
	
	public int getTokens() {
		return this.tokens;
	}
	
	public void addTokens(int amount) {
		this.tokens += amount;
		
		JSONObject body = new JSONObject();
		body.put("uuid", this.p.getUniqueId());
		body.put("amount", amount);
		
		this.plugin.client.put("/player/tokens/add", body, new Callback<JsonNode>() {
			public void cancelled() {}
			public void failed(UnirestException e) {
				plugin.getLogger().log(Level.SEVERE, "Failed /player/tokens/add", e);
			}
			
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject resp = response.getBody().getObject();
				tokens = resp.getInt("amount");
			}
		});
		
		this.notify(ChatColor.GREEN + "" + amount + ChatColor.GOLD + " tokens were added!");
	}
	
	public void removeTokens(int amount) {
		this.tokens -= amount;
		
		JSONObject body = new JSONObject();
		body.put("uuid", this.p.getUniqueId());
		body.put("amount", amount);
		
		this.plugin.client.put("/player/tokens/remove", body, new Callback<JsonNode>() {
			public void cancelled() {}
			public void failed(UnirestException e) {
				plugin.getLogger().log(Level.SEVERE, "Failed /player/tokens/remove", e);
			}
			
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject resp = response.getBody().getObject();
				tokens = resp.getInt("amount");
			}
		});
		
		this.notify(ChatColor.RED + "" + amount + ChatColor.GOLD + " tokens were removed!");
	}
	
	public void openProfileGUI() {
		ProfileItems profile = new ProfileItems();
		Inventory inv = profile.getInventory(this.p);
		profile.setTokens(this.tokens);
		profile.setRegistered(this.isRegistered());
		this.p.openInventory(inv);
	}
	
	public boolean isRegistered() {
		return false;
	}
	
	public String getEmail() {
		if(this.isRegistered()) {
			return "example@mc-ac.com";
		}
		
		return null;
	}

	public boolean hasPermission(String permission) {
		if(this.p != null) {
			return this.p.hasPermission(permission);
		}

		return this.offlineP.getPlayer().hasPermission(permission);
	}
	
	/**
	 * Send an ActionBar notification to the Player
	 * Falls back to sending a text message if ActionBarAPI is not available
	 * 
	 * @param message
	 */
	public void notify(String message) {
		if(this.plugin.actionBarApi && this.p != null) {
			ActionBarAPI.sendActionBar(this.p, message);
		} else {
			this.sendMessage(message);
		}
	}
	
	/**
	 * Send a basic message to the Player
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		if(this.p != null) {
			this.p.sendMessage(message);
		}
	}
	
	/**
	 * Send a prefixed message to the Player
	 * 
	 * @param message
	 */
	public void sendPrefixedMessage(String message) {
		if(this.p != null) {
			this.p.sendMessage(this.plugin.prefix + message);
		}
	}
	
	/**
	 * Send a clickable command to the Player
	 * 
	 * @param name
	 * @param color
	 * @param cmd
	 * @param text
	 */
	public void sendClickableCommand(String name, net.md_5.bungee.api.ChatColor color, String cmd, String text) {
		if(this.p != null) {
			TextComponent command = new TextComponent(name);
			command.setColor(color);
			command.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd));
			command.addExtra(text);
			this.p.spigot().sendMessage(command);
		}
	}

}
