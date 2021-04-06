package com.xiaoming.widgettimer;

import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

//第二种定时器：用handler+timer+timeTask方法
public class Timer2Activity extends AppCompatActivity {
    private int i; //成员变量既使不初始化也会默认初始值，i默认初始值为0。局部变量必须自己初始化。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer2);

        startTimer();
    }


    //第二种定时器：用handler+timer+timeTask方法
    private void startTimer() {
        final Handler handler =  new Handler() {
            @Override
            public void handleMessage(Message msg) {
                i = i + 1; //记录执行的次数
                if(msg.what == 1) {
                    //do something
                    Toast.makeText(Timer2Activity.this, i + "", Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };

        Timer timer  = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };

        //主线程中调用。第一次延时3s,每隔2s执行run方法
        timer.schedule(timerTask, 3000, 2000);
    }
}
