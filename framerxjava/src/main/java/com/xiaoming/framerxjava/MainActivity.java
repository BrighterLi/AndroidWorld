package com.xiaoming.framerxjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView listView;
    private List<String> demoNameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDemoList();

        listView = findViewById(R.id.lv_main);
        //创建适配器
        //这里ListView的适配器选用ArrayAdapter，ListView中每一项的布局选用系统的simple_list_item_1
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, demoNameList);
        //绑定适配器
        listView.setAdapter(adapter);

        // 通过一个实现OnItemClickListener接口的匿名类的onItemClick方法来处理ListView中每一项的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, RxJavaDemoActivity.class));
                    case 1:
                        startActivity(new Intent(MainActivity.this, RxJavaDemo2Activity.class));
                }

            }
        });
    }

    private void initDemoList() {
        demoNameList.add("RxJava Demo(观察者和被观察者分开写)");
        demoNameList.add("RxJava Demo(观察者和被观察者不分开写，链式调用)");
    }
}
