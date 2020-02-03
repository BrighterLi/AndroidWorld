package com.xiaoming.framerxjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;

//RxJava Demo
// 观察者和被观察者分开写
public class RxJavaDemoActivity extends Activity {
    Button btClick;
    Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);
        btClick = findViewById(R.id.bt_click);
        btBack = findViewById(R.id.bt_back);

        //被观察者
        final Observable<String> testObservable =  Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("How are you");
                subscriber.onNext("i'm fine");
                subscriber.onCompleted();
            }
        });

        //观察者
        final Subscriber<String> testSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(RxJavaDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }
        };


        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //订阅
                testObservable.subscribe(testSubscriber);
            }

        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
