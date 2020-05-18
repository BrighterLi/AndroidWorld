package com.fenqile.androidknowledgepoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.fenqile.androidknowledgepoints.jnidemo.JniActivity;
import com.fenqile.androidknowledgepoints.jnidemo2.Jni2Activity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mLvMain;
    private List<String> demoDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoData();
        initView();
    }

    private void initView() {
        mLvMain = findViewById(R.id.lv_main);
        mLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, JniActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, Jni2Activity.class));
                        break;
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demoDataList);
        mLvMain.setAdapter(adapter);
    }

    private void initDemoData() {
        demoDataList = new ArrayList<>();
        demoDataList.add("jni：加减法");
        demoDataList.add("jni:hello world");
    }
}
