package com.xiaoming.widgetattribute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.widgetattribute.image.ImageAttributeActivity;
import com.xiaoming.widgetattribute.image.ScaleTypeActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv_main);

        initListDemoData();
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, demoListData);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: startActivity(new Intent(MainActivity.this, ImageAttributeActivity.class));
                    break;
                    case 1: startActivity(new Intent(MainActivity.this, ScaleTypeActivity.class));
                        break;
                }
            }
        });
    }

    private void initListDemoData() {
        demoListData = new ArrayList<>();
        demoListData.add("一般属性");
        demoListData.add("ScaleType");
    }
}
