package com.xiaoming.acrossendwebview.openh5.intercept;

import android.support.annotation.Nullable;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientInterceptor extends WebViewClient {
    public static final String UTF_8 = "UTF-8";
    public static final String GBK = "GBK";

    private OnOverrideUrlListener mOnOverrideUrlListener;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        return super.shouldInterceptRequest(view, url);
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    //拦截rul加载的监听
    public interface OnOverrideUrlListener {
        boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request);
        boolean shouldOverrideUrlLoading(WebView webView, String url);
    }
}
