package com.xiaoming.androidpoints.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xiaoming.androidpoints.R;

//Android中Service的一个Demo例子: https://blog.csdn.net/fansunion/article/details/49837455
public class ServiceTestActivity extends AppCompatActivity {
    private static final String TAG = "ServiceTestActivity";
    private Button mStartSeriveBt;
    private Button mStopSeriveBt;
    private Button mBindSeriveBt;
    private Button mUnBindSeriveBt;
    private LocalService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        mStartSeriveBt = findViewById(R.id.mStartServiceBt);
        mStopSeriveBt = findViewById(R.id.mStopServiceBt);
        mBindSeriveBt = findViewById(R.id.mBindServiceBt);
        mUnBindSeriveBt = findViewById(R.id.mUnBindServiceBt);

        mStartSeriveBt.setOnClickListener(new MyOnClickListener());
        mStopSeriveBt.setOnClickListener(new MyOnClickListener());
        mBindSeriveBt.setOnClickListener(new MyOnClickListener());
        mUnBindSeriveBt.setOnClickListener(new MyOnClickListener());
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(ServiceTestActivity.this, LocalService.class);
            switch (v.getId()) {
                case R.id.mStartServiceBt:
                    startService(intent);
                    toast("startService");
                    break;
                case R.id.mStopServiceBt:
                    stopService(intent);
                    toast("stopService");
                    break;
                case R.id.mBindServiceBt:
                    bindService(intent, connection, Service.BIND_AUTO_CREATE);
                    toast("bindService");
                    break;
                case R.id.mUnBindServiceBt:
                    unbindService(connection);
                    toast("unBindService");
                    break;
            }
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "bright8#ServiceConnection#onServiceConnected#连接成功");
            myService = ((LocalService.LocalBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "bright8#ServiceConnection#onServiceDisconnected");

        }
    };

    private void toast(final String tip) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), tip, Toast.LENGTH_LONG).show();
            }
        });
    }
}
