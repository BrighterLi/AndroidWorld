package com.xiaoming.acrossendweex;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.xiaoming.acrossendweex.openweexname.ImageAdapter;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitConfig config = new  InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        //WXSDKEngine初始化
        WXSDKEngine.initialize(this, config);
    }
}
