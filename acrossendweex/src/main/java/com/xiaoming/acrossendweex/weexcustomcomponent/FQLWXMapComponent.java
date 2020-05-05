package com.xiaoming.acrossendweex.weexcustomcomponent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.xiaoming.acrossendweex.R;
import com.xiaoming.functionbaidumaker.custommapview.MyMapView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brightli on 2020.4.20.
 * 百度地图Weex组件
 */

public class FQLWXMapComponent extends WXComponent<MyMapView> {
    MyMapView mapView;
    WXSDKInstance wxSdkInstance; //weex实例对象，用于获得上下文

    public FQLWXMapComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
        Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent");
        wxSdkInstance = instance;
    }

    /*public FQLWXMapComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, int type) {
        super(instance, dom, parent, type);
        Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent2");
        wxSdkInstance = instance;
    }*/

    @Override
    protected MyMapView initComponentHostView(@NonNull Context context) {


        //View view = LayoutInflater.from(context).inflate(R.layout.custom_map_view, null);
        //mapView = view.findViewById(R.id.custom_map);

        /*if (PermissionCheckUtil.checkLBS()) {
            Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent#initComponentHostView#PermissionCheckUtil.checkLBS()");
        }*/

        //获取CustomMapView对象
        this.mapView = new MyMapView(context);
        Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent#initComponentHostView");
        mapView.setMarkerClickListener(new CustomMapView.MarkerClickListener() {
            @Override
            public void onMarkerClick(String title) {
                Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent#onMarkerClick");
                Map inMap = new HashMap<>();
                inMap.put("title", title);
                fireEvent("pointAnnotationClick",inMap);
            }
        });
        Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent#mapView:" + mapView.toString());
        return this.mapView;
    }

    @Override
    protected void onHostViewInitialized(MyMapView host) {
        super.onHostViewInitialized(host);
        Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent#onHostViewInitialized");
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent#onCreate");
    }

    @Override
    public void onActivityCreate() {
        super.onActivityCreate();
        Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent#onActivityCreate");
    }

    @Override
    public void onActivityResume() {
        super.onActivityResume();
        Log.d("FQLWXMapComponent","bright5#FQLWXMapComponent#onActivityResume");
        mapView.onResume();
    }

    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
    }

    //宽度
    @WXComponentProp(name = "width")
    public void setWidth(float width) {
        mapView.setWidth((int) width);
        //mapView.setWidth((int)width);
    }

    //高度
    @WXComponentProp(name = "height")
    public void setHeight(float height) {
        mapView.setHeight((int) height);
    }

    //纬度，用来设置中心点
    @WXComponentProp(name = "latitude")
    public void setLatitude(float latitude) {
        mapView.setLatitude(latitude);
    }

    //经度，用来设置中心点
    @WXComponentProp(name = "longitude")
    public void setLongitude(float longitude) {
        mapView.setLongitude(longitude);
    }

    //缩放比例(4-21)
    @WXComponentProp(name = "zoom")
    public void setZoom(float zoom) {
        mapView.setZoom(zoom);
    }

    //锚点
    @WXComponentProp(name = "arrayPoint")
    public void setArrayPoint(String arrayPoint) {
        mapView.setArrayPoint(arrayPoint);
    }


    // 设置缩放比例
    @JSMethod(uiThread = true)
    public void setMapZoom(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            float zoom = (float)jsonObject.optDouble("zoom");
            mapView.setZoom(zoom);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //添加锚点
    @JSMethod(uiThread = true)
    public void addPointAnnotation(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            float latitude = (float)jsonObject.optDouble("latitude");
            float longitude = (float)jsonObject.optDouble("longitude");
            String title = jsonObject.optString("title");
            mapView.addPointAnnotation(latitude, longitude, title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //设置地图中心点
    @JSMethod(uiThread = true)
    public void setMapCenter(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            float latitude = (float)jsonObject.optDouble("latitude");
            float longitude = (float)jsonObject.optDouble("longitude");
            mapView.setMapCenter(latitude, longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //获取定位
    @JSMethod
    public void getUserLocation() {
        mapView.getUserLocation();
    }

}
