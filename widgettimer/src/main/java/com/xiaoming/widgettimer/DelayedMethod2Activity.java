package com.xiaoming.widgettimer;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

//第二种延时方法：timer + TimerTask方法
public class DelayedMethod2Activity extends AppCompatActivity {
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_method2);

        doSomethingDelayed();
    }

    //延时执行
    private void doSomethingDelayed() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //do something
                Toast.makeText(DelayedMethod2Activity.this, "延时执行了", Toast.LENGTH_SHORT).show();
            }
        }, 2000); //延时2s执行
    }
}
