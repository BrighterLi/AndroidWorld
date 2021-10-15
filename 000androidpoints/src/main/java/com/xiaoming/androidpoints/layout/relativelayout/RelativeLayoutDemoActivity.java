package com.xiaoming.androidpoints.layout.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xiaoming.androidpoints.R;

//RelativeLayout 两个子View 居中 + 靠左/靠右
//android在RelativeLayout里的TextView或者EditView里放置ImageView使其居中；靠右显示设置：https://blog.csdn.net/ShiXinXin_Harbour/article/details/104276016
public class RelativeLayoutDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout_demo);
    }
}
