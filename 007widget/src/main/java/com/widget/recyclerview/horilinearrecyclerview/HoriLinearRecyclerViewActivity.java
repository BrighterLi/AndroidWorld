package com.widget.recyclerview.horilinearrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.widget.R;

public class HoriLinearRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hori_linear_recycler_view);

        recyclerView = findViewById(R.id.hori_linear_recyclerview);

        //设置成一个线性布局管理器，因为要设置方向，就不采用匿名内部类的方式了
        //生成一个LinearLayoutManager的对象
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置这个线性布局管理器的方向，为水平方向
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置recyclerView的线性布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);

        //设置适配器Adapter
        recyclerView.setAdapter(new HoriLinearRecyclerViewAdapter(HoriLinearRecyclerViewActivity.this));
    }
}
