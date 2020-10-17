package com.xiaoming.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.view.onmeasure.OnMeasureActivity;
import com.xiaoming.view.touch.TouchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements View.OnClickListener{

    @BindView(R.id.mBtTouchDemo)
    Button mBtTouchDemo;
    @BindView(R.id.mBtOnmeasure)
    Button mBtOnmeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mBtTouchDemo.setOnClickListener(this);
        mBtOnmeasure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBtTouchDemo:
            startActivity(new Intent(MainActivity.this, TouchActivity.class));
            break;
            case R.id.mBtOnmeasure:
                startActivity(new Intent(MainActivity.this, OnMeasureActivity.class));
                break;
        }
    }
}
