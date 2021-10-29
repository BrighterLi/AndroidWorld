package com.xiaoming.a004performance;

import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;
import com.xiaoming.a004performance.block.blockcanary.AppBlockCanaryContext;

public class PerfromanceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //在主进程初始化
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }
}
