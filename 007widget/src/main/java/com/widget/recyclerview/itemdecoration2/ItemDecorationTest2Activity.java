package com.widget.recyclerview.itemdecoration2;

import android.app.Activity;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.widget.R;

public class ItemDecorationTest2Activity extends Activity {
    private GridRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_decoration_test2);

        initView();
    }


    private void initView() {
        final RecyclerView recyclerView = findViewById(R.id.recyclerView2);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new GridRecyclerViewAdapter(ItemDecorationTest2Activity.this);
        recyclerView.setAdapter(mAdapter);

        int color = getResources().getColor(R.color.gray);
        //间距的设置
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                if (params.getSpanIndex() % 2 == 0) {
                    outRect.left = 25;
                } else {
                    outRect.left = 5;
                    outRect.right = 25;
                }
                outRect.top = 5;
            }
        });
    }

}
