package com.xiaoming.androidpoints.aaajavaknowledge.hook.demo2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoming.androidpoints.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.appcompat.app.AppCompatActivity;

//hook技术简单示例:https://blog.csdn.net/shidalang/article/details/84033943
public class HookActivity2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook2);

        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText("hook");
        tv.setOnClickListener(this);
        try {
            // 1.点击事件拦截
            hookOnClickListener(tv);
            // 2. 通知拦截//
            //hookNotificationManager(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "原始的 Click  Listener", Toast.LENGTH_SHORT).show();
    }

    public static void hookOnClickListener(View view) throws Exception {
        // 1.反射得到 ListenerInfo 对象
        Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
        getListenerInfo.setAccessible(true);
        Object listenerInfo = getListenerInfo.invoke(view);
        //2.得到原始的 OnClickListener事件方法
        Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
        Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
        mOnClickListener.setAccessible(true);
        View.OnClickListener originOnClickListener = (View.OnClickListener)
                mOnClickListener.get(listenerInfo);
        // 3.用 Hook代理类 替换原始的 OnClickListener
        View.OnClickListener hookedOnClickListener = new HookedClickListenerProxy(originOnClickListener);
        mOnClickListener.set(listenerInfo, hookedOnClickListener);
    }

}