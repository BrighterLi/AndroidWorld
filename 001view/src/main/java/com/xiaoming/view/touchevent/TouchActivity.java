package com.xiaoming.view.touchevent;

import android.app.Activity;
import android.os.Bundle;

import com.xiaoming.view.R;

//ViewGroup里的onInterceptTouchEvent默认值是false这样才能把事件传给View里的onTouchEvent.
//ViewGroup里的onTouchEvent默认值是false。
//View里的onTouchEvent返回默认值是true.这样才能执行多次touch事件。

//Android中onInterceptTouchEvent与onTouchEvent:https://blog.csdn.net/top_code/article/details/8585777
public class TouchActivity extends Activity {
    public static final String TAG = "TouchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
    }
}