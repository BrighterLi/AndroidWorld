package com.xiaoming.functionbaidumaker.marker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.xiaoming.functionbaidumaker.R;

//https://www.cnblogs.com/mfmdaoyou/p/6755845.html
public class MarkerActivity extends AppCompatActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Marker mMarker;
    private MarkerOptions mMarkerOptions;
    private MarkerOptions mMarkerOptions2;
    private Button btnAddOrDeletleMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maker);

        initView();
        showMapViewMaker();
    }

    private void initView() {
        mMapView = findViewById(R.id.bmapView);
        btnAddOrDeletleMarker = findViewById(R.id.btn_add_or_delete_marker);

        btnAddOrDeletleMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnAddOrDeletleMarker.getText() == "添加Marker") {
                    mMarker = ((Marker) mBaiduMap.addOverlay(mMarkerOptions));
                    btnAddOrDeletleMarker.setText("删除Marker");
                } else {
                    mMarker.remove();
                    btnAddOrDeletleMarker.setText("添加Marker");
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    private void showMapViewMaker() {
        mBaiduMap = mMapView.getMap();
        //获取地图中心点
        //LatLng latLng = mBaiduMap.getMapStatus().target;
        //定义Marker图标
        LatLng  latLng = new LatLng(39.963175, 116.400244);
        LatLng latLng2 = new LatLng(39.947246, 116.414977);
        //准备maker在照片
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_marker);
        //准备marker option加入marker使用
         mMarkerOptions =  new MarkerOptions().icon(bitmap).position(latLng);
        mMarkerOptions2 =  new MarkerOptions().icon(bitmap).position(latLng2);
        //在地图上添加Marker
         mMarker = ((Marker) mBaiduMap.addOverlay(mMarkerOptions));
         mBaiduMap.addOverlay(mMarkerOptions2);
        //实现 Marker 的点击事件响应
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MarkerActivity.this, "Marker被点击了", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
