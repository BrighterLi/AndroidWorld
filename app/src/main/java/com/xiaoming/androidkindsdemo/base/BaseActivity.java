package com.xiaoming.androidkindsdemo.base;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.xiaoming.androidkindsdemo.R;

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
