package com.xiaoming.functionbaidumaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.functionbaidumaker.custommapview.MyMapViewActivity;
import com.xiaoming.functionbaidumaker.marker.MarkerActivity;
import com.xiaoming.functionbaidumaker.weexmapview.WeexComponentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnMaker;
    private Button btnCustomMapView;
    private Button btnWxComponentMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnMaker = findViewById(R.id.btn_maker);
        btnCustomMapView = findViewById(R.id.btn_custom_map_view);
        btnCustomMapView = findViewById(R.id.btn_wx_component_map_view);

        btnMaker.setOnClickListener(this);
        btnCustomMapView.setOnClickListener(this);
        btnWxComponentMapView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_maker:
                startActivity(new Intent(MainActivity.this, MarkerActivity.class));
                break;
            case R.id.btn_custom_map_view:
                startActivity(new Intent(MainActivity.this, MyMapViewActivity.class));
                break;
            case R.id.btn_wx_component_map_view:
                startActivity(new Intent(MainActivity.this, WeexComponentActivity.class));
                break;
        }
    }
}
