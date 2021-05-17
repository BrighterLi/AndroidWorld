package com.xiaoming.a000keeplearning.java.rxjava.source;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import android.os.Bundle;
import android.util.Log;

import com.xiaoming.a000keeplearning.R;

/**
 * TODO RxJava包裹模型
 */
public class SourceActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source4);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Derry");
                e.onComplete();
            }
        })

                // ↓ObservableCreate.map   包裹模型中 最里面
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        return 45454;
                    }
                })

                // ObservableMap.map
                .map(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) throws Exception {
                        return true;
                    }
                })

                // ↓包裹模型中最外面   往上走↑的时候在一层 层的剥开
                // ObservableMap.subscribe
                .subscribe(/*new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(Boolean bool) {
                        Log.d("SourceActivity4", "onNext bool:" + bool);
                    }

                    @Override
                    public void onError(Throwable e) { }

                    @Override
                    public void onComplete() { }
                }*/);
    }
}

