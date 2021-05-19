package com.xiaoming.androidpoints.aaajavaknowledge.hook.demo;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

public class InstrumentationProxy extends Instrumentation {

    public static final String TAG = "InstrumentationProxy";
    public static final String EXEC_START_ACTIVITY = "execStartActivity";

    // ActivityThread里面原始的Instrumentation对象,这里千万不能写成mInstrumentation,这样写
    //抛出异常，已亲测试，所以这个地方就要注意了
    public Instrumentation oldInstrumentation;

    //通过构造函数来传递对象
    public InstrumentationProxy(Instrumentation mInstrumentation) {
        oldInstrumentation = mInstrumentation;
    }


    //这个方法是由于原始方法里面的Instrumentation有execStartActivity方法来定的
    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options) {
        Log.d(TAG, "\nbright8#打印调用startActivity相关参数: \n" + "who = [" + who + "], " +
                "\ncontextThread = [" + contextThread + "], \ntoken = [" + token + "], " +
                "\ntarget = [" + target + "], \nintent = [" + intent +
                "], \nrequestCode = [" + requestCode + "], \noptions = [" + options + "]");


        Log.i(TAG, "bright8#------------hook  success------------->");
        Log.i(TAG, "bright8#这里可以做你在打开StartActivity方法之前的事情");
        Log.i(TAG, "bright8#------------hook  success------------->");
        Log.i(TAG, "bright8#");

        //由于这个方法是隐藏的，所以需要反射来调用，先找到这方法
        try {
            Method execStartActivity = Instrumentation.class.getDeclaredMethod(
                    EXEC_START_ACTIVITY,
                    Context.class, IBinder.class, IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);
            execStartActivity.setAccessible(true);
            return (ActivityResult) execStartActivity.invoke(oldInstrumentation, who,
                    contextThread, token, target, intent, requestCode, options);
        } catch (Exception e) {
            //如果你在这个类的成员变量Instrumentation的实例写错mInstrument,代码讲会执行到这里来
            throw new RuntimeException("if Instrumentation paramerter is mInstrumentation, hook will fail");
        }
    }
}
