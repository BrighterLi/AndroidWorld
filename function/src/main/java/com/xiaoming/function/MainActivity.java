package com.xiaoming.function;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;

//各种功能Demo
public class MainActivity extends AppCompatActivity {
    private ListView demoListView;
    private List<String> demoListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        demoListView = findViewById(R.id.lv_main);
        demoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, IsRootActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, IsVoLteEnabledActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, StatusBarActivity.class));
                }
            }
        });

        ArrayAdapter<String>  adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,demoListData);
        demoListView.setAdapter(adapter);
    }

    private void initData() {
        demoListData.add("判断安卓设备是否root");
        demoListData.add("获取VoLte开关开关状态");
        demoListData.add("获取状态栏高度，改变状态栏颜色");
    }
}
