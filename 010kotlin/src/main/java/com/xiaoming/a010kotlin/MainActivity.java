package com.xiaoming.a010kotlin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xiaoming.a010kotlin.androidpoints.activitylifecycle.AActivity;
import com.xiaoming.a010kotlin.androidpoints.flow.FlowActivity;
import com.xiaoming.a010kotlin.androidpoints.flow.flowandroom.demo.FlowActivity2;
import com.xiaoming.a010kotlin.androidpoints.frame.eventbus.EventBusActivity;
import com.xiaoming.a010kotlin.androidpoints.jetpack.viewbinding.demo.ViewBindingActivity;
import com.xiaoming.a010kotlin.androidpoints.jetpack.viewmodel.demo1.ViewModelTestActivity;
import com.xiaoming.a010kotlin.project.ProjectActivity;
import com.xiaoming.a010kotlin.xiangxuedemo.modules.home.HomeActivity;
import com.xiaoming.net.NetDemoActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBtXiangxue;
    private Button mBtNet;
    private Button mBtActivityLifecycle;
    private Button mBtProject;
    private Button mBtViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getKotlinType();

        initView();

    }

    private void initView() {
        mBtXiangxue = findViewById(R.id.bt_xiangxue);
        mBtNet = findViewById(R.id.bt_net);
        mBtActivityLifecycle = findViewById(R.id.bt_activity_lifecycle);
        mBtProject = findViewById(R.id.bt_project);
        mBtViewModel = findViewById(R.id.bt_view_model);
        mBtXiangxue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                //startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        mBtNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NetDemoActivity.class));
            }
        });
        mBtActivityLifecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AActivity.class));
            }
        });
        mBtProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProjectActivity.class));
            }
        });
        mBtViewModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ViewModel
                // startActivity(new Intent(MainActivity.this, ViewModelTestActivity.class));
                //ViewBinding
                // startActivity(new Intent(MainActivity.this, ViewBindingActivity.class));
                //EventBus
                //startActivity(new Intent(MainActivity.this, EventBusActivity.class));
                //startActivity(new Intent(MainActivity.this, FlowActivity.class));
                startActivity(new Intent(MainActivity.this, FlowActivity2.class));
            }
        });
    }

    //调用kotlin
    public String getKotlinType(){
        Log.i("MainActivity", "bright8#getKotlinType:type:" + new KotlinDemo().getType());
        return  new KotlinDemo().getType();
    }
}
