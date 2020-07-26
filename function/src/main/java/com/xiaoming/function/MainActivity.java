package com.xiaoming.function;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.function.command.CommandActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView mListView;
    private List<String> demoDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoData();
        initView();
    }

    private void initDemoData() {
        demoDataList = new ArrayList<>();
        demoDataList.add("乐口令");
    }

    private void initView() {
        mListView = findViewById(R.id.main_list);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoDataList);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, CommandActivity.class));
                        break;
                }
            }
        });
    }
}
