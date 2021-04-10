package com.tencent.tecentim.messagehelper;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
