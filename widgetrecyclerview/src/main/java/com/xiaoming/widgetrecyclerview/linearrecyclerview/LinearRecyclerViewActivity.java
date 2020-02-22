package com.xiaoming.widgetrecyclerview.linearrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaoming.widgetrecyclerview.R;

public class LinearRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView linearRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycler_view);

        linearRecyclerView = findViewById(R.id.linear_recyclerview);
        //设置线性布局管理器
        linearRecyclerView.setLayoutManager(new LinearLayoutManager(LinearRecyclerViewActivity.this));
        //设置Adapter
        linearRecyclerView.setAdapter(new LinearRecyclerViewAdapter(LinearRecyclerViewActivity.this));
    }
}
