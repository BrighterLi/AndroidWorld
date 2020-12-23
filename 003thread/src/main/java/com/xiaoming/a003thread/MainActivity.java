package com.xiaoming.a003thread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.a003thread.multithreads.MultiThreadsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mLvMian;
    private List<String> mDemoListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoListData();
        initView();
    }

    private void initDemoListData() {
        mDemoListData.add("主线程和子线程方法执行顺序");
        mDemoListData.add("两个线程+共享变量");
    }

    private void initView(){
        mLvMian = findViewById(R.id.lv_main);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDemoListData);
        mLvMian.setAdapter(arrayAdapter);
        mLvMian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, ChildThreadActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, MultiThreadsActivity.class));
                        break;
                }
            }
        });
    }
}
