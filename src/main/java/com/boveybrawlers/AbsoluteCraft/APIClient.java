package com.boveybrawlers.AbsoluteCraft;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Level;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.bukkit.plugin.Plugin;
import org.json.JSONObject;

public class APIClient {
	
    private Plugin plugin;

    private String baseUrl = "https://absolutecraft.co.uk/api";
    private String apiKey = null;

    public APIClient(AbsoluteCraft plugin) {
	    this.plugin = plugin;

        this.apiKey = plugin.getConfig().getString("api_key");
        if(this.apiKey == null) {
            this.plugin.onDisable();
        }

        String baseUrl = plugin.getConfig().getString("api_baseurl");
        if(baseUrl != null) {
            this.baseUrl = baseUrl;
        }
    }
    
    /**
     * Make an async get request
     *
     * @param url The endpoint
     * @param query The query string
     * @param callback The APICallback
     * @return Future promise
     */
    public Future<HttpResponse<JsonNode>> get(String url, Map<String, Object> query, Callback<JsonNode> callback) {
        this.plugin.getLogger().log(Level.INFO, "Making api call to " + this.buildUrl(url));
        return Unirest.get(this.buildUrl(url))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .header("Authorization", this.apiKey)
            .queryString(query)
            .asJsonAsync(callback);
    }

    /**
     * Make an async get request without a query string
     *
     * @param url The endpoint
     * @param callback The APICallback
     * @return Future promise
     */
    public Future<HttpResponse<JsonNode>> get(String url, Callback<JsonNode> callback) {
        return this.get(url, new HashMap<String, Object>(), callback);
    }

    /**
     * Make an async post request
     *
     * @param url The endpoint
     * @param body The post fields
     * @param callback The APICallback
     * @return Future promise
     */
    public Future<HttpResponse<JsonNode>> post(String url, JSONObject body, Callback<JsonNode> callback) {
        return Unirest.post(this.buildUrl(url))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .header("Authorization", this.apiKey)
            .body(body)
            .asJsonAsync(callback);
    }

    /**
     * Make an async post request where we don't care about the response
     *
     * @param url The endpoint
     * @param body The post fields
     * @return Future promise
     */
    public Future<HttpResponse<JsonNode>> post(String url, JSONObject body) {
        return Unirest.post(this.buildUrl(url))
            .header("Authorization", this.apiKey)
            .body(body)
            .asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> httpResponse) {}
                public void failed(UnirestException e) {}
                public void cancelled() {}
            });
    }

    /**
     * Make an async put request
     *
     * @param url The endpoint
     * @param body The put fields
     * @param callback The APICallback
     * @return Future promise
     */
    public Future<HttpResponse<JsonNode>> put(String url, JSONObject body, Callback<JsonNode> callback) {
        return Unirest.put(this.buildUrl(url))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .header("Authorization", this.apiKey)
            .body(body)
            .asJsonAsync(callback);
    }
    
    /**
     * Make an async put request where we don't care about the response
     *
     * @param url The endpoint
     * @param body The put fields
     * @return Future promise
     */
    public Future<HttpResponse<JsonNode>> put(String url, JSONObject body) {
        return Unirest.put(this.buildUrl(url))
            .header("Authorization", this.apiKey)
            .body(body)
            .asJsonAsync(new Callback<JsonNode>() {
                public void completed(HttpResponse<JsonNode> httpResponse) {}
                public void failed(UnirestException e) {}
                public void cancelled() {}
            });
    }
    
    private String buildUrl(String url) {
        if(url.length() > 0 && url.charAt(0) != '/') {
            url = "/" + url;
        }

        return this.baseUrl + url;
    }

}
