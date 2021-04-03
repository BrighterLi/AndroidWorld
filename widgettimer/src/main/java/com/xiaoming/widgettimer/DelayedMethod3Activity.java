package com.xiaoming.widgettimer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

//第三种延时方法：Thread方法
public class DelayedMethod3Activity extends AppCompatActivity {
    private final String TAG = "DelayedMethod3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_method3);

        doSomethingDelayed();
    }

    //延时操作
    private void doSomethingDelayed() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000); //延时2s
                    //do something
                    //在子线程中弹出Toast，会报错：java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()。
                    //解决方式：先调用Looper.prepare();再调用Toast.makeText().show();最后再调用Looper.loop();在子线程中调用Toast。
                    //http://blog.sina.com.cn/s/blog_680942070102xbjf.html
                    Log.d(TAG,"doSomethingDelayed#run");
                    //Toast.makeText(DelayedMethod3Activity.this, "延时执行了", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
