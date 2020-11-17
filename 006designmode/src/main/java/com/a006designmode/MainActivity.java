package com.a006designmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a006designmode.behaviormode.observermode.ObserverActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBtObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initView() {
        //创建者模式
        //构建模式
        //行为模式
        mBtObserver = findViewById(R.id.bt_observer);
    }

    private void initEvent() {
        mBtObserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ObserverActivity.class));
            }
        });
    }
}
