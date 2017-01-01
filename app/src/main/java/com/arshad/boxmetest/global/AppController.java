package com.arshad.boxmetest.global;

import android.app.Application;

/**
 * Created by Arshad on 01-12-2017.
 */
public class AppController extends Application {

    private static AppController instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "mont-reg.otf");
        instance = this;
    }

    public static synchronized AppController getInstance() {
        return instance;
    }
}
