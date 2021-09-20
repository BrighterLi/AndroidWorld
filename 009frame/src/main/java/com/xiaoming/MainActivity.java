package com.xiaoming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.aop.aspectj.activity_life_demo.AspectJActivity;
import com.xiaoming.aop.aspectj.click_demo.AspectJActivity2;
import com.xiaoming.databinding.demo2.DataBindingActivity2;
import com.xiaoming.eventbus.demo.EventBusFirst2Activity;
import com.xiaoming.framearouter.R;
import com.xiaoming.lifecycle.LifeCycleActivity;
import com.xiaoming.mvx.mvvm.demo1.view.MvvmActivity;
import com.xiaoming.router.FirstActivity;

//在支持路由的页面上添加注解
public class MainActivity extends Activity implements View.OnClickListener{
    private Button mMvx;
    private Button mBtRouter;
    private Button mBtEventBus;
    private Button mBtLifeCycle;
    private Button mBtDataBinding;
    private Button mBtAop;

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
        mBtLifeCycle = findViewById(R.id.bt_lifecycle);
        mBtLifeCycle.setOnClickListener(this);
        mBtDataBinding = findViewById(R.id.bt_databinding);
        mBtDataBinding.setOnClickListener(this);
        mBtAop = findViewById(R.id.bt_aop);
        mBtAop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_mvx:
                //startActivity(new Intent(MainActivity.this, JinziqiActivity.class));
                //startActivity(new Intent(MainActivity.this, MvcJinziqiActivityDemo.class));
                //startActivity(new Intent(MainActivity.this, MvcJinziqiActivityDemo.class));

                //startActivity(new Intent(MainActivity.this, MvcActivity.class));
                //startActivity(new Intent(MainActivity.this, MvpActivity.class));
                startActivity(new Intent(MainActivity.this, MvvmActivity.class));
                break;
            case R.id.bt_router:
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
                break;
            case R.id.bt_event_bus:
                startActivity(new Intent(MainActivity.this, EventBusFirst2Activity.class));
                break;
            case R.id.bt_lifecycle:
                startActivity(new Intent(MainActivity.this, LifeCycleActivity.class));
                break;
            case R.id.bt_databinding:
                //startActivity(new Intent(MainActivity.this, DataBindingActivity.class));
                startActivity(new Intent(MainActivity.this, DataBindingActivity2.class));
                break;
            case R.id.bt_aop:
                //startActivity(new Intent(MainActivity.this, DataBindingActivity.class));
                //startActivity(new Intent(MainActivity.this, AspectJActivity.class));
                startActivity(new Intent(MainActivity.this, AspectJActivity2.class));
                break;
        }
    }
}
