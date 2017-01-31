package com.boveybrawlers.AbsoluteCraft.utils;

import com.boveybrawlers.AbsoluteCraft.AbsoluteCraft;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.logging.Level;

public class UnirestCallback<T> implements Callback<T> {

    private AbsoluteCraft plugin;
    private UnirestResponse callback;

    public UnirestCallback(UnirestResponse<T> callback) {
        this.plugin = plugin;
        this.callback = callback;
    }

    public void cancelled() {
        // Do nothing
    }

    public void failed(UnirestException e) {
        this.plugin.getLogger().log(Level.SEVERE, "Failed API call", e);
    }

    public void completed(HttpResponse<T> response) {
        callback.run(response);
    }

}
