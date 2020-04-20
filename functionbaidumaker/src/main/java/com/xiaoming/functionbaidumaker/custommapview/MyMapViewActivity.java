package com.xiaoming.functionbaidumaker.custommapview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.functionbaidumaker.R;

public class MyMapViewActivity extends AppCompatActivity {
    private MyMapView mMyMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map_view);

        initView();
    }

    private void initView() {
        //获取MyMapView对象
        mMyMapView = findViewById(R.id.my_map_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyMapView.onResume();
    }
}
