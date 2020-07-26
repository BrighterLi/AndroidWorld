package com.xiaoming.androidknowledgepoints.utils;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaoming.androidknowledgepoints.R;

import java.util.ArrayList;
import java.util.List;

public class UtilActivity extends Activity {
    private ListView mLvUtil;
    List<String> demoDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);

        initData();
        initView();
    }

    private void initData() {
        demoDataList = new ArrayList<>();
        demoDataList.add("获取屏幕宽高");
        demoDataList.add("获取ROM类型");
    }

    private void initView() {
        mLvUtil = findViewById(R.id.lv_util);
        mLvUtil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               switch (i) {
                   case 0:
                       Point point = ScreenUtil.getScreenSize(getApplicationContext());
                       Toast.makeText(UtilActivity.this, "weight：" + point.x + "  height:：" + point.y, Toast.LENGTH_SHORT).show();
                       break;
                   case 1:
                       boolean isHuaWei = RomUtil.isEmui();
                       Toast.makeText(UtilActivity.this, "是否华为：" + isHuaWei, Toast.LENGTH_SHORT).show();
                       break;
               }
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoDataList);
        mLvUtil.setAdapter(arrayAdapter);
    }

}
