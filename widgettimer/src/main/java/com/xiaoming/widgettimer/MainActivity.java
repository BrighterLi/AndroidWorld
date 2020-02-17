package com.xiaoming.widgettimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

//三种常用定时器:https://www.cnblogs.com/dame/p/8085983.html
//延时方法
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoListData = new ArrayList<>();

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
                        startActivity(new Intent(MainActivity.this, Timer1Activity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, Timer2Activity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Timer3Activity.class));
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
        demoListData.add("第一种定时器：Handler类的postDelayed方法");
        demoListData.add("第二种定时器：用handler+timer+timeTask方法");
        demoListData.add("第一种定时器：Handler类的postDelayed方法");
    }

}
