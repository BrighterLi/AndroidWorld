package com.xiaoming.androidknowledgepoints.floating.floating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.xiaoming.androidknowledgepoints.R;
import com.xiaoming.androidknowledgepoints.floating.floating.manager.FloatingWindowManager;
import com.xiaoming.androidknowledgepoints.floating.floating.service.FloatingService;
import com.xiaoming.androidknowledgepoints.floating.floating.service.FloatingService2;

//Android实现悬浮窗:https://www.open-open.com/lib/view/open1450668450620.html
//https://github.com/zimoguo/FloatWindo
public class FloatingActivity extends Activity {
    private Button mBtOPenFloatingService;
    private Button mBtCloseFloatingService;
    private Button mBtOPenFloatingService2;
    private Button mBtCloseFloatingService2;
    private Button mBtOpenFloating;

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
        mBtOpenFloating = findViewById(R.id.bt_open_floating);

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

        mBtOpenFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingWindowManager.createFloatWindow(FloatingActivity.this);
            }
        });
    }

}
