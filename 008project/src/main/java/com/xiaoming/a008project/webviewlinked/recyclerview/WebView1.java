package com.xiaoming.a008project.webviewlinked.recyclerview;

import android.annotation.SuppressLint;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xiaoming.a008project.webviewlinked.DpUtil;

public class WebView1 extends WebView {
    private int mScrollY;
    private int mScrollHeadHeight = 300;


    public WebView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebSettings(context);
        // 获取焦点，解决不能弹出软键盘的问题
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public WebView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebView1(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mScrollY > 0 && mScrollY < DpUtil.dip2px(getContext(), mScrollHeadHeight)) {
            requestDisallowInterceptTouchEvent(false);
        } else {
            requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        mScrollY = scrollY;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebSettings(Context context) {
        WebSettings settings = getSettings();
        settings.setDefaultTextEncodingName("utf-8"); // 设置默认编码格式
        settings.setJavaScriptEnabled(true); // 支持JS交互
        settings.setAllowFileAccess(true); // 设置可以访问文件
        settings.setLoadsImagesAutomatically(true); // 自动加载图片
        settings.setSupportZoom(false); // 禁止缩放
        settings.setLoadWithOverviewMode(true); // 适应屏幕
        settings.setDomStorageEnabled(true); // 离线加载
        // 支持Http和Https混合
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 缓存，可以不设，这里path是随便写的，可以自定义
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(context.getCacheDir().getPath());
    }

    @Override
    public void loadUrl(String url) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            super.loadUrl(url);
        } else {
            super.loadUrl("http://" + url);
        }
    }

    public void onDestroy() {
        // 避免WebView的内存泄漏
        loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        clearHistory();
        ViewGroup webViewParent = (ViewGroup) getParent();
        webViewParent.removeView(this);
        destroy();
    }

    public void onTrimMemory(int level) {
        if (level >= ComponentCallbacks2.TRIM_MEMORY_MODERATE) {
            clearCache(false);
        }
    }

}


