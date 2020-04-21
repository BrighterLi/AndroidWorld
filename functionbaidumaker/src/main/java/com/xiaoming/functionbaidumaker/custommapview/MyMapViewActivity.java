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
        //MyMapView.setArrayPoint();
        setContentView(R.layout.activity_my_map_view);

        initView();
    }

    private void initView() {
        //获取MyMapView对象
        mMyMapView = findViewById(R.id.my_map_view);
        //mMyMapView.setHeight(500);
        //mMyMapView.setWidth(500);
        //mMyMapView.setArrayPoint(); //锚点
        String str = "[{\"latitude\":\"39.963175\", \"longitude\":\"116.400244\", \"title\":\"1\"}]";
        String str2 = "[\n" +
                "  {\n" +
                "    \"latitude\": \"22.112\",\n" +
                "    \"longitude\": \"113.224\",\n" +
                "    \"title\": \"1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"latitude\": \"39.963175\",\n" +
                "    \"longitude\": \"116.400244\",\n" +
                "    \"title\": \"2\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"latitude\": \"39.947246\",\n" +
                "    \"longitude\": \"116.414977\",\n" +
                "    \"title\": \"3\"\n" +
                "  }\n" +
                "]";
        mMyMapView.setArrayPoint(str2); //锚点
        //mMyMapView.setZoom(4); //缩放级别
        //mMyMapView.setLatitude((float)29.806651); //用来设置默认中心点
        //mMyMapView.setLongitude((float)121.606983); //用来设置默认中心点
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyMapView.onResume();
    }
}
