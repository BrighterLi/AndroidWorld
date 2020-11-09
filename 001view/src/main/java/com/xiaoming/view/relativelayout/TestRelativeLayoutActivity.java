package com.xiaoming.view.relativelayout;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.view.R;

//RelativeLayout布局：存在覆盖的后面的子布局会在前面的子布局上面
public class TestRelativeLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_relative_layout);
    }
}