package com.xiaoming.androidpoints.plugin.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.plugin.demo.ams.AMSHookHelper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import androidx.appcompat.app.AppCompatActivity;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;

//Android插件化：https://blog.csdn.net/yulong0809/article/details/59113935
//https://github.com/ljqloveyou123/LiujiaqiAndroid
public class PluginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);

        findViewById(R.id.bbb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里说一下，我们真正要启动的Activity不是直接用类名.class，因为
                //我们这个应用里根本没有这个类
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.huanju.chajiandemo",
                        "com.huanju.chajiandemo.TestActivity"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            new Thread() {
                @Override
                public void run() {
                    //创建一个属于我们自己插件的ClassLoader，我们分析过只能使用DexClassLoader
                    String cachePath = PluginActivity.this.getCacheDir().getAbsolutePath();
                    String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/chajian_demo.apk";
                    DexClassLoader mClassLoader = new DexClassLoader(apkPath, cachePath, cachePath, getClassLoader());
                    MyHookHelper.inject(mClassLoader);
                    try {
                        AMSHookHelper.hookActivityManagerNative();
                        AMSHookHelper.hookActivityThreadHandler();
                    } catch (Exception e) {
                        Log.e("Main", "bright9#加载异常了 = " + e.getMessage());
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(PluginActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //原来demo的方法
    public static void patchClassLoader(ClassLoader cl, File apkFile, File optDexFile)
            throws IllegalAccessException, NoSuchMethodException, IOException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        // 获取 BaseDexClassLoader : pathList
        Field pathListField = DexClassLoader.class.getSuperclass().getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathListObj = pathListField.get(cl);
        // 获取 PathList: Element[] dexElements
        Field dexElementArray = pathListObj.getClass().getDeclaredField("dexElements");
        dexElementArray.setAccessible(true);
        Object[] dexElements = (Object[]) dexElementArray.get(pathListObj);
        // Element 类型
        Class<?> elementClass = dexElements.getClass().getComponentType();
        // 创建一个数组, 用来替换原始的数组
        Object[] newElements = (Object[]) Array.newInstance(elementClass, dexElements.length + 1);
        // 构造插件Element(File file, boolean isDirectory, File zip, DexFile dexFile) 这个构造函数
        Constructor<?> constructor = elementClass.getConstructor(File.class, boolean.class, File.class, DexFile.class);
        Object o = constructor.newInstance(apkFile, false, apkFile, DexFile.loadDex(apkFile.getCanonicalPath(), optDexFile.getAbsolutePath(), 0));
        Object[] toAddElementArray = new Object[]{o};
        // 把原始的elements复制进去
        System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
        // 插件的那个element复制进去
        System.arraycopy(toAddElementArray, 0, newElements, dexElements.length, toAddElementArray.length);
        // 替换
        dexElementArray.set(pathListObj, newElements);
    }

}