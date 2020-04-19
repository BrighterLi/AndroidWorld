package com.xiaoming.functionbaidumaker.custommapview;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.xiaoming.functionbaidumaker.R;

//新建MyMapView继承LinearLayout类，开发一个自定义的组合组件
public class MyMapView extends LinearLayout{
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public MyMapView(Context context) {
        super(context);
        LinearLayout.inflate(context, R.layout.layout_map, this); //获取布局
        mMapView = findViewById(R.id.map); //得到MapView对象
        if(mBaiduMap == null) {
            mBaiduMap = mMapView.getMap(); //得到地图控制对象
        }

        //创建地图视图
        mMapView.onCreate(context,Bundle.EMPTY);
    }

    public MyMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyMapView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
