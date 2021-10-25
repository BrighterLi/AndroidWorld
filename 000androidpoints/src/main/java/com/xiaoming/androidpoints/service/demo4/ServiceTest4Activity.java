package com.xiaoming.androidpoints.service.demo4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.xiaoming.androidpoints.R;

public class ServiceTest4Activity extends Activity {
    private TService services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test4);
        Intent intent = new Intent(this, TService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TService.TBinder binder = (TService.TBinder) service;
            services = binder.getService();
            Log.e("servcice", "bright8#onServiceConnected");
            if(services != null) {
                int num = services.getNumber();
                Log.e("servcice", "bright8#onServiceConnected#num: " + num);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("servcice", "bright8#onServiceDisconnected");
        }


    };


}
