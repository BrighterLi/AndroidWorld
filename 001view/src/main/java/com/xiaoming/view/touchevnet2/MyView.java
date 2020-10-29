package com.xiaoming.view.touchevnet2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("bright#MyView","dispatchTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("bright#MyView","dispatchTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("bright#MyView","dispatchTouchEvent_ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event); //dispatchTouchEvent返回值决定onTouchEvent是否消费？
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("bright#MyView","onTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("bright#MyView","onTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("bright#MyView","onTouchEvent_ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

}
