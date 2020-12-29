package com.a006designmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a006designmode.behaviormode.observermode.ObserverActivity;
import com.a006designmode.behaviormode.observermode2.Observer2Activity;
import com.a006designmode.othermode.productandconsume.ProducterAndConsumer;

public class MainActivity extends AppCompatActivity {
    private Button mBtObserver;
    private Button mBtObserver2;
    private Button mBtProductConsume;

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
        mBtObserver = findViewById(R.id.bt_observer); //观察者模式
        mBtObserver2 = findViewById(R.id.bt_observer2); //观察者模式
        //其他模式
        mBtProductConsume = findViewById(R.id.bt_product_consume); //消费者生产者模型
    }

    private void initEvent() {
        mBtObserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ObserverActivity.class));
            }
        });
        mBtObserver2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Observer2Activity.class));
            }
        });

        mBtProductConsume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProducterAndConsumer.main();
            }
        });
    }
}
