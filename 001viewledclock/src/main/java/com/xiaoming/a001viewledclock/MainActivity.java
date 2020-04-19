package com.xiaoming.a001viewledclock;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private LEDClockView mLEDClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过findViewById获取LEDClockView对象
        mLEDClockView = findViewById(R.id.led_clock_view);

       /* ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

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
