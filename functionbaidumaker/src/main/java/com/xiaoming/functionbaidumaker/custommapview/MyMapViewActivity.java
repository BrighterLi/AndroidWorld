package com.xiaoming.functionbaidumaker.custommapview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xiaoming.functionbaidumaker.R;

public class MyMapViewActivity extends AppCompatActivity {
    private MyMapView mMyMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个时候就已经生成MyMapView对象了，调用了MyMapView的构造函数?
        setContentView(R.layout.activity_my_map_view);

        initView();
    }

    private void initView() {
        //获取MyMapView对象
        mMyMapView = findViewById(R.id.my_map_view);
        mMyMapView.setHeight(500);
        mMyMapView.setWidth(500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyMapView.onResume();
    }
}
