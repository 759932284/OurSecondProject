package com.lanou.yindongge.music.pineapple.net;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 17/2/24.
 */

public class OkHttpManager {
    private static OkHttpManager instance;
    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (OkHttpManager.class) {
                if (instance == null) {
                    instance = new OkHttpManager();
                }
            }

        }
        return instance;
    }

    private OkHttpClient client;
    private Handler handler;
    private OkHttpManager() {
         client = new OkHttpClient.Builder()
                 .build();
        handler = new Handler();
    }

    private void _startGetRequest(final String url, final int requestCode, final OnNetResultListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                creatCall(request, requestCode, listener);

            }
        }).start();
    }

    private void creatCall(Request request, final int requestCode, final OnNetResultListener listener) {

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                final String errMsg = e.getMessage();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailureListener(errMsg);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccessListener(result, requestCode);
                    }
                });
            }
        });
    }

    public void startGetRequest(String url, int requestCode, OnNetResultListener listener) {
        _startGetRequest(url, requestCode, listener);
    }

}
