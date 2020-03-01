package com.xiaoming.widgetattribute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.widgetattribute.shadow.BackgroundImageActivity;
import com.xiaoming.widgetattribute.image.ImageAttributeActivity;
import com.xiaoming.widgetattribute.image.ScaleTypeActivity;
import com.xiaoming.widgetattribute.shadow.ShadowActivity;
import com.xiaoming.widgetattribute.shadow.ShadowActivity2;
import com.xiaoming.widgetattribute.shadow.ShadowActivity3;

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
                    case 2: startActivity(new Intent(MainActivity.this, BackgroundImageActivity.class));
                        break;
                    case 3: startActivity(new Intent(MainActivity.this, ShadowActivity.class));
                        break;
                    case 4: startActivity(new Intent(MainActivity.this, ShadowActivity2.class));
                        break;
                    case 5: startActivity(new Intent(MainActivity.this, ShadowActivity3.class));
                        break;
                }
            }
        });
    }

    private void initListDemoData() {
        demoListData = new ArrayList<>();
        demoListData.add("一般属性");
        demoListData.add("ScaleType");
        demoListData.add(".9图作为背景");
        demoListData.add("通过elevation设置背景阴影");
        demoListData.add("通过xml文件设置背影阴影和边框和圆角");
        demoListData.add("通过xml文件设置背影阴影和上下边框");
    }
}
