package com.xiaoming.view.customview.clock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.view.R;

public class ClockActivity extends AppCompatActivity {
    private LEDClockView mLEDClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

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
