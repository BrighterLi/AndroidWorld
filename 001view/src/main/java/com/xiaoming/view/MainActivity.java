package com.xiaoming.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.view.customview.CustomViewActivity;
import com.xiaoming.view.customview.commheader.CommHeaderActivity;
import com.xiaoming.view.onmeasure.OnMeasureGroupView2Activity;
import com.xiaoming.view.onmeasure.OnMeasureGroupViewActivity;
import com.xiaoming.view.onmeasure.OnMeasureViewActivity;
import com.xiaoming.view.relativelayout.TestRelativeLayoutActivity;
import com.xiaoming.view.touchevent.TouchActivity;
import com.xiaoming.view.touchevnet2.TouchEvent2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements View.OnClickListener{

    @BindView(R.id.mBtTouchDemo)
    Button mBtTouchDemo;
    @BindView(R.id.mBtOnmeasure)
    Button mBtOnmeasure;
    @BindView(R.id.mBtOnmeasureViewGroup)
    Button mBtOnmeasureViewGroup;
    @BindView(R.id.mBtOnmeasureViewGroup2)
    Button mBtOnmeasureViewGroup2;
    @BindView(R.id.mBtRelativeLayout)
    Button mBtRelativeLayout;
    @BindView(R.id.mBtTouchEvent2)
    Button mBtTouchEvent2;
    @BindView(R.id.mBtCustomView)
    Button mBtCustomView;
    @BindView(R.id.mBtCommHeader)
    Button mBtCommHeader;

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
        mBtOnmeasureViewGroup.setOnClickListener(this);
        mBtOnmeasureViewGroup2.setOnClickListener(this);
        mBtRelativeLayout.setOnClickListener(this);
        mBtTouchEvent2.setOnClickListener(this);
        mBtCustomView.setOnClickListener(this);
        mBtCommHeader.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBtTouchDemo:
            startActivity(new Intent(MainActivity.this, TouchActivity.class));
            break;
            case R.id.mBtOnmeasure:
                startActivity(new Intent(MainActivity.this, OnMeasureViewActivity.class));
                break;
            case R.id.mBtOnmeasureViewGroup:
                startActivity(new Intent(MainActivity.this, OnMeasureGroupViewActivity.class));
                break;
            case R.id.mBtOnmeasureViewGroup2:
                startActivity(new Intent(MainActivity.this, OnMeasureGroupView2Activity.class));
                break;
            case R.id.mBtRelativeLayout:
                startActivity(new Intent(MainActivity.this, TestRelativeLayoutActivity.class));
                break;
            case R.id.mBtTouchEvent2:
                startActivity(new Intent(MainActivity.this, TouchEvent2Activity.class));
                break;
            case R.id.mBtCustomView:
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
                break;
            case R.id.mBtCommHeader:
                startActivity(new Intent(MainActivity.this, CommHeaderActivity.class));
                break;
        }
    }
}
