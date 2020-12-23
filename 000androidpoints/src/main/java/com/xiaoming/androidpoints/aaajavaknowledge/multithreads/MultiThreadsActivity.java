package com.xiaoming.androidpoints.aaajavaknowledge.multithreads;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;

public class MultiThreadsActivity extends Activity {
    private Button mBtMultiThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_threads);

        initView();
    }

    private void initView() {
        mBtMultiThread = findViewById(R.id.bt_multi_thread);

        mBtMultiThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRunnable myRunnable = new MyRunnable();
                //启动2个线程，实现资源共享的目的
                new Thread(myRunnable).start();
                new Thread(myRunnable).start();
            }
        });
    }
}
