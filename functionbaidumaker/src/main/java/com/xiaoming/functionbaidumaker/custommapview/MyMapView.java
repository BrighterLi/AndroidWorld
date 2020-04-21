package com.xiaoming.functionbaidumaker.custommapview;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.xiaoming.functionbaidumaker.R;
import com.xiaoming.functionbaidumaker.marker.MarkerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//新建MyMapView继承LinearLayout类，开发一个自定义的组合组件
public class MyMapView extends LinearLayout{
    private static final String TAG = "MyMapView";
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Marker mMarker;
    private  int mWidth;
    private  int mHeight;
    private float centerLatitude;
    private float centerLongitude;
    private MarkerClickListener mMarkerClickListener;
    List<MarkerPointBean> pointList = new ArrayList<>();

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
        Log.d(TAG,"init#bright#height:" + mHeight);
        LinearLayout.inflate(context, R.layout.layout_map, this); //获取布局
        mMapView = findViewById(R.id.map); //得到MapView

        //MyMapView的宽高
        if(mHeight <= 0) {
            mHeight = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        if (mWidth <=0) {
            mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth, mHeight);
        mMapView.setLayoutParams(params);

        if(mBaiduMap == null) {
            mBaiduMap = mMapView.getMap(); //得到地图控制对象
        }

        //setArrayPoint();
        //创建地图视图
        //mMapView.onCreate(context,Bundle.EMPTY);


    }

    public void setWidth(int width) {
        mWidth = width;
        //刷新布局
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mHeight, mWidth);
        setLayoutParams(params);
        requestLayout();
    }

    public void setHeight(int height) {
        Log.d("bright5","bright5#setHeight");
        mHeight = height;
        //刷新布局
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mHeight, mWidth);
        setLayoutParams(params);
        requestLayout();
    }

    public void setArrayPoint() {
        //marker自定义布局
        View markerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_marker, null);
        TextView markerText = markerView.findViewById(R.id.tv_marker_text);
        ImageView markerImg = markerView.findViewById(R.id.img_marker);
        //markerText.setText("自定义文本");
        //构建marker图标
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(markerView);

        //Marker图标
        LatLng point = new LatLng(39.963175, 116.400244);
        LatLng point2 = new LatLng(39.947246, 116.414977);
        //准备maker view
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_marker);
        //准备marker option加入marker使用
        //MarkerOptions mMarkerOptions =  new MarkerOptions().icon(bitmap).position(point);
        //使用了自定义的marker布局
        MarkerOptions mMarkerOptions =  new MarkerOptions().icon(bitmapDescriptor).position(point);
        MarkerOptions mMarkerOptions2 =  new MarkerOptions().icon(bitmapDescriptor).position(point2);
        //在地图上添加Marker
        mMarker = ((Marker) mBaiduMap.addOverlay(mMarkerOptions));
        mBaiduMap.addOverlay(mMarkerOptions2);
    }

    //使用json列表的输入形式
    public void setArrayPoint(String json) {
        //解析数据
        try {
            JSONArray jsonArray = new JSONArray(json);
            if(jsonArray != null && jsonArray.length() > 0) {
                for(int i= 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    MarkerPointBean bean = new MarkerPointBean();
                    bean.latitude = (float) jsonObject.optDouble("latitude");
                    bean.longitude = (float)jsonObject.optDouble("longitude");
                    bean.title = jsonObject.optString("title");
                    pointList.add(bean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //marker自定义布局
        View markerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_marker, null);
        TextView markerText = markerView.findViewById(R.id.tv_marker_text);
        ImageView markerImg = markerView.findViewById(R.id.img_marker);
        if(pointList.size() > 0) {
            for(int i = 0; i < pointList.size(); i++) {
                if(pointList.get(i) == null) {
                    continue;
                }
                markerText.setText(pointList.get(i).title);
                //构建marker图标
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(markerView);
                //Marker图标
                LatLng point = new LatLng(pointList.get(i).latitude, pointList.get(i).longitude);
                //准备marker option加入marker使用
                //使用了自定义的marker布局
                MarkerOptions option =  new MarkerOptions().icon(bitmapDescriptor).position(point);
                //在地图上添加Marker
                Marker marker = ((Marker) mBaiduMap.addOverlay(option));
                Bundle bundle = new Bundle();
                bundle.putString("title",pointList.get(i).title);
                marker.setExtraInfo(bundle);
                //保存住对应的Marker对象
                pointList.get(i).setMarker(marker);
            }

            //实现 Marker 的点击事件响应
            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Bundle bundle = marker.getExtraInfo();
                    String title = bundle.getString("title");
                    if(mMarkerClickListener != null) {
                        mMarkerClickListener.onMarkerClick(title);
                    }
                    Toast.makeText(getContext(), "点击title：" + title, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }

    //设置地图缩放级别
    public void setZoom(float zoom) {
        MapStatusUpdate update = MapStatusUpdateFactory.zoomTo(zoom);
        mBaiduMap.animateMapStatus(update);
    }

    // 经度，用来设置中心点
    public void setLatitude(float latitude) {
        centerLatitude = latitude;
        setMapCenter();
    }
    // 纬度，用来设置中心点
    public void setLongitude(float longitude) {
        centerLongitude = longitude;
        setMapCenter();
    }

    //设置中心点
    private void setMapCenter() {
        LatLng center = new LatLng(centerLatitude,centerLongitude);
        MapStatus mapStatus = new MapStatus.Builder()
                .target(center)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mapStatusUpdate);
    }

    public void setMarkerClickListener(MarkerClickListener listener) {
        mMarkerClickListener = listener;
    }

    public interface MarkerClickListener {
        void onMarkerClick(String title);
    }
}
