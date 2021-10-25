package com.xiaoming.androidpoints.service.demo4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

import androidx.annotation.Nullable;

//Service通信以及跨进程通信: https://www.jianshu.com/p/2c666751f833
public class TService extends Service {

    private IBinder binder = new TBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class TBinder extends Binder {
        TService getService() {
            return TService.this;
        }
    }

    private Random util = new Random();
    public int getNumber() {
        return util.nextInt(100);
    }
}
