package com.xiaoming.androidpoints;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.reflect.Field;

public class StatusBarActivity extends AppCompatActivity {
    private Button btGetStatusBarHeight;
    private Button btChangeStatusBarColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);

        btGetStatusBarHeight = findViewById(R.id.bt_get_statusbar_height);
        btChangeStatusBarColor = findViewById(R.id.bt_change_statusbar_color);

        btGetStatusBarHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int statusBarHeight = getStatusBarHeight();
                Toast.makeText(StatusBarActivity.this, "状态栏的高度：" + statusBarHeight, Toast.LENGTH_SHORT).show();
            }
        });

        btChangeStatusBarColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWindowStatusBarColor(StatusBarActivity.this, 0xFFD81B60);
            }
        });
    }

    //高度通过反射获取
    private int getStatusBarHeight() {
        Class c = null;
        int statusBarHeight = 0;
        try {
            //获取该类的对象
            c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            //获取变量
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = this.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    //高度直接获取属性，通过getResource
    private int getStatusBarHeight2() {
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //设置状态栏颜色
    //https://blog.csdn.net/maosidiaoxian/article/details/51734895
    private void setWindowStatusBarColor(Activity activity, int colorResId) {
        //设置系统状态栏为半透明
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //通过往Window窗口的decorView添加一个View,让它大小与系统状态栏一样，然后设置这个view的背景，
        // 就可以实现修改状态栏颜色的效果
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        //获取状态栏高度
        int statusBarHeight = getStatusBarHeight();
        //获取FrameLayout的宽高
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        //?
        params.gravity = Gravity.TOP;
        //设置statusBarViewd的宽等
        statusBarView.setLayoutParams(params);
        //设置statusBarView的颜色
        statusBarView.setBackgroundColor(colorResId);
        decorViewGroup.addView(statusBarView);

    }

}
