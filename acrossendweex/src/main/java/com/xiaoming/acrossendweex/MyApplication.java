package com.xiaoming.acrossendweex;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.xiaoming.acrossendweex.openweexpage.ImageAdapter;

public class MyApplication extends Application {
    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        InitConfig config = new  InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        //WXSDKEngine初始化
        /**
         * 底层的初始化是异步执行的，尤其是初始化JS引擎部分的代码（WXBridgeManager），是相当耗时的
         * 因此，在调用完初始化之后，Activity第一次调用的时候，一定要增加是否已经初始化完成的判断
         * 如果没有完成初始化，适当的增加延迟等待的代码
         **/
        WXSDKEngine.initialize(this, config);
        /*//Weex组件注册
        try {
            WXSDKEngine.registerComponent("FQLWXMapComponent", FQLWXMapComponent.class);
        } catch (WXException e) {
            e.printStackTrace();
        }*/
    }
}
