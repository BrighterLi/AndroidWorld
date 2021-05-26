package com.xiaoming.a008project.webviewlinked.demo;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoming.a008project.R;
import com.xiaoming.a008project.webviewlinked.DpUtil;
import com.xiaoming.a008project.webviewlinked.demo2.MyScrollView2;
import com.xiaoming.a008project.webviewlinked.demo2.MyWebView2;
import com.xiaoming.a008project.webviewlinked.demo2.UiUtil;

import java.io.ByteArrayInputStream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

//android中Scrollview嵌套WebView问题:https://www.cnblogs.com/zhang-cb/p/9845021.html
public class ScrollViewActivity extends AppCompatActivity {
    private MyScrollView1 mScrollView;
    private MyWebView1 mWebView;
    private View mBottomListLayout;
    String url = "http://www.baidu.com";
    String url2 = "https://pm.m.fenqile.com/entry/logic_layer/flow_webview/index.html?webViewHeight=1000&tabKey=recommendWeex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);

        initView();
        initWeb();
    }

    private void initView() {
        mWebView = findViewById(R.id.webview);
        mScrollView = findViewById(R.id.scrollview);
        mBottomListLayout = findViewById(R.id.bottom_list_layout);

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //mScrollView.setIsRecLayoutShow(UiUtil.isViewShowReally(mBottomListLayout));
                if(scrollY < DpUtil.dip2px(ScrollViewActivity.this, 300)) {
                    mScrollView.setIsRecLayoutShow(true);
                } else {
                    mScrollView.setIsRecLayoutShow(false);
                }
            }
        });
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


        // 监听网页是否滑到底
        mWebView.setOnOverScrollListener(new MyWebView1.OnOverScrollListener() {
            @Override
            public void onOverScrolled(MyWebView1 v, boolean onBottom) {
                mScrollView.setIsWebViewOnBottom(onBottom);
            }

            @Override
            public void onOverScrollY(MyWebView1 v, int scrollY) {
                mScrollView.setmScrollY2(scrollY);
            }
        });
       /* mWebView.post(new Runnable() {
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

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}