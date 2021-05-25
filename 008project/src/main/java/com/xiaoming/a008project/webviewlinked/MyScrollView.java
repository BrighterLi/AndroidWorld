package com.xiaoming.a008project.webviewlinked;


import android.content.Context;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private boolean mIsWebViewOnBottom;
    private boolean mIsOthersLayoutShow;
    private float mDownY;


    public MyScrollView(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mDownY;
                if (dy < 0) { // 手指向上滑
                    if (!mIsWebViewOnBottom)
                        return false; // 网页未到底，不拦截事件
                } else { // 手指向下滑
                    if (!mIsOthersLayoutShow)
                        return false; // 底部原生layout完全隐藏，不拦截事件
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    public void setIsWebViewOnBottom(boolean onBottom) {
        this.mIsWebViewOnBottom = onBottom;
    }
    public void setIsOthersLayoutShow(boolean isOthersLayoutShow) {
        this.mIsOthersLayoutShow = isOthersLayoutShow;
    }

}
