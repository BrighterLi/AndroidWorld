package com.xiaoming.acrossendweex;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.xiaoming.acrossendweex.openweexpage.ImageAdapter;
import com.xiaoming.acrossendweex.weexcustomcomponent.FQLWXMapComponent;

public class MyApplication extends Application {
    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        InitConfig config = new  InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        //WXSDKEngine初始化
        WXSDKEngine.initialize(this, config);
        //Weex组件注册
        try {
            WXSDKEngine.registerComponent("FQLWXMapComponent", FQLWXMapComponent.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}
