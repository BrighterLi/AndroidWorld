package com.xiaoming.fuctionchoosephotoandcrop;

import android.app.Application;
import android.os.StrictMode;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        super.onCreate();
    }
}
