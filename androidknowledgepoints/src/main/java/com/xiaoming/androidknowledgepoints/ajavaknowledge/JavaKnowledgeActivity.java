package com.xiaoming.androidknowledgepoints.ajavaknowledge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.androidknowledgepoints.R;

import java.util.ArrayList;
import java.util.List;

public class JavaKnowledgeActivity extends Activity {
   private ListView mJavaKnowledgeLv;
   private List<String> mDemoListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_knowledge);

        initDemoData();
        initView();
    }

    private void initDemoData() {
        mDemoListData =  new ArrayList<>();
        mDemoListData.add("值传递");
        mDemoListData.add("局部变量");
        mDemoListData.add("多线程");
    }

    private void initView() {
        mJavaKnowledgeLv = findViewById(R.id.lv_java_knowledge);

        mJavaKnowledgeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                }
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDemoListData);
        mJavaKnowledgeLv.setAdapter(arrayAdapter);
    }


}
