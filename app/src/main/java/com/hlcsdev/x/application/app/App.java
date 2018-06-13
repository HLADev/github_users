package com.hlcsdev.x.application.app;

import android.app.Application;

import com.hlcsdev.x.application.net.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static Api retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit = retro.create(Api.class);
    }

    public static Api getRetrofit() {
        return retrofit;
    }
}