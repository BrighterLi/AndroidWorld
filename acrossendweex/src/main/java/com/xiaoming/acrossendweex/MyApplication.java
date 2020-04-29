package com.xiaoming.acrossendweex;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.xiaoming.acrossendweex.openweexpage.ImageAdapter;

public class MyApplication extends Application {
    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        InitConfig config = new  InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        //WXSDKEngine初始化
        WXSDKEngine.initialize(this, config);
    }
}
