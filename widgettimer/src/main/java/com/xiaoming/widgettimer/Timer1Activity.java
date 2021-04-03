package com.xiaoming.widgettimer;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

//第一种定时器：Handler类的postDelayed方法
public class Timer1Activity extends AppCompatActivity {
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer1);

        startTimer();
    }

    //第一种定时器：Handler类的postDelayed方法
    private void startTimer() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //do something
                //每隔1s循环执行run方法
                i = i + 1; //记录执行次数
                Toast.makeText(Timer1Activity.this, i+ "", Toast.LENGTH_SHORT).show();
                handler.postDelayed(this, 2000); //每次延时2s再执行runnable
            }
        };

        handler.postDelayed(runnable, 3000);////主线程中调用,第一次延时3秒执行runnable
    }
}
