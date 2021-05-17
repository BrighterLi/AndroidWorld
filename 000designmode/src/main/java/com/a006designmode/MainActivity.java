package com.a006designmode;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a006designmode.behaviormode.observermode.ObserverActivity;
import com.a006designmode.behaviormode.observermode2.Observer2Activity;
import com.a006designmode.creationmode.buildermode.BuilderActivity;
import com.a006designmode.othermode.productandconsume.ProducterAndConsumer;
import com.a006designmode.structuralmode.adaptermode.AdapterModeTest;
import com.a006designmode.structuralmode.proxymode.ProxyTest;
import com.a006designmode.structuralmode.proxymode.staticmode.Proxy;

public class MainActivity extends AppCompatActivity {
    //创建型
    private Button mBtSingletonMode;
    private Button mBtBuilderMode;
    //结构型
    private Button mBtAdapterMode;
    private Button mBtStaticProxyMode;
    //行为型
    private Button mBtObserver;
    private Button mBtObserver2;

    //其他
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
        mBtSingletonMode = findViewById(R.id.bt_singleton_mode);
        mBtBuilderMode = findViewById(R.id.bt_builder_mode);
        //结构型模式
        mBtAdapterMode = findViewById(R.id.bt_adapter_mode);
        mBtStaticProxyMode = findViewById(R.id.bt_static_proxy_mode);
        //行为型模式
        mBtObserver = findViewById(R.id.bt_observer); //观察者模式
        mBtObserver2 = findViewById(R.id.bt_observer2); //观察者模式
        //其他模式
        mBtProductConsume = findViewById(R.id.bt_product_consume); //消费者生产者模型
    }

    private void initEvent() {
        //创建型
        mBtSingletonMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //单例模式
            }
        });

        mBtBuilderMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创造者模式
                startActivity(new Intent(MainActivity.this, BuilderActivity.class));
                //startActivity(new Intent(MainActivity.this, CreationBuilder2Activity.class));
            }
        });

        //结构型
        mBtAdapterMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterModeTest.test();
            }
        });
        mBtStaticProxyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProxyTest.staticProxyTest();
            }
        });

        //行为型
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

        //其他
        mBtProductConsume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProducterAndConsumer.main();
            }
        });
    }
}
