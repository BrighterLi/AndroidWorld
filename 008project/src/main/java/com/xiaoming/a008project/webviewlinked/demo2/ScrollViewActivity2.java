package com.xiaoming.a008project.webviewlinked.demo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xiaoming.a008project.R;

//ScrollView嵌套WebView与原生控件组合的一些问题:https://blog.csdn.net/ysy950803/article/details/86657790
public class ScrollViewActivity2 extends AppCompatActivity {
    String url = "http://www.baidu.com";
    String url2 = "https://pm.m.fenqile.com/entry/logic_layer/flow_webview/index.html?webViewHeight=1000&tabKey=recommendWeex";
    private EditText mTitleText;
    private FrameLayout mScrollContainer;
    private MyScrollView2 mScrollView;
    private MyWebView2 mWebView;
    private View mBottomListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view2);

        initView();
        initWeb();
    }

    private void initView() {
        mTitleText = findViewById(R.id.web_view_title);
        mScrollContainer = findViewById(R.id.scroll_container);
        mScrollView = findViewById(R.id.scroll_view);
        mWebView = findViewById(R.id.web_view);
        mBottomListLayout = findViewById(R.id.bottom_list_layout);

        mTitleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mWebView.loadUrl(mTitleText.getText().toString());
                }
                return false;
            }
        });

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mScrollView.setIsRecLayoutShow(UiUtil.isViewShowReally(mBottomListLayout));
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
                mTitleText.setText(url);
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
        mWebView.setOnOverScrollListener(new MyWebView2.OnOverScrollListener() {
            @Override
            public void onOverScrolled(MyWebView2 v, boolean onBottom) {
                mScrollView.setIsWebViewOnBottom(onBottom);
            }
        });
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                if (mWebView != null) {
                    // WebView设置固定高度，避免各种嵌套问题
                    ViewGroup.LayoutParams lp = mWebView.getLayoutParams();
                    lp.height = mScrollContainer.getHeight();
                    mWebView.setLayoutParams(lp);
                }
            }
        });

        //mWebView.loadUrl("www.mi.com");
        mWebView.loadUrl(url);
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
