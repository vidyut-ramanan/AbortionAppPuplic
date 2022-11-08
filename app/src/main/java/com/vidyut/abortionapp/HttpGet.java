package com.vidyut.abortionapp;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpGet {
    boolean requestFailed;

    boolean requestDone;

    String output;

    String constructedUrl;

    public HttpGet(String constructedUrl) {

        this.constructedUrl = constructedUrl;
    }

    public HttpGet sendGet() throws IllegalStateException {
        if (output != null) {
            throw new IllegalStateException("get request already sent");
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);

        executor.submit(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(constructedUrl).addHeader("token", "wj/25q9xK++rLIOf").get()
                    .build();
            Response response;

            try {
                response = client.newCall(request).execute();
                output = response.body().string();
                requestDone = true;
            } catch (IOException e) {
                requestDone = true;
                requestFailed = true;
            }


        });
        return this;
    }

    public String getResponse() {
        return output;
    }

}
