package com.xiaoming.a004performanceblock;

import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;
import com.xiaoming.a004performanceblock.blockcanary.AppBlockCanaryContext;

public class BlockApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //在主进程初始化
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }
}
