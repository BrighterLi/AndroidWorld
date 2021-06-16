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
import com.widget.aaaview.get_tag.ViewGetTagActivity;


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
        mDataList.add("自定义View");
        mDataList.add("View getTag");

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
                }
            }
        });
    }
}

