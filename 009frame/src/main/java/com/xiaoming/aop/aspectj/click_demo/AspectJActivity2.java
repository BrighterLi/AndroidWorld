package com.xiaoming.aop.aspectj.click_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

import com.xiaoming.framearouter.R;

public class AspectJActivity2 extends AppCompatActivity {
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect2);

        mBt = findViewById(R.id.bt_click);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("AspectJActivity2", "click#onClick ");
            }
        });
    }
}
