package com.xiaoming.acrossendweex.weexcustomcomponent.mapview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.acrossendweex.R;

import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

public class WeexComponentActivity extends AppCompatActivity implements IWXRenderListener {
    WXSDKInstance mWXSDKInstance;
    private Handler mHandler = new Handler();
    private FrameLayout mWeexContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weex_component);

        mWeexContainer = findViewById(R.id.weex_component_container);

        //WXSDKInstance
        mWXSDKInstance = new WXSDKInstance(this);
        //注册监听
        mWXSDKInstance.registerRenderListener(this);

        loadPage();
    }

    private void loadPage() {
        //WXSDKEngine初始化
        if (!WXSDKEngine.isInitialized()) {
            //WXSDKEngine未初始化，延时500ms执行
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadPage();
                }
            }, 500);
        } else {
            loadPageReally();
        }
    }

    //加载页面
    private void loadPageReally() {
        /**
         * WXSample 可以替换成自定义的字符串，针对埋点有效。
         * template 是.we transform 后的 js文件。
         * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
         * jsonInitData 可以为空。
         * width 为-1 默认全屏，可以自己定制。
         * height =-1 默认全屏，可以自己定制。
         */
        //加载本地weex页面。渲染页面，home.js就是weex打包好后给你的js文件
        mWXSDKInstance.render("WXSample", WXFileUtils.loadAsset("wxmapcomponent.js", this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    }

    //创建View，这个view就是weex页面
    @Override
    public void onViewCreated(WXSDKInstance wxsdkInstance, View view) {
        //setContentView(view);
        if (mWeexContainer != null) {
            mWeexContainer.addView(view);

        }
    }

    //渲染成功
    @Override
    public void onRenderSuccess(WXSDKInstance wxsdkInstance, int i, int i1) {

    }

    //刷新成功
    @Override
    public void onRefreshSuccess(WXSDKInstance wxsdkInstance, int i, int i1) {

    }

    //异常
    @Override
    public void onException(WXSDKInstance wxsdkInstance, String s, String s1) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}
