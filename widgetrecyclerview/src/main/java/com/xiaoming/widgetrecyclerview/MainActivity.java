package com.xiaoming.widgetrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.widgetrecyclerview.gridrecyclerview.GridRecyclerViewActivity;
import com.xiaoming.widgetrecyclerview.horilinearrecyclerview.HoriLinearRecyclerViewActivity;
import com.xiaoming.widgetrecyclerview.linearrecyclerview.LinearRecyclerViewActivity;
import com.xiaoming.widgetrecyclerview.waterfallrecyclerview.WaterfallRecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/qq_36243942/article/details/82187204?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoListData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv_main);
        initData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, LinearRecyclerViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, HoriLinearRecyclerViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, GridRecyclerViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, WaterfallRecyclerViewActivity.class));
                        break;
                }
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoListData);
        listView.setAdapter(arrayAdapter);
    }

    private void initData() {
        demoListData = new ArrayList<>();
        demoListData.add("线性垂直RecyclerView");
        demoListData.add("线性水平RecyclerView");
        demoListData.add("网格视图的RecyclerView");
        demoListData.add("瀑布流视图的RecyclerView");
    }
}
