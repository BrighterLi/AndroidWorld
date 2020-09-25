package com.xiaoming.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.view.touch.TouchActivity;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button mBtTouchDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtTouchDemo = findViewById(R.id.bt_touch_demo);
        mBtTouchDemo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_touch_demo:
            startActivity(new Intent(MainActivity.this, TouchActivity.class));
            break;
        }
    }
}
