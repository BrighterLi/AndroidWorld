package com.widget.material_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.widget.R;
import com.widget.material_design.NestedScrollView.demo1.NestedScrollDemoActivity;
import com.widget.material_design.NestedScrollView.demo2.NestedScrollDemo2Activity;
import com.widget.material_design.NestedScrollView.demo2.ScrollViewDemoActivity;
import com.widget.material_design.coordinatorlayout.CoordinatorLayoutActivity;
import com.widget.material_design.coordinatorlayout.coordinatorlayout2.CoordinatorLayout2Activity;
import com.widget.material_design.toolbar.ToolbarActivity;
import com.widget.material_design.viewpager2.ViewPager2Activity;

import java.util.ArrayList;
import java.util.List;

public class MaterialDesignActivity extends AppCompatActivity {
    private ListView mLvMain;
    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        initDemoData();
        initView();
    }

    private void initDemoData() {
        mDataList = new ArrayList<>();
        mDataList.add("ViewPager2");
        mDataList.add("Toolbar");
        mDataList.add("NestedScrollView");
        mDataList.add("NestedScrollView2");
        mDataList.add("ScrollVie 对比NestedScrollView");
        mDataList.add("Coordinator");
        mDataList.add("Coordinator2");
    }


    private void initView() {
        mLvMain = findViewById(R.id.lv_main);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDataList);
        mLvMain.setAdapter(arrayAdapter);
        mLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MaterialDesignActivity.this, ViewPager2Activity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MaterialDesignActivity.this, ToolbarActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MaterialDesignActivity.this, NestedScrollDemoActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MaterialDesignActivity.this, NestedScrollDemo2Activity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MaterialDesignActivity.this, ScrollViewDemoActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MaterialDesignActivity.this, CoordinatorLayoutActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MaterialDesignActivity.this, CoordinatorLayout2Activity.class));
                        break;
                }
            }
        });
    }
}
