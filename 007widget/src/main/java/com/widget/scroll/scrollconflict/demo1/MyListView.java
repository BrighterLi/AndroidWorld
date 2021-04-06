package com.widget.scroll.scrollconflict.demo1;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);

    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private float lastY;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("MyListView", "dispatchTouchEvent#ev.getY:" + ev.getY() + "   lastY:" + lastY);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //父View不拦截ACTION_DOWN方法的原因，根据之前的源码阅读可知如果ACTION_DOWN事件被拦截，
            // 之后的所有事件就都不会再传递下去了
            getParent().getParent().requestDisallowInterceptTouchEvent(true);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            //getX()、getY()返回的则是触摸点相对于View的位置
            if (lastY > ev.getY()) { //向上过程，ev.getY越变越小
                // 这里判断是向上滑动，而且不能再向上滑了，说明到头了，就让父View处理
                if (!canScrollList(1)) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            } else if (ev.getY() > lastY) { //向下
                // 这里判断是向下滑动，而且不能再向下滑了，说明到头了，同样让父View处理
                if (!canScrollList(-1)) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            }
        }
        lastY = ev.getY();
        return super.dispatchTouchEvent(ev); //？
    }
}
