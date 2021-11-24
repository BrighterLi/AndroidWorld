package com.xiaoming.androidpoints.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {
    private static final String TAG = "LocalService";
    private final IBinder myBinder = new LocalBinder();


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "bright8#onBind");
        return myBinder;
    }

    // 调用startService方法或者bindService方法时创建Service时（当前Service未创建）调用该方法
    @Override
    public void onCreate() {
        Log.i(TAG, "bright8#onCreate");
    }

    // 调用startService方法启动Service时调用该方法

    //onStart方法是在Android2.0之前的平台使用的.在2.0及其之后，则需重写onStartCommand方法，同时，旧的onStart方法则不会再被直接调用（外部调用onStartCommand，
    // 而onStartCommand里会再调用 onStart。在2.0之后，推荐覆盖onStartCommand方法，而为了向前兼容，在onStartCommand依然会调用onStart方法。
    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "bright8#onStart");
    }

    // Service创建并启动后在调用stopService方法或unbindService方法时调用该方法
    @Override
    public void onDestroy() {
        Log.i(TAG, "bright8#onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "bright8#onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //提供给客户端访问
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }
}