package com.xiaoming.androidpoints.service.demo3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.xiaoming.androidpoints.service.demo2.IService;

//https://www.cnblogs.com/sevenyuan/archive/2013/03/22/2975682.html


/*
public class MyService extends Service{
    //log标记
    private static final String TAG="MyService";

    private String str;

    private IService.Stub bind=new Stub() {

        public long getCurrentTime() throws RemoteException {
            // TODO Auto-generated method stub
            return System.currentTimeMillis();
        }

        public void setValue(String from, int value) throws RemoteException {
            // TODO Auto-generated method stub
            str="value from-------"+from+" and value is-------"+value;
            Log.d(TAG, "ServiceServer setValue from-------"+from+" value is-------"+value);
        }

        public String getValue() throws RemoteException {
            // TODO Auto-generated method stub
            return str;
        }
    };

    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, "ServiceServer onBind");
        return bind;
    }

    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d(TAG, "ServiceServer onCreate");
        super.onCreate();
    }

    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d(TAG, "ServiceServer onDestroy");
        super.onDestroy();
    }

    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, "ServiceServer onStart");
        super.onStart(intent, startId);
    }

    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, "ServiceServer onUnbind");
        return super.onUnbind(intent);
    }

}
*/
