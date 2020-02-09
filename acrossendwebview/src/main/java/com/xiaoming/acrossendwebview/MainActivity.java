package com.xiaoming.acrossendwebview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.acrossendwebview.openh5.OpenH5Activity;
import com.xiaoming.acrossendwebview.webviewandjs.WebViewAndJSActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDemoList();

        //创建适配器
        //这里ListView的适配器选用ArrayAdapter，ListView中每一项的布局选用系统的simple_list_item_1
        listView = findViewById(R.id.lv_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, demoNameList);

        //绑定适配器
        listView.setAdapter(adapter);

        // 通过一个实现OnItemClickListener接口的匿名类的onItemClick方法来处理ListView中每一项的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                      startActivity(new Intent(MainActivity.this, OpenH5Activity.class));
                    case 1:
                        startActivity(new Intent(MainActivity.this, WebViewAndJSActivity.class));
                }
            }
        });
    }

    private void initDemoList() {
        demoNameList.add("打开H5页面");
        demoNameList.add("Android与js交互");
    }
}
