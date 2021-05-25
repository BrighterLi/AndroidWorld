package com.xiaoming.a008project.webviewlinked;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoming.a008project.R;

import java.io.ByteArrayInputStream;

import androidx.appcompat.app.AppCompatActivity;

//android中Scrollview嵌套WebView问题:https://www.cnblogs.com/zhang-cb/p/9845021.html
public class ScrollviewActivity extends AppCompatActivity {
    private MyWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);

        initView();
    }

    private void initView() {
        String url = "http://www.baidu.com";
        String url2 = "https://pm.m.fenqile.com/entry/logic_layer/flow_webview/index.html?webViewHeight=1000&tabKey=recommendWeex";
        webView = (MyWebView) findViewById(R.id.webview);
        //启用支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        //启用支持DOM Storage
        webView.getSettings().setDomStorageEnabled(true);
        //加载web资源
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //解决android7.0以后版本加载异常问题
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(request.toString());
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                // 这个方法在6.0才出现
                int statusCode = errorResponse.getStatusCode();

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //接受证书
                handler.proceed();
            }
        });
    }
}