package com.xiaoming.widgetlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.widgetlistview.listview2.ListView2Activity;
import com.xiaoming.widgetlistview.listview3.ListView3Activity;

import java.util.ArrayList;
import java.util.List;

//列表Demo
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        listView = findViewById(R.id.lv_main);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ListView1Activity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ListView2Activity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ListView3Activity.class));
                        break;
                }
            }
        });

        //适配器
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoListData);
        //绑定适配器
        listView.setAdapter(arrayAdapter);

    }

    private void initData() {
        demoListData = new ArrayList<>();
        demoListData.add("ListView");
        demoListData.add("ListView 基本用法");
        demoListData.add("ListView 自定义Item");
        demoListData.add("ListView 优化");
        demoListData.add("RecycleView");
        demoListData.add("ScrollView");
    }
}
