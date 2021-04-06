package com.widget.scroll.scrollconflict.demo1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.widget.R;

import java.util.ArrayList;
import java.util.List;

//内部拦截法
//同向滑动：ScrollView+ListView
//https://blog.csdn.net/chuhe1989/article/details/108741095
public class ScrollConflictActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_conflict);

        initListData();
        initView();
    }

    private void initListData() {
        mListData = new ArrayList<>();
        for(int i = 0; i< 10; i++) {
            mListData.add("list数据item" + i);
        }
    }

    private void initView() {
        mListView = findViewById(R.id.lv);
        //adapter:context;layout;listdata
        ArrayAdapter  arrayAdapter = new ArrayAdapter(ScrollConflictActivity.this, android.R.layout.simple_list_item_1, mListData);
       //listview:adater
        mListView.setAdapter(arrayAdapter);
    }
}
