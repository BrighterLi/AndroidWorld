package com.xiaoming.functionbaidumaker;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.xiaoming.functionbaidumaker.weexmapview.FQLWXMapComponent;
import com.xiaoming.functionbaidumaker.weexmapview.ImageAdapter;

public class MyApplication extends Application {
    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

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
