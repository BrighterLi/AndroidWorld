package com.widget.material_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.widget.MainActivity;
import com.widget.R;
import com.widget.aaaview.ViewKownledgePointsActivity;
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
                }
            }
        });
    }
}
