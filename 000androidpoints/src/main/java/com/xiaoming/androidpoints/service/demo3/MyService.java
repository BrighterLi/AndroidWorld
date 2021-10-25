package com.xiaoming.androidpoints.service.demo3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

//https://www.cnblogs.com/sevenyuan/archive/2013/03/22/2975682.html

public class MyService extends Service{
    //log标记
    private static final String TAG="MyService";


    @Override
    public IBinder onBind(Intent intent) {
        return Abinder;
    }

    Binder Abinder = new IMyAidlInterface.Stub() {
        @Override
        public String getNotice() throws RemoteException {
            return "a notice from service";
        }
    };
}
