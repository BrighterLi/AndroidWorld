package com.xiaoming.androidpoints.aaabug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.aaabug.lx.AnimationBug.FirstActivity;

import java.util.ArrayList;
import java.util.List;

public class BugTestActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_test);

        initListData();
        initListView();
    }

    private void initListData() {
        mList = new ArrayList<>();
        mList.add("java.util.ConcurrentModificationException"); //0
        mList.add("动画失效Bug"); //1
    }

    private void initListView() {
        mListView = findViewById(R.id.listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        ConcurrentModificationException.testList();
                        break;
                    case 1:
                        startActivity(new Intent(BugTestActivity.this, FirstActivity.class));
                        break;
                }
            }
        });
    }
}