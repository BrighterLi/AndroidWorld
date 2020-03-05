package com.xiaoming.widgetreddot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.widgetreddot.customreddot.CustomRedDotActivity;
import com.xiaoming.widgetreddot.shape.RedDotActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView demoListView;
    private List<String> demoListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoListData();
        demoListView = findViewById(R.id.lv_main);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,demoListData);
        demoListView.setAdapter(arrayAdapter);

        demoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, RedDotActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, CustomRedDotActivity.class));
                        break;
                }
            }
        });
    }

    private void initDemoListData() {
        demoListData = new ArrayList<>();
        demoListData.add("通过shape资源制作消息红点");
        demoListData.add("通过自定义View制作消息红点");
    }
}
