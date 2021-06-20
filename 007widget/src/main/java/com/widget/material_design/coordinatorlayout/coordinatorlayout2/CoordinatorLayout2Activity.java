package com.widget.material_design.coordinatorlayout.coordinatorlayout2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.widget.R;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorLayout2Activity extends AppCompatActivity {
    private String mText = "自定义Behavior";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout2);

        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        CommonRecyclerAdapter<String> commonRecyclerAdapter = new CommonRecyclerAdapter<String>(this, createData(), R.layout.item_coordinator_recycle) {

            @Override
            public void convert(CommonRecyclerHolder holder, String item, int position, boolean isScrolling) {
                holder.setText(R.id.item_tv, item);
            }
        };
        mRecyclerView.setAdapter(commonRecyclerAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private List<String> createData() {
        ArrayList<String> result = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            result.add(mText + i);
        }
        return result;
    }

}