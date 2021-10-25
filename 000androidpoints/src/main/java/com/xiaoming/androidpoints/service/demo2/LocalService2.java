package com.xiaoming.androidpoints.service.demo2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalService2 extends Service {
    private static final String TAG = "LocalService2";
    //获取绑定接口
    private MyBind myBind=new MyBind();

    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, "bright8#localService onBind");
        return myBind;
    }

    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d(TAG, "bright8#localService onCreate");
        super.onCreate();
    }

    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d(TAG, "bright8#localService onDestroy");
        super.onDestroy();
    }

    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, "bright8#localService onStart");
        super.onStart(intent, startId);
    }

    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, "bright8#localService onUnbind");
        return super.onUnbind(intent);
    }


    //本地服务中的绑定
    public class MyBind extends Binder implements IService {

        @Override
        public long getCurrentTime() {
            return System.currentTimeMillis();
        }
    }
}
