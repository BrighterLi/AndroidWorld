package com.xiaoming.androidknowledgepoints.uiframe.ScrollView1TabLayout1ViewPager1RecyclerView;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.androidknowledgepoints.R;

//Android开发之复杂布局嵌套(ScrollView+TabLayout+ViewPager+RecyclerView)导致冲突的解决办法
//https://blog.csdn.net/hq942845204/article/details/88844272
public class UiFrame3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_frame3);
    }
}
