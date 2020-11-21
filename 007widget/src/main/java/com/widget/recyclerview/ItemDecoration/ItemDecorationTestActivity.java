package com.widget.recyclerview.ItemDecoration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.widget.R;


//为 GridLayout 的 RecyclerView 设置 item 间距，实现所有 Item 靠边对齐，中间留白的效果：
//https://blog.csdn.net/zgh0711/article/details/71422516?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control
public class ItemDecorationTestActivity extends Activity {
    private GridRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_decoration_test);

        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new GridRecyclerViewAdapter(ItemDecorationTestActivity.this);
        recyclerView.setAdapter(mAdapter);

        int color = getResources().getColor(R.color.gray);
        //间距的设置
        recyclerView.addItemDecoration(new GridItemDecoration(this, 8 ,color) {
            @Override
            public boolean[] getItemSidesIsHaveOffsets(int itemPosition) {
                //顺序:left, top, right, bottom
                boolean[] booleans = {false, false, false, false};
                switch (itemPosition % 2) {
                    case 0:
                        //每一行第一个只显示右边距和下边距
                        booleans[2] = true;
                        booleans[3] = true;
                        break;
                    case 1:
                        //每一行第二个只显示左边距和下边距
                        booleans[0] = true;
                        booleans[3] = true;
                        break;
                }
                return booleans;
            }
        });
    }



}
