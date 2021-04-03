package com.xiaoming.view.touchevent3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.view.R;

//super.dispatchTouchEvent(event)使用:https://blog.csdn.net/Liu_yunzhao/article/details/80247498
public class TouchEvent3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event3);
    }
}
