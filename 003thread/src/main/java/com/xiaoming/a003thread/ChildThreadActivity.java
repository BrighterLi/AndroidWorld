package com.xiaoming.a003thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//测试子线程方法执行顺序
public class ChildThreadActivity extends AppCompatActivity {
    private static final String TAG = "ChildThreadActivity";
    private Button mBt;
    private Button mBt2;
    private Button mBt3;
    private int p = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_thread);

        mBt = findViewById(R.id.bt);
        mBt2 = findViewById(R.id.bt2);
        mBt3 = findViewById(R.id.bt3);

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startChildThread(); //开启的子线程与testA()不按顺序执行
                testA();
            }
        });

        mBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testC();
                testD(); //tescC()虽然是耗时操作，但是还是主线程，所以会等testC()执行完再执行
            }
        });

        mBt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = startChildThreadReturn(); //开启的子线程虽然有返回值，但与testE()不按顺序执行
                Log.v(TAG, "bright#startChildThreadReturn-testE#result：" + result);
                testE(); //tescC()虽然是耗时操作，但是还是主线程，所以会等testC()执行完再执行
            }
        });
    }

    //子线程里的按顺序执行
    private void startChildThread() {
        Log.v(TAG, "bright#startChildThread1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.v(TAG, "bright#startChildThread2");
                    Thread.sleep(1000);
                    p = 1;
                    testB();
                    Log.v(TAG, "bright#startChildThread3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private int startChildThreadReturn() {
        final int result = -1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.v(TAG, "bright#startChildThreadReturn");
                    Thread.sleep(1000);
                    Log.v(TAG, "bright#startChildThreadReturn2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Log.v(TAG, "bright#startChildThreadReturn3");
        return result;
    }

    private void testA() {
        Log.v(TAG, "bright#testA#p：" + p);
    }

    private void testB() {
        Log.v(TAG, "bright#testB#p：" + p);
    }

    private void testC() {
        Log.v(TAG, "bright#testC1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "bright#testC2");
    }

    private void testD() {
        Log.v(TAG, "bright#testD");
    }

    private void testE() {
        Log.v(TAG, "bright#testE");
    }
}
