package com.xiaoming.a008project.webviewlinked.demo;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.xiaoming.a008project.webviewlinked.DpUtil;

import androidx.core.widget.NestedScrollView;

public class MyScrollView1 extends NestedScrollView implements MyWebView1.OnOverScrollListener {
    private boolean mIsWebViewOnBottom;
    private boolean mIsRecLayoutShow;
    private float mDownY;

    private int lastInterceptX;
    private int lastInterceptY;
    private int mScrollY;
    private int mScrollHeadHeight = 300;

    private int mScrollY2;


    public MyScrollView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                //Log.d("MyScrollView1", "bright9#onInterceptTouchEvent0#mScrollY:"+ DpUtil.px2dp(getContext(), mScrollY) +"\n" + "(Math.abs(deltaX) - Math.abs(deltaY) < 0:" + ((Math.abs(deltaX) - Math.abs(deltaY) < 0)));
                Log.d("MyScrollView1", "bright9#onInterceptTouchEvent0#mScrollY2:"+ DpUtil.px2dp(getContext(), mScrollY2));
                if ((mScrollY2 > 0 && mScrollY2 < DpUtil.dip2px(getContext(), mScrollHeadHeight))
                ) {
                    Log.d("MyScrollView1", "bright9#onInterceptTouchEvent1");
                    intercept = true;
                } else {
                    Log.d("MyScrollView1", "bright9#onInterceptTouchEvent2");
                    intercept = false;
                }
                break;
        }
        lastInterceptX = x;
        lastInterceptY = y;
        super.onInterceptTouchEvent(ev);//这一句一定不能漏掉，否则无法拦截事件
        return intercept;
    }

    public void setIsWebViewOnBottom(boolean onBottom) {
        this.mIsWebViewOnBottom = onBottom;
    }

    public void setIsRecLayoutShow(boolean isRecLayoutShow) {
        this.mIsRecLayoutShow = isRecLayoutShow;
    }

    @Override
    public void onOverScrolled(MyWebView1 v, boolean onBottom) {

    }

    @Override
    public void onOverScrollY(MyWebView1 v, int scrollY) {
        mScrollY2 = scrollY;
        Log.d("MyScrollView1", "bright9#onOverScrollY#mScrollY2:"+ DpUtil.px2dp(getContext(), mScrollY2));
    }

    public void setmScrollY2(int y) {
        mScrollY2 = y;
    }
}
