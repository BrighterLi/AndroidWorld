package com.xiaoming.acrossendwebview.openh5;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoming.acrossendwebview.R;

public class AutomaticallyJumpActivity extends Activity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatically_jump);

        initView();
        initData();
    }

    private void initView() {
        mWebView = findViewById(R.id.web_view);
    }

    private void initData() {
        mWebView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = mWebView.getSettings();
        //是否支持 Js 使用,允许JavaScript执行,设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        //设置本地调用对象及其接口(js调用android),添加一个对象, 让javascript可以访问该对象的方法
        //mWebView.addJavascriptInterface(new JsInteraction(),"control");
        //加载本地h5页面
        mWebView.loadUrl("file:///android_asset/automatically_jump_h5.html");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("bright8", "shouldOverrideUrlLoading#url:" + url + "\nview.getUrl():" + view.getUrl());
                return super.shouldOverrideUrlLoading(view, url);
            }

           /* @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                Log.d("bright8", "shouldInterceptRequest#url:" + url + "\nview.getUrl():" + view.getUrl());
                return super.shouldInterceptRequest(view, url);
            }*/
        });
    }
}
