package com.xiaoming.widgetrecyclerview.waterfallrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.xiaoming.widgetrecyclerview.R;

public class WaterfallRecyclerViewActivity extends AppCompatActivity {
    //声明引用
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterfall_recycler_view);

        //获取控件对象
        recyclerView = findViewById(R.id.waterfall_recyclerview);

        //设置布局管理器。两个参数，如果后面参数是垂直代表第一个是几列；
        //如果是水平，第一个代表行数
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //设置Adapter
        recyclerView.setAdapter(new WaterfallRecyclerViewAdapter(WaterfallRecyclerViewActivity.this));
    }
}
