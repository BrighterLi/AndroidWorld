package com.xiaoming.a000keeplearning.java.rxjava.source;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.xiaoming.a000keeplearning.R;

/**
 * TODO 一个Map源码分析
 */
public class SourceActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source3);
        // ObseravbleCreate  自定义source传递进去了 == source
        Observable.create(

                // 自定义source
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {

                    }
                })

                //  ObseravbleCreate.map
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        return null;
                    }
                })

                // ObservableMap.subscribe
                .subscribe(

                        // 自定义观察者（终点）
                        new Observer<Bitmap>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Bitmap bitmap) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }




    private void t() {

        // 自己去查询
        // Flowable.create

        // single(只能发射单个) Observable的简化版    Observable完整版(多个 单个)

        // 背压
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                while (true) {
                    e.onNext("A");
                }
            }
        })

                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        // Thread.sleep(5000); // 五秒后 消费下
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

