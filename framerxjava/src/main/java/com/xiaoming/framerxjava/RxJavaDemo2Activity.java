package com.xiaoming.framerxjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

//RxJava Demo
// 观察者和被观察者不分开写，链式调用
public class RxJavaDemo2Activity extends Activity {
    Button btClick;
    Button btBack;
    final String Host = "http://blog.csdn.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);
        btClick = findViewById(R.id.bt_click);
        btBack = findViewById(R.id.bt_back);

        //just:不断地将事件添加到任务队列中
        //map:转换，如下将Host字符串转化成Host+s字符串
        //subscribe:订阅
        Observable.just("itachi85").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return Host + s;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(RxJavaDemo2Activity.this, s, Toast.LENGTH_LONG).show();
            }
        });

        btClick.setVisibility(View.GONE);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
