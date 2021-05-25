package com.xiaoming.a008project.webviewlinked;


import android.content.Context;
import android.webkit.WebView;

public class MyWebView extends WebView {

    public interface OnOverScrollListener {
        void onOverScrolled(MyWebView v, boolean onBottom);
    }

    private OnOverScrollListener mOnOverScrollListener;

    public MyWebView(Context context) {
        this(context, null);
    }
    // 省略无关代码
    public void setOnOverScrollListener(OnOverScrollListener listener) {
        this.mOnOverScrollListener = listener;
    }
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (mOnOverScrollListener != null) {
            // clampedY=true的前提下，scrollY=0时表示滑动到顶部，scrollY!=0时表示到底部
            mOnOverScrollListener.onOverScrolled(this, scrollY != 0 && clampedY);
        }
    }

}
