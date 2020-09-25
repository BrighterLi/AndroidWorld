package com.xiaoming.view.touch;

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
        return super.onInterceptTouchEvent(ev);
        //return true;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TouchActivity.TAG,"MyLayout#onTouchEvent#super.onTouchEvent(event):"
                + super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
