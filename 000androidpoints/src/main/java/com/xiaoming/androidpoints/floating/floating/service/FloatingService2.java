package com.xiaoming.androidpoints.floating.floating.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;


import com.xiaoming.androidpoints.floating.floating.manager.FloatingWindowManager;
import com.xiaoming.androidpoints.floating.floating.util.TaskUtil;

import java.util.Timer;
import java.util.TimerTask;

//在桌面和你自己的app中显示
public class FloatingService2 extends Service {
    public FloatingService2() {
    }

    private Handler handler = new Handler();

    private Timer timer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new RefreshTask(), 0, 500);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    class RefreshTask extends TimerTask {
        @Override
        public void run() {

            if ((TaskUtil.isHome(FloatingService2.this) || !TaskUtil.isApplicationBroughtToBackground(FloatingService2.this))
                    && !FloatingWindowManager.isWindowShowing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        FloatingWindowManager.createFloatWindow(getApplicationContext());
                    }
                });
            } else if (!TaskUtil.isHome(FloatingService2.this)
                    && TaskUtil.isApplicationBroughtToBackground(FloatingService2.this)
                    && FloatingWindowManager.isWindowShowing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        FloatingWindowManager.removeFloatWindow(getApplicationContext());
                    }
                });

            }

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
        if (FloatingWindowManager.isWindowShowing()) {
            FloatingWindowManager.removeFloatWindow(getApplicationContext());
        }
    }
}
