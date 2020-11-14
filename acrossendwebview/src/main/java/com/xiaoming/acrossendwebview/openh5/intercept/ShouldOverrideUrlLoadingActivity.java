package com.xiaoming.acrossendwebview.openh5.intercept;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.Toast;

import com.xiaoming.acrossendwebview.R;

//WebView拦截url:https://www.jianshu.com/p/55fd544246c2
//shouldOverrideUrlLoading是在webView加载url阶段执行拦截的
public class ShouldOverrideUrlLoadingActivity extends Activity {
    private WebView mWebView;
    private WebViewClientInterceptor mWebViewClientInterceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_should_override_url_loading);

        initView();
        initData();
    }

    private void initView() {
        mWebView = findViewById(R.id.web_view);
    }

    private void initData() {
        mWebViewClientInterceptor = new WebViewClientInterceptor();
        //webview基础设置
        mWebViewClientInterceptor.setWebViewConfig(mWebView, ShouldOverrideUrlLoadingActivity.this);


        mWebViewClientInterceptor.setOnOverrideUrlListener(new WebViewClientInterceptor.OnOverrideUrlListener() {
           /* @Override
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
                Toast.makeText(ShouldOverrideUrlLoadingActivity.this, "这是拦截url的操作1,原url="+webView.getUrl(), Toast.LENGTH_LONG).show();
                return true;
            }*/

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                Toast.makeText(ShouldOverrideUrlLoadingActivity.this, "这是拦截url的操作2,\nurl="+"url: " + url + "\nwebView.getUrl()：" + webView.getUrl(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

        //设置WebViewClient向一个网页发送请求，可以返回文本，文件等
        mWebView.setWebViewClient(mWebViewClientInterceptor);
        //设置可让界面弹出alert等提示框
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadUrl("file:///android_asset/should_override_url_loading_h5.html");
    }

    @Override
    protected void onDestroy() {
        //清理webview相关配置
        mWebViewClientInterceptor.destoryWebViewConfig(mWebView, ShouldOverrideUrlLoadingActivity.this);
        super.onDestroy();
    }

}
