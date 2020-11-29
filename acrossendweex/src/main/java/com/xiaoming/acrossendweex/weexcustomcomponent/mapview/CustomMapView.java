/*
package com.xiaoming.acrossendweex.weexcustomcomponent;

import android.content.Context;
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
import com.tiqianle.tiqianle_manager.R;
import com.tiqianle.tools.baidulbs.MarkerPointBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by brightli on 2020.4.20.
 * 百度地图
 *//*

public class CustomMapView extends LinearLayout {
    private static final String TAG = "CustomMapView";
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private int mWidth;
    private int mHeight;
    private float mLatitude;
    private float mLongitude;
    private float mCeneterLatitude;
    private float mCenterLongitude;
    private MarkerClickListener mMarkerClickListener;
    List<MarkerPointBean> pointList = new ArrayList<>();

    public CustomMapView(Context context) {
        super(context);
        init(context);
    }

    public CustomMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Log.d(TAG, "init");
        Log.v(TAG, "bright5#CustomMapView#init#context:" + context.toString());
        //获取布局
        LinearLayout.inflate(context, R.layout.layout_map, this);
        mMapView = findViewById(R.id.map);

        //设置CustomMapView的宽高
        if(mHeight <= 0) {
            mHeight = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        if(mWidth <= 0) {
            mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        LayoutParams params = new LayoutParams(200, mHeight);
        mMapView.setLayoutParams(params);

        //获取地图控制对象
        if(mBaiduMap == null) {
            mBaiduMap = mMapView.getMap();
        }
        //mMapView.onCreate(context, Bundle.EMPTY);
        Log.v(TAG, "bright5#CustomMapView#init2");
    }

    public void onCreate(Bundle outState) {
        Log.v(TAG, "bright5#CustomMapView#onCreate");
        mMapView.onCreate(getContext(),outState );
    }

    public void onResume() {
        Log.v(TAG, "bright5#CustomMapView#onResume");
        mMapView.onResume();
    }

    public void onPause() {
        mMapView.onPause();
    }

    public void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);
    }

    // MapView的宽度
    public void setWidth(int width) {
        mWidth = width;
        //刷新布局
        LayoutParams params = new LayoutParams(mWidth, mHeight);
        setLayoutParams(params);
        requestLayout();
    }

    //MapView的高度
    public void setHeight(int height) {
        mHeight = height;
        //刷新布局
        LayoutParams params = new LayoutParams(mWidth, mHeight);
        setLayoutParams(params);
        requestLayout();
    }

    //锚点
    public void setArrayPoint(String arrayPoint) {
        if(arrayPoint != null) {
            //解析数据
            try {
                JSONArray jsonArray = new JSONArray(arrayPoint);
                if(jsonArray != null && jsonArray.length() > 0) {
                    for(int i = 0; i < jsonArray.length(); i++) {
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
                bundle.putString("title", pointList.get(i).title);
                marker.setExtraInfo(bundle);
                //保存住对应的Marker对象
                pointList.get(i).setMarker(marker);

                // 实现Marker的点击事件
                mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Bundle bundle = marker.getExtraInfo();
                        String title = bundle.getString("title");
                        if(mMarkerClickListener != null) {
                            mMarkerClickListener.onMarkerClick(title);
                        }
                        return false;
                    }
                });
            }
        }
    }

    //设置地图缩放级别
    public void setZoom(float zoom) {
        MapStatusUpdate update = MapStatusUpdateFactory.zoomTo(zoom);
        mBaiduMap.animateMapStatus(update);
    }

    // 经度，用来设置中心点
    public void setLatitude(float latitude) {
        mCeneterLatitude = latitude;
        setMapCenter(mCeneterLatitude, mCenterLongitude);
    }
    // 纬度，用来设置中心点
    public void setLongitude(float longitude) {
        mCenterLongitude = longitude;
        setMapCenter(mCeneterLatitude, mCenterLongitude);
    }

    //设置中心点
    public void setMapCenter(float latitude, float longitude) {
        LatLng center = new LatLng(latitude,longitude);
        MapStatus mapStatus = new MapStatus.Builder()
                .target(center)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mapStatusUpdate);
    }

    //添加锚点
    public void addPointAnnotation(float latitude, float longitude, String title) {
        MarkerPointBean bean = new MarkerPointBean();
        bean.latitude = latitude;
        bean.longitude = longitude;
        bean.title = title;
        pointList.add(bean);
        setArrayPoint(null);
    }

    //获取定位
    public void getUserLocation() {

    }

    public void setMarkerClickListener(MarkerClickListener listener) {
        mMarkerClickListener = listener;
    }

    public interface MarkerClickListener {
        void onMarkerClick(String title);
    }
}
*/
