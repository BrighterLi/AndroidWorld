package com.xiaoming.androidknowledgepoints;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

//监听App在前台还是后台
//方案一：自己定义一个MyApplication继承Application
public class MyApplication extends Application {
    public int count = 0;
    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityStopped(Activity activity) {
                Log.v("function#MyApplication", activity + "onActivityStopped");
                count--;
                if (count == 0) {
                    Log.v("function#MyApplication", ">>>>>>>>>>>>>>>>>>>切到后台  lifecycle");
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.v("function#MyApplication", activity + "onActivityStarted");
                if (count == 0) {
                    Log.v("function#MyApplication", ">>>>>>>>>>>>>>>>>>>切到前台  lifecycle");
                }
                count++;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.v("function#MyApplication", activity + "onActivitySaveInstanceState");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.v("function#MyApplication", activity + "onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.v("function#MyApplication", activity + "onActivityPaused");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.v("function#MyApplication", activity + "onActivityDestroyed");
            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.v("function#MyApplication", activity + "onActivityCreated");
            }
        });
    }
}
