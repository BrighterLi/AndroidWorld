package com.widget.statusbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.widget.R;

//Android 沉浸式状态栏实践:https://www.jianshu.com/p/fc5854895a10
public class StatusBarTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_test);
    }
}
