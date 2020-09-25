package com.xiaoming.view.touch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by brightli on 2020/9/25
 */
@SuppressLint("AppCompatCustomView")
public class MyView extends Button {

    public MyView(Context context){
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TouchActivity.TAG,"MyView#onTouchEvent#ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TouchActivity.TAG,"MyView#onTouchEvent#ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TouchActivity.TAG,"MyView#onTouchEvent#ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TouchActivity.TAG,"MyView#onTouchEvent#ACTION_CANCEL");
                break;
        }
        Log.d(TouchActivity.TAG,"MyView#onTouchEvent#super.onTouchEvent(event):"
                + super.onTouchEvent(event));
        //return super.onTouchEvent(event);
        return false;
    }

}
