package ru.tvs.app;

import android.content.Intent;
import android.support.v7.app.AppCompatDelegate;

public class App extends android.app.Application {

    public static App app;
    public static boolean activated = false;


    public App() {
        app = this;
    }

    @Override
    public void onCreate() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Injector.setAppContext(getApplicationContext());

        super.onCreate();
    }


    public void activate() {

        if (activated)
            return;
        activated = true;

        startService(new Intent(this, FonService.class));

    }


    public void finish() {

        if (!activated)
            return;

        stopService(new Intent(this, FonService.class));

        activated = false;

    }


}