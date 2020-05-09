package com.xiaoming.androidkindsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidkindsdemo.flutter.CustomFlutterActivity;

public class MainActivity extends AppCompatActivity implements View.
        OnClickListener{
    private Button mBtnFlutter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtnFlutter = findViewById(R.id.btn_flutter);
        mBtnFlutter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_flutter:
                startActivity(new Intent(MainActivity.this, CustomFlutterActivity.class));
                break;
        }
    }
}
