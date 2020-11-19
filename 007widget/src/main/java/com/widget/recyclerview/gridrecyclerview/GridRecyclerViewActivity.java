package com.widget.recyclerview.gridrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.widget.R;


public class GridRecyclerViewActivity extends AppCompatActivity {
    //声明引用
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycler_view);

        //获取控件对象
        recyclerView = findViewById(R.id.grid_recyclerview);
        //设置recyclerView的布局管理器,网格布局，第二个参数为多少列
        recyclerView.setLayoutManager(new GridLayoutManager(GridRecyclerViewActivity.this, 3));

        //设置适配器
        recyclerView.setAdapter(new GridRecyclerViewAdapter(GridRecyclerViewActivity.this));
    }
}
