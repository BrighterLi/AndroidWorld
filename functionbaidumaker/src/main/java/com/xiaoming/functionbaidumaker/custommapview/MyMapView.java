package com.xiaoming.functionbaidumaker.custommapview;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.xiaoming.functionbaidumaker.R;

//新建MyMapView继承LinearLayout类，开发一个自定义的组合组件
public class MyMapView extends LinearLayout{
    private static final String TAG = "MyMapView";
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public MyMapView(Context context) {
        super(context);
        Log.d(TAG,"MyMapView#1");
        init(context);

    }

    //为什么MyMapViewActivity的 mMyMapView = findViewById(R.id.my_map_view)默认走到这里？
    public MyMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG,"MyMapView#2");
        init(context);
    }

    public MyMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG,"MyMapView#3");
        init(context);
    }

    public MyMapView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG,"MyMapView#4");
        init(context);
    }

    public void onResume() {
        mMapView.onResume();
    }

    private void init(Context context) {
        Log.d(TAG,"init");
        LinearLayout.inflate(context, R.layout.layout_map, this); //获取布局
        mMapView = findViewById(R.id.map); //得到MapView对象
        if(mBaiduMap == null) {
            mBaiduMap = mMapView.getMap(); //得到地图控制对象
        }

        //创建地图视图
        mMapView.onCreate(context,Bundle.EMPTY);
    }

    public void setWidth() {

    }

    public void setHeight() {

    }

    public void setArrayPoint() {

    }
}
