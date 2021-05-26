package com.xiaoming.a008project.webviewlinked.demo3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;

import com.xiaoming.a008project.R;


public class ScrollViewActivity3 extends AppCompatActivity {
    private MyWebView3 mWebView;
    private ScrollView mScrollView;

    String url = "http://www.baidu.com";
    String url2 = "https://pm.m.fenqile.com/entry/logic_layer/flow_webview/index.html?webViewHeight=1000&tabKey=recommendWeex";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view3);

        initView();
        initWeb();
    }

    private void initView() {
        mWebView = findViewById(R.id.webview);
        mScrollView = findViewById(R.id.scrollview);
    }

    private void initWeb() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }

            @Override
            public void onLoadResource(WebView view, String url) {
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });


      /*  mWebView.post(new Runnable() {
            @Override
            public void run() {
                if (mWebView != null) {
                    // WebView设置固定高度，避免各种嵌套问题
                    ViewGroup.LayoutParams lp = mWebView.getLayoutParams();
                    lp.height = mScrollView.getHeight();
                    mWebView.setLayoutParams(lp);
                }
            }
        });*/
        mWebView.loadUrl(url2);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.onDestroy();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        mWebView.onTrimMemory(level);
    }

}