package com.xiaoming.functionbaidumaker.custommapview;

import com.baidu.mapapi.map.Marker;

public class MarkerPointBean {
    public float latitude;
    public float longitude;
    public String title;

    private Marker marker;

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }


}
