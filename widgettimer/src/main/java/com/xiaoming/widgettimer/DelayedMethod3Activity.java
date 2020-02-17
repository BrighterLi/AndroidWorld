package com.xiaoming.widgettimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//第三种延时方法：Thread方法
public class DelayedMethod3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_method3);

        doSomethingDelayed();
    }

    private void doSomethingDelayed() {

    }
}
