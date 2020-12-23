package com.xiaoming.androidpoints.uiframe.ScrollView1TabLayout1ViewPager1RecyclerView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by brightli on 2020/9/23
 */
public class InterceptScrollView extends ScrollView {

    private int lastInterceptX;
    private int lastInterceptY;

    private ScrollChangedListener onScrollChangedListener;

    public InterceptScrollView(Context context) {
        super(context);
    }

    public InterceptScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                //如果是垂直滑动，则拦截
                if (Math.abs(deltaX) - Math.abs(deltaY) < 0) {
                    intercept = true;
                } else {
                    intercept = false;
                }

                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastInterceptX = x;
        lastInterceptY = y;
        super.onInterceptTouchEvent(ev);//这一句一定不能漏掉，否则无法拦截事件
        return intercept;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollChangedListener!=null){
            onScrollChangedListener.onScrollChanged(l,t,oldl,oldt);
        }
    }

    public void setScrollChangedListener(ScrollChangedListener listener){
        onScrollChangedListener = listener;
    }

    public interface ScrollChangedListener{
        void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
}

