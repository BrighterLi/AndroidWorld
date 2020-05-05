package com.xiaoming.acrossendweex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.xiaoming.acrossendweex.openweexpage.OpenWeexPageActivity;
import com.xiaoming.acrossendweex.weexcustomcomponent.WeexComponentActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initEvent();

        //适配器
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, demoNameList);
        //绑定适配器
        listView.setAdapter(arrayAdapter);
    }

    private void initData() {
        demoNameList.add("Android打开本地Weex页面");
        demoNameList.add("WeexComponent自定义");
    }

    private void initView() {
        listView = findViewById(R.id.lv_main);
    }

    private void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //Android打开本地Weex页面
                        startActivity(new Intent(MainActivity.this, OpenWeexPageActivity.class));
                    case 1: //Android打开本地Weex页面
                        startActivity(new Intent(MainActivity.this, WeexComponentActivity.class));
                }
            }
        });
    }
}
