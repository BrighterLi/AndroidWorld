package com.widget.recyclerview;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.widget.R;
import com.widget.recyclerview.ItemDecoration.ItemDecorationTestActivity;
import com.widget.recyclerview.gridrecyclerview.GridRecyclerViewActivity;
import com.widget.recyclerview.horilinearrecyclerview.HoriLinearRecyclerViewActivity;
import com.widget.recyclerview.itemdecoration2.ItemDecorationTest2Activity;
import com.widget.recyclerview.linearrecyclerview.LinearRecyclerViewActivity;
import com.widget.recyclerview.nestrecyclerview.NestRecyclerViewActivity;
import com.widget.recyclerview.twoitemsrecyclerview.TwoItemsRecyclerViewActivity;
import com.widget.recyclerview.waterfallrecyclerview.WaterfallRecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/qq_36243942/article/details/82187204?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
public class RecyclerViewActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> demoListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        listView = findViewById(R.id.lv_main);
        initData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(RecyclerViewActivity.this, LinearRecyclerViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(RecyclerViewActivity.this, HoriLinearRecyclerViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(RecyclerViewActivity.this, GridRecyclerViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(RecyclerViewActivity.this, WaterfallRecyclerViewActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(RecyclerViewActivity.this, NestRecyclerViewActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(RecyclerViewActivity.this, TwoItemsRecyclerViewActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(RecyclerViewActivity.this, ItemDecorationTestActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(RecyclerViewActivity.this, ItemDecorationTest2Activity.class));
                        break;
                }
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoListData);
        listView.setAdapter(arrayAdapter);
    }

    private void initData() {
        demoListData = new ArrayList<>();
        demoListData.add("线性垂直RecyclerView");
        demoListData.add("线性水平RecyclerView");
        demoListData.add("网格视图的RecyclerView");
        demoListData.add("瀑布流视图的RecyclerView");
        demoListData.add("RecyclerView嵌套：横向+垂直");
        demoListData.add("RecyclerView根据不同的ViewHolder实现不同的Item");
        demoListData.add("RcyclerView的间距设置ItemDecoration");
        demoListData.add("RcyclerView的间距设置ItemDecoration");
    }
}
