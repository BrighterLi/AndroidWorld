package com.xiaoming.androidknowledgepoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.xiaoming.androidknowledgepoints.utils.RomUtil;

//欢迎页/启动页/开屏页
public class WelcomeActivity extends Activity {
    private Handler mHander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(RomUtil.isEmui()) {
            setTheme(R.style.AppThemeSplashHuawei);
        } else {
            setTheme(R.style.AppThemeSplash);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mHander = new Handler();
        jumpToMainActivity();
    }

    private void jumpToMainActivity() {
        mHander.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        }, 3000);
    }
}
