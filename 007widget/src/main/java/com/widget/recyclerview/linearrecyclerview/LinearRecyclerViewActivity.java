package com.widget.recyclerview.linearrecyclerview;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.widget.R;

public class LinearRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView linearRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycler_view);

        linearRecyclerView = findViewById(R.id.linear_recyclerview);
        //设置线性布局管理器
        //item设置装饰，分割线
        linearRecyclerView.addItemDecoration(new MyDecoration());
        linearRecyclerView.setLayoutManager(new LinearLayoutManager(LinearRecyclerViewActivity.this));
        //设置Adapter
        linearRecyclerView.setAdapter(new LinearRecyclerViewAdapter(LinearRecyclerViewActivity.this));
    }

    //增加分割线,继承ItemDecoration
    class MyDecoration extends RecyclerView.ItemDecoration{
        //复写getItemOffsets方法
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}
