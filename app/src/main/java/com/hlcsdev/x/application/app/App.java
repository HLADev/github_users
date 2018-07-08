package com.hlcsdev.x.application.app;

import android.app.Application;

import com.hlcsdev.x.application.di.AppComponent;
import com.hlcsdev.x.application.di.DaggerAppComponent;


public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().build();

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}