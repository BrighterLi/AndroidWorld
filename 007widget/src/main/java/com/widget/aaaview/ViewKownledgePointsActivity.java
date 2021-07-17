package com.widget.aaaview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.widget.R;
import com.widget.aaaview.custom_view.fish.FishActivity;
import com.widget.aaaview.custom_view.gif.GifDemoActivity;
import com.widget.aaaview.custom_view.round_view.RoundFrameLayout;
import com.widget.aaaview.custom_view.round_view.RoundFrameLayoutActivity;
import com.widget.aaaview.get_tag.ViewGetTagActivity;
import com.widget.aaaview.layout.dynamic_add_remove.DynamicAddOrRemoveActivity;
import com.widget.aaaview.layout.dynamic_add_remove.DynamicAddOrRemoveActivity2;
import com.widget.aaaview.shape.ViewShapeActivity;
import com.widget.aaaview.share_a_view.ShareAViewActivity;


import java.util.ArrayList;
import java.util.List;

public class ViewKownledgePointsActivity extends AppCompatActivity {
    private ListView mLv;
    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_kownledge_points);
        initDemoData();
        initView();
    }

    private void initDemoData() {
        mDataList = new ArrayList<>();
        mDataList.add("自定义View"); //0
        mDataList.add("View getTag");
        mDataList.add("两个Activity公用一个View");
        mDataList.add("自定义View: 圆角矩形框背景RoundFrameLayout");
        mDataList.add("View加上shape，圆角");
        mDataList.add("View: Gif");
        mDataList.add("动态添加删除布局");

    }

    private void initView() {
        mLv = findViewById(R.id.lv_view);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDataList);
        mLv.setAdapter(arrayAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(ViewKownledgePointsActivity.this, FishActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(ViewKownledgePointsActivity.this, ViewGetTagActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(ViewKownledgePointsActivity.this, ShareAViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(ViewKownledgePointsActivity.this, RoundFrameLayoutActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(ViewKownledgePointsActivity.this, ViewShapeActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(ViewKownledgePointsActivity.this, GifDemoActivity.class));
                        break;
                    case 6:
                        //startActivity(new Intent(ViewKownledgePointsActivity.this, DynamicAddOrRemoveActivity.class));
                        startActivity(new Intent(ViewKownledgePointsActivity.this, DynamicAddOrRemoveActivity2.class));
                        break;
                }
            }
        });
    }
}

