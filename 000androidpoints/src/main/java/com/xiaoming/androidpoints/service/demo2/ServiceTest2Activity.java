package com.xiaoming.androidpoints.service.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.service.LocalService;

//https://www.cnblogs.com/sevenyuan/archive/2013/03/22/2975682.html
public class ServiceTest2Activity extends Activity {

    private Button mStartSeriveBt;
    private Button mStopSeriveBt;
    private Button mBindSeriveBt;
    private Button mUnBindSeriveBt;
    private IService iService=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service_test);
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
            intent.setClass(getApplicationContext(), LocalService2.class);
            switch (v.getId()) {
                case R.id.mStartServiceBt:
                    startService(intent);
                    break;
                case R.id.mStopServiceBt:
                    stopService(intent);
                    break;
                case R.id.mBindServiceBt:
                    bindservice();
                    break;
                case R.id.mUnBindServiceBt:
                    unbindService(connection);
                    break;
            }
        }
    }

    //绑定服务
    private void bindservice() {
        // TODO Auto-generated method stub
        //这里用到getApplicationContext是为了提升应用等级,避免出现“android.app.ServiceConnectionLeaked”这样的错误
        Intent intent=new Intent(getApplicationContext(), LocalService.class);
        this.bindService(intent, connection, BIND_AUTO_CREATE);
    }

    //连接服务接口
    ServiceConnection connection=new ServiceConnection() {

        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            iService=null;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            //获取连接的服务对象
            iService=(IService) service;
            //调用服务，获取服务中的接口方法
            if(iService!=null)
                Log.d("MyService", "bright8#iService bindService getCurrentTime"+iService.getCurrentTime());
        }
    };

    protected void onDestroy() {
        // TODO Auto-generated method stub
        Log.d("MyService", "bright8#localServiceActivity onDestroy");
        super.onDestroy();
    }
}
