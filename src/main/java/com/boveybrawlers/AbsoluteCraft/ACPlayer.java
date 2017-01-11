package com.boveybrawlers.AbsoluteCraft;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.boveybrawlers.AbsoluteCraft.utils.APILoadCallback;
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
    private boolean isLoaded = false;
	
	private Integer tokens;
	
	public ACPlayer(AbsoluteCraft plugin) {
		this.plugin = plugin;
	}

	public boolean isLoaded() {
	    return this.isLoaded;
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
				JSONObject resp = response.getBody().getObject();
				
				// TODO: Store more attributes in this model
				tokens = resp.getInt("tokens");

                isLoaded = true;
				
				callback.run(resp);
			}
		});
	}
	
	public Object getPlayer() {
		if(this.isOnline()) {
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

	public String getName() {
	    if(this.isOnline()) {
	        return this.p.getDisplayName();
        }

        return this.offlineP.getName();
    }
	
	public void setPlayer(OfflinePlayer player) {
		if(player.isOnline()) {
			this.p = (Player) player;
		} else {
			this.offlineP = player;
		}
	}

	public void getTokens(final APILoadCallback callback) {
	    if(this.isLoaded) {
	        callback.run(this.tokens);
        } else {
            this.load(new APICallback() {
                public void run(JSONObject response) {
                    callback.run(tokens);
                }
            });
        }
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
	}

	public void addTokens(int amount, boolean notify) {
	    this.addTokens(amount);

        if(notify) {
            this.notify(ChatColor.GREEN + "+" + amount + " Tokens!");
        }
    }

	public void addTokens(int amount, ACPlayer adder) {
	    this.addTokens(amount, true);

        this.sendMessage(ChatColor.GOLD + "Tokens were added to you by " + adder.getName());
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
		final ProfileItems profile = new ProfileItems(this.plugin);
		final Inventory inv = profile.getInventory(this.p);
		profile.setRegistered(this.isRegistered());
		this.getTokens(new APILoadCallback() {
			public void run(Object response) {
			    int tokens = (Integer) response;
                profile.setTokens(tokens);
				p.openInventory(inv);
			}
		});
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
		if(this.isOnline()) {
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
		if(this.plugin.actionBarApi && this.isOnline()) {
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
		if(this.isOnline()) {
			this.p.sendMessage(message);
		}
	}
	
	/**
	 * Send a prefixed message to the Player
	 * 
	 * @param message
	 */
	public void sendPrefixedMessage(String message) {
		if(this.isOnline()) {
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
		if(this.isOnline()) {
			TextComponent command = new TextComponent(name);
			command.setColor(color);
			command.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd));
			command.addExtra(text);
			this.p.spigot().sendMessage(command);
		}
	}

}
