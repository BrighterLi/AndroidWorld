package com.xiaoming.androidpoints;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.xiaoming.androidpoints.aaajavaknowledge.hook.demo.InstrumentationProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

//监听App在前台还是后台
//方案一：自己定义一个MyApplication继承Application
public class MyApplication extends Application {
    public int count = 0;
    public static final String TAG = "MyApplication";
    public static final String ACTIVIT_THREAD = "android.app.ActivityThread";
    public static final String CURRENT_ACTIVITY_THREAD = "currentActivityThread";
    public static final String INSTRUMENTATION = "mInstrumentation";

    private static Context sContext;
    public static DexClassLoader mClassLoader;
    private AssetManager assetManager;
    private Resources newResource;
    private Resources.Theme mTheme;

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


    //hook demo
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



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MyHookHelper.hookActivityResource(base);
        sContext = base;

        try {
//            //拿到ContextWrapper类中的字段mBase字段，就是Context
//            Class<?> aClass = activity.getClass();
//            Log.e("Main", "activity aClass = " + aClass);
//            Log.e("Main", "activity aClass aClass = " + aClass.getSuperclass());
//            Log.e("Main", "activity aClass aClass aClass = " + aClass.getSuperclass().getSuperclass());
//            Field mBaseField = Activity.class.getSuperclass().getSuperclass().getDeclaredField("mBase");
//
//            mBaseField.setAccessible(true);
//            Context mBase = (Context) mBaseField.get(activity);
//            Log.e("Main", "mBase = " + mBase);
//
//            //拿出Context中的Resource字段
//            Class<?> mContextImplClass = Class.forName("android.app.ContextImpl");
//            Field mResourcesField = mContextImplClass.getDeclaredField("mResources");
//            mResourcesField.setAccessible(true);

            //创建我们自己的Resource
            String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/chajian_demo.apk";
            String mPath = getPackageResourcePath();

            assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);

//            addAssetPathMethod.invoke(assetManager, mPath);
            addAssetPathMethod.invoke(assetManager, apkPath);

            Method ensureStringBlocks = AssetManager.class.getDeclaredMethod("ensureStringBlocks");
            ensureStringBlocks.setAccessible(true);
            ensureStringBlocks.invoke(assetManager);

            Resources supResource = getResources();
            Log.e("Main", "bright9#supResource = " + supResource);
            newResource = new Resources(assetManager, supResource.getDisplayMetrics(), supResource.getConfiguration());
            Log.e("Main", "bright9#设置 getResource = " + getResources());

            mTheme = newResource.newTheme();
            mTheme.setTo(super.getTheme());
        } catch (Exception e) {
            Log.e("Main", "bright9#走了我的callActivityOnCreate 错了 = " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public AssetManager getAssets() {
        return assetManager == null ? super.getAssets() : assetManager;
    }

    @Override
    public Resources getResources() {
        return newResource == null ? super.getResources() : newResource;
    }

    @Override
    public Resources.Theme getTheme() {
        return mTheme == null ? super.getTheme() : mTheme;
    }

    public static Context getContext() {
        return sContext;
    }
}
