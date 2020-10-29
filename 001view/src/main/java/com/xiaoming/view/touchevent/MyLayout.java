package com.xiaoming.view.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by brightli on 2020/9/25
 */
public class MyLayout extends FrameLayout {


    public MyLayout(Context context){
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TouchActivity.TAG,"MyLayout#onInterceptTouchEvent#ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TouchActivity.TAG,"MyLayout#onInterceptTouchEvent#ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TouchActivity.TAG,"MyLayout#onInterceptTouchEvent#ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TouchActivity.TAG,"MyLayout#onInterceptTouchEvent#ACTION_CANCEL");
                break;
        }

        Log.d(TouchActivity.TAG,"MyLayout#onInterceptTouchEvent#super.onInterceptTouchEvent(ev): "
                + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);//默认返回的是false，即默认不拦截，继续传递到子View的dispatchTouchEvent
        //return true; //如果返回的是true,则执行onTouchEvent
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //onInterceptTouchEvent不拦截即返回false就不会调用onTouchEvent
        Log.d(TouchActivity.TAG,"MyLayout#onTouchEvent#super.onTouchEvent(event):"
                + super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TouchActivity.TAG,"MyLayout#dispatchTouchEvent#ev:" + ev.toString());
        return super.dispatchTouchEvent(ev);
    }
}
