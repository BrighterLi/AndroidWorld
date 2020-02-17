package com.xiaoming.widgettimer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

//第三种定时器：Thread+handler方法
public class Timer3Activity extends AppCompatActivity {
    private Handler handler;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer3);

        startTimer();
        //主线程中调用
        new Thread(new MyThread()).start();
    }

    private void startTimer() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1) {
                    //do something
                    i = i + 1; //记录执行的次数
                    Toast.makeText(Timer3Activity.this, i + "", Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };
    }

    //内部类
    class MyThread extends Thread{
        @Override
        public void run() {
            while (true) {
                try {
                    //每隔2s执行
                    Thread.sleep(2000);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message); //内部类可以直接使用外部类的成员变量handler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
