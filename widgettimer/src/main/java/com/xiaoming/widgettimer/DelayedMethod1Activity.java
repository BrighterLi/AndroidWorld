package com.xiaoming.widgettimer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

//第一种延时方法：Handler的postDelayed方法
public class DelayedMethod1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_method1);

        doSomethingDelayed();
    }

    //延时执行
    private void doSomethingDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //do something
                Toast.makeText(DelayedMethod1Activity.this, "延时了3秒执行", Toast.LENGTH_SHORT).show();
            }
        }, 3000); //延时3s执行
    }
}
