package com.xiaoming.function.androidknowledgepoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.function.R;

import java.util.ArrayList;
import java.util.List;

//各种Android知识点Demo
public class AndroidKnowledgePointsActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_knowledge_points);

        initDemoData();
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.lv_android_knowledge);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(AndroidKnowledgePointsActivity.this, DialogShowActivity.class));
                        break;
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoDataList);
        listView.setAdapter(adapter);
    }

    private void initDemoData() {
        demoDataList = new ArrayList<>();
        demoDataList.add("Dialog只能在主线程里执行");
    }
}
