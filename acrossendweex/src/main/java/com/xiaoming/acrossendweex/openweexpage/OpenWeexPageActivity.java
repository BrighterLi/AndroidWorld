package com.xiaoming.acrossendweex.openweexpage;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.xiaoming.acrossendweex.R;

//Native -> Weex,Android打开本地Weex页面
//https://blog.csdn.net/weixin_33742618/article/details/91380769
//https://blog.csdn.net/nyb521/article/details/82656791
public class OpenWeexPageActivity extends AppCompatActivity implements IWXRenderListener {
    WXSDKInstance mWXSDKInstance;
    private Handler mHandler = new Handler();
    private FrameLayout mWeexContainer;
    private LinearLayout mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mContentView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_open_weex_page,null, false);
        setContentView(R.layout.activity_open_weex_page);

        //Weex作为Activity布局的一部分，在Activity的xml进行显示。weex以view的形式出现。
        mWeexContainer = findViewById(R.id.weex_container);

        //WXSDKInstance
        mWXSDKInstance = new WXSDKInstance(this);
        //注册监听
        mWXSDKInstance.registerRenderListener(this);

        loadPage();
        //不起作用,因为都被weex页面给遮挡住了
        //mContentView.setBackgroundResource(R.drawable.activity_background);

        ////不起作用
        //getWindow().getDecorView().setBackgroundResource(R.drawable.activity_background);

        //不起作用
       /* View decorView = getWindow().getDecorView();
        if (decorView instanceof FrameLayout) {
            LinearLayout ll = (LinearLayout) ((FrameLayout) decorView).getChildAt(0);
            FrameLayout content = (FrameLayout) ll.getChildAt(1);
            content.getChildAt(0).setBackgroundResource(R.drawable.activity_background);
        }*/
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
        mWXSDKInstance.render("WXSample", WXFileUtils.loadAsset("weex/component/video/video_test.js", this), null, null, -1,-1, WXRenderStrategy.APPEND_ASYNC);
        //mWXSDKInstance.render("WXSample", WXFileUtils.loadAsset("weex/component/fql_richtext.js", this), null, null, -1,-1, WXRenderStrategy.APPEND_ASYNC);
        //mWXSDKInstance.render("WXSample", WXFileUtils.loadAsset("home.js", OpenWeexPageActivity.this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    }

    //创建View，这个view就是weex页面
    @Override
    public void onViewCreated(WXSDKInstance wxsdkInstance, View view) {
        //setContentView(view);  //整个根布局设置成weex这个view
        if (mWeexContainer != null) {
            mWeexContainer.addView(view); //Activity布局的一部分加进weex这个view
            //不起作用
            //view.setBackgroundColor(0xffff6830);
        }
        //不起作用
        //view.setBackgroundResource(R.drawable.activity_background);
        //mWeexContainer.setBackgroundResource(R.drawable.activity_background);
        //mWeexContainer.setBackgroundColor(getColor(android.R.color.holo_purple));
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