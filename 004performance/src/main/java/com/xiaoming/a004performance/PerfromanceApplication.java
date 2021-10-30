package com.xiaoming.a004performance;

import android.app.Application;
import android.os.StrictMode;

import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.xiaoming.a004performance.block.blockcanary.AppBlockCanaryContext;

public class PerfromanceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //在主进程初始化
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        LeakCanary.install(this);
        startStrictMode();
    }

    //严苛模式
    private void startStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                //.penaltyDialog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
