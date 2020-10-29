package com.xiaoming.view.touchevnet2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.xiaoming.view.R;

//Android的事件分发（dispatchTouchEvent），拦截（onInterceptTouchEvent）与处理（onTouchEvent）:
//https://blog.csdn.net/caifengyao/article/details/65437695?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
public class TouchEvent2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event2);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("bright#Activity","dispatchTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("bright#Activity","dispatchTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("bright#Activity","dispatchTouchEvent_ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("bright#Activity","onTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("bright#Activity","onTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("bright#Activity","onTouchEvent_ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

}
