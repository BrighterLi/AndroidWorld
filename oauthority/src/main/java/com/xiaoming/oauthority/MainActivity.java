package com.xiaoming.oauthority;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//权限Demo
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
                        startActivity(new Intent(MainActivity.this, OnePermissionActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, MultiPermissionActivity.class));
                        break;
                        default:
                            break;
                }
            }
        });

        //适配器
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,demoListData);
        //绑定适配器,相当于将数据放入到view
        listView.setAdapter(arrayAdapter);
    }

    private void initData() {
        demoListData.add("单个权限申请");
        demoListData.add("多个权限申请");
        demoListData.add("使用权限第三库");
        demoListData.add("自定义弹出的权限请求框");
    }
}
