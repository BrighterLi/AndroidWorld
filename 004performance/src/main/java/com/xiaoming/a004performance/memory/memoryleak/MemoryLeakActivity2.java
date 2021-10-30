package com.xiaoming.a004performance.memory.memoryleak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xiaoming.a004performance.R;

//Android 非静态内部类导致的内存泄露（非static内部类）：https://blog.csdn.net/lsyz0021/article/details/51473819
public class MemoryLeakActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak2);

        mLeaHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10*60*1000);

        //finish();
    }

    private final Handler mLeaHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };
}
