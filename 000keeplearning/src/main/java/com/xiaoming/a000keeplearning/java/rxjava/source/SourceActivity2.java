package com.xiaoming.a000keeplearning.java.rxjava.source;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

import android.os.Bundle;

import com.xiaoming.a000keeplearning.R;

/**
 * TODO RxJava的观察者模式
 * 1：Observer 源码看看
 * 2：Observable创建过程 源码分析
 * 3：subscribe订阅过程 源码分析
 */
public class SourceActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source2);
        // 结论： new ObservableCreate() {source == 自定义source}
        // 2：Observable创建过程 源码分析
       /* Observable.create(
                // 自定义source
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        // 发射器.onNext
                        emitter.onNext("A");
                    }
                })

                // 3：subscribe订阅过程 源码分析
                // ObservableCreate. subscribe
                .subscribe(


                        // 自定义观察者
                        // 1：Observer 源码看看
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });*/
    }
}

