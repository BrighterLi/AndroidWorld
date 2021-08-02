package com.xiaoming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.eventbus.demo.EventBusFirstActivity;
import com.xiaoming.framearouter.R;
import com.xiaoming.mvx.xiangxue.mvc.controller.MvcJinziqiActivityDemo;
import com.xiaoming.router.FirstActivity;

//在支持路由的页面上添加注解
public class MainActivity extends Activity implements View.OnClickListener{
    private Button mMvx;
    private Button mBtRouter;
    private Button mBtEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtRouter = findViewById(R.id.bt_router);
        mBtRouter.setOnClickListener(this);
        mBtEventBus = findViewById(R.id.bt_event_bus);
        mBtEventBus.setOnClickListener(this);
        mMvx = findViewById(R.id.bt_mvx);
        mMvx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_mvx:
                //startActivity(new Intent(MainActivity.this, JinziqiActivity.class));
                //startActivity(new Intent(MainActivity.this, MvcJinziqiActivityDemo.class));
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MvcJinziqiActivityDemo.class);
                startActivity(intent);
                break;
            case R.id.bt_router:
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
                break;
            case R.id.bt_event_bus:
                startActivity(new Intent(MainActivity.this, EventBusFirstActivity.class));
                break;
        }
    }
}
