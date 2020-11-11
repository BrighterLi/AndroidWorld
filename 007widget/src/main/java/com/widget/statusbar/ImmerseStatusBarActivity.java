package com.widget.statusbar;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.widget.R;

//Android沉浸式状态栏实现：https://www.cnblogs.com/geeksongs/p/12031785.html
//android:fitsSystemWindows="true" ：触发View的padding属性来给系统窗口留出空间
//android:clipToPadding="true" : 控件的绘制区域是否在padding里面
public class ImmerseStatusBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immerse_status_bar);
        //设置状态栏透明，并且变为全屏模式
        //取当前手机SDK版本号大于或等于Build.VERSION_CODES.KITKAT(安卓4.4)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }
}
