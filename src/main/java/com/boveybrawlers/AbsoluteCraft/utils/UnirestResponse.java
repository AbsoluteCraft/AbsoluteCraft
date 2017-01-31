package com.boveybrawlers.AbsoluteCraft.utils;

import com.mashape.unirest.http.HttpResponse;

public interface UnirestResponse<T> {

    public void run(HttpResponse<T> response);

}
