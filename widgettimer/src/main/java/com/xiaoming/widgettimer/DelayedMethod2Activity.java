package com.xiaoming.widgettimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

//第二种延时方法：timer + TimerTask方法
public class DelayedMethod2Activity extends AppCompatActivity {
    private final String TAG  = "DelayedMethod2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_method2);

        doSomethingDelayed();
    }

    //延时执行
    private void doSomethingDelayed() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //do something
                //在子线程中弹出Toast，会报错：java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()。
                //解决方式：先调用Looper.prepare();再调用Toast.makeText().show();最后再调用Looper.loop();在子线程中调用Toast。
                //http://blog.sina.com.cn/s/blog_680942070102xbjf.html
                //Toast.makeText(DelayedMethod2Activity.this, "延时执行了", Toast.LENGTH_SHORT).show();
                Log.d("TAG","doSomethingDelayed#run");
            }
        }, 2000); //延时2s执行
    }
}
