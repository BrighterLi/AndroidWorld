package com.xiaoming.androidpoints.aaajavaknowledge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.aaajavaknowledge.abstractclass.AClass;
import com.xiaoming.androidpoints.aaajavaknowledge.abstractclass.BClass;
import com.xiaoming.androidpoints.aaajavaknowledge.initialize.InitializeActivity;
import com.xiaoming.androidpoints.aaajavaknowledge.localvarible.LocalVaribleActivity;
import com.xiaoming.androidpoints.aaajavaknowledge.valuetransition.ValueTransitionActivity;

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
        mDemoListData.add("初始化");
        mDemoListData.add("抽象类");
    }

    private void initView() {
        mJavaKnowledgeLv = findViewById(R.id.lv_java_knowledge);

        mJavaKnowledgeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(JavaKnowledgeActivity.this, ValueTransitionActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(JavaKnowledgeActivity.this, LocalVaribleActivity.class));
                        break;
                    case 2:
                        //startActivity(new Intent(JavaKnowledgeActivity.this, MultiThreadsActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(JavaKnowledgeActivity.this, InitializeActivity.class));
                        break;
                    case 4:
                        AClass aClass = new AClass();
                        BClass bClass = new BClass();
                        break;
                }
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDemoListData);
        mJavaKnowledgeLv.setAdapter(arrayAdapter);
    }


}
