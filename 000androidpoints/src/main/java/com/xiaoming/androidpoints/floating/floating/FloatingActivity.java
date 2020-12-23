package com.xiaoming.androidpoints.floating.floating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.floating.floating.service.FloatingService;
import com.xiaoming.androidpoints.floating.floating.service.FloatingService2;
import com.xiaoming.androidpoints.floating.timerfloating.manager.TimeFloatingWindowManager;

//Android实现悬浮窗:https://www.open-open.com/lib/view/open1450668450620.html
//https://github.com/zimoguo/FloatWindo
public class FloatingActivity extends Activity {
    private Button mBtOPenFloatingService;
    private Button mBtCloseFloatingService;
    private Button mBtOPenFloatingService2;
    private Button mBtCloseFloatingService2;
    private Button mBtOpenTimeFloating;
    private Button mBtCloseTimeFloating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);

        initView();
    }

    private void initView() {
        mBtOPenFloatingService = findViewById(R.id.bt_open_floating_service);
        mBtCloseFloatingService = findViewById(R.id.bt_close_floating_service);
        mBtOPenFloatingService2 = findViewById(R.id.bt_open_floating_service2);
        mBtCloseFloatingService2 = findViewById(R.id.bt_close_floating_service2);
        mBtOpenTimeFloating = findViewById(R.id.bt_open_time_floating);
        mBtCloseTimeFloating = findViewById(R.id.bt_close_time_floating);

        mBtOPenFloatingService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(FloatingActivity.this, FloatingService.class);
                startService(open);
            }
        });
        mBtCloseFloatingService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent close = new Intent(FloatingActivity.this, FloatingService.class);
                stopService(close);
            }
        });

        mBtOPenFloatingService2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(FloatingActivity.this, FloatingService2.class);
                startService(open);
            }
        });
        mBtCloseFloatingService2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent close = new Intent(FloatingActivity.this, FloatingService2.class);
                stopService(close);
            }
        });

        mBtOpenTimeFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeFloatingWindowManager.createFloatWindow(getApplicationContext());
            }
        });

        mBtCloseTimeFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeFloatingWindowManager.removeFloatWindow(getApplicationContext());
            }
        });
    }

}
