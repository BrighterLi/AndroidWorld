package com.xiaoming.androidpoints;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;

import com.xiaoming.androidpoints.aaajavaknowledge.hook.demo.InstrumentationProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//监听App在前台还是后台
//方案一：自己定义一个MyApplication继承Application
public class MyApplication extends Application {
    public int count = 0;
    public static final String TAG = "MyApplication";
    public static final String ACTIVIT_THREAD = "android.app.ActivityThread";
    public static final String CURRENT_ACTIVITY_THREAD = "currentActivityThread";
    public static final String INSTRUMENTATION = "mInstrumentation";

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            //这个方法一般是写在Application的oncreate函数里面，如果你写在activity里面的oncrate函数里面就已经晚了
            attachContext();
        } catch (Exception e) {
            e.printStackTrace();
        }

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


    public static void attachContext() throws Exception {
        //获取当前的ActivityThread对象
        Class<?> activityThreadClass = Class.forName(ACTIVIT_THREAD);
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod(CURRENT_ACTIVITY_THREAD);
        currentActivityThreadMethod.setAccessible(true);
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);

        //拿到在ActivityThread类里面的原始mInstrumentation对象
        Field mInstrumentationField = activityThreadClass.getDeclaredField(INSTRUMENTATION);
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

        //构建我们的代理对象
        Instrumentation evilInstrumentation = new InstrumentationProxy(mInstrumentation);

        //通过反射，换掉字段，注意，这里是反射的代码，不是Instrumentation里面的方法
        mInstrumentationField.set(currentActivityThread, evilInstrumentation);

        //做个标记，方便后面查看
        Log.i(TAG, "bright8#has go in MyApplication attachContext method");
    }
}
