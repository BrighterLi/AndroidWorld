package com.xiaoming.androidkindsdemo.flutter;

import android.os.Bundle;

import com.xiaoming.androidkindsdemo.R;
import com.xiaoming.androidkindsdemo.base.BaseActivity;

public class CustomFlutterActivity extends BaseActivity {
    private String mRouterKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);
    }
}
