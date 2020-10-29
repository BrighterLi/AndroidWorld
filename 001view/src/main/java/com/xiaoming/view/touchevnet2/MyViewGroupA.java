package com.xiaoming.view.touchevnet2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyViewGroupA extends LinearLayout {
    public MyViewGroupA(Context context) {
        super(context);
    }

    public MyViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("bright#MyViewGroupA","dispatchTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("bright#MyViewGroupA","dispatchTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("bright#MyViewGroupA","dispatchTouchEvent_ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("bright#MyViewGroupA","onInterceptTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("bright#MyViewGroupA","onInterceptTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("bright#MyViewGroupA","onInterceptTouchEvent_ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("bright#MyViewGroupA","onTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("bright#MyViewGroupA","onTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("bright#MyViewGroupA","onTouchEvent_ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
