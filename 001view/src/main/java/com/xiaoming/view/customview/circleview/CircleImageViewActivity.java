package com.xiaoming.view.customview.circleview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.xiaoming.view.R;
import com.xiaoming.view.customview.clock.LEDClockView;

//将图片变成圆形
//https://blog.csdn.net/wsyizmao/article/details/78491422
//第一步，创建你的自定义类，让它继承View类，并重写构造方法。
//第二步，重写onDraw()方法。
public class CircleImageViewActivity extends AppCompatActivity {
    private LEDClockView mLEDClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_view);

        //通过findViewById获取LEDClockView对象
        mLEDClockView = findViewById(R.id.led_clock_view);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mLEDClockView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLEDClockView.stop();
    }
}

