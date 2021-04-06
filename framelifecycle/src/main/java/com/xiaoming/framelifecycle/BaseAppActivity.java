package com.xiaoming.framelifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

//我们需要在Activity(或Fragment)生命周期中处理Lifecycle事件（在Base类里做一次即可）
//订阅该lifecycle的观察者(比如前面的SafeObserver)就能接收到该lifecycle对应Activity(或Fragment)生命周期的回调了
public abstract class BaseAppActivity extends AppCompatActivity {
    //AppCompatActivity与v4Fragment自带LifecycleRegistry，所以通过强转获取即可
    protected LifecycleRegistry mLifecycleRegistry = (LifecycleRegistry) getLifecycle();
    protected abstract int getLayoutId();
    protected abstract void initView(@Nullable Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());
        this.initView(savedInstanceState);
        handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handleLifecycleEvent(LifecycleRegistry.Event.ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handleLifecycleEvent(LifecycleRegistry.Event.ON_STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handleLifecycleEvent(LifecycleRegistry.Event.ON_DESTROY);
    }

    public void handleLifecycleEvent(@NonNull Lifecycle.Event event) {
        mLifecycleRegistry.handleLifecycleEvent(event);
    }
}
