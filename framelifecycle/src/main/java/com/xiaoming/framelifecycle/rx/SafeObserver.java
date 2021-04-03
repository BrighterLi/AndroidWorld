package com.xiaoming.framelifecycle.rx;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
*  一个生命周期安全的RxObserver。
*/

//自定义的RxObserver实现LifecycleObserver接口
public abstract class SafeObserver<T> implements LifecycleObserver, Observer<T> {
    public static final String TAG = "SafeObserver";
    private Lifecycle mLifecycle;
    private Disposable mDisposable;

    //不传入Lifecycle，手动管理该Observer的生命周期
    public SafeObserver() {
        //unSafe
    }

    //传入Lifecycle，自动保证回调安全
    public SafeObserver(Lifecycle lifecycle) {
        mLifecycle = lifecycle;
    }


    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        if(mLifecycle != null) {
            mLifecycle.addObserver(this); //加入到lifecycle观察者
        }
    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onEnd();
    }

    @Override
    public void onComplete() {
        onEnd();
    }

    protected void onEnd() {
        Log.i(TAG, "onEnd");
        onDispose(); //RxObserver结束后自动释放
    }

    //释放Rx观察者（在Lifecycle的ON_DESTROY事件发生时会自动调用该方法）
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDispose() {
        Log.i(TAG, "dispose");
        if(mLifecycle != null) {
            mLifecycle.removeObserver(this); //解除与lifecycle的绑定
            if(mDisposable != null && !mDisposable.isDisposed()) {
                mDisposable.dispose();
            }
        }
    }
}
